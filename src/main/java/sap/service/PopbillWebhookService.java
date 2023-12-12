package sap.service;

import static sap.type.ErrorCode.INPUT_DATA_NOT_FOUND;
import static sap.type.ErrorCode.SAP_DATA_FIELD_ERROR;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import sap.config.JCoConnectionManager;
import sap.dto.HTTaxinvoiceBodyCSVDto;
import sap.dto.HTTaxinvoiceHeaderCSVDto;
import sap.dto.ResponseResult;
import sap.entity.HTTaxinvoice;
import sap.exception.BusinessExceptionHandler;
import sap.mapper.HTTaxinvoiceBadyRFCMapper;
import sap.mapper.HTTaxinvoiceDetailRFCMapper;
import sap.mapper.HTTaxinvoiceHeaderRFCMapper;
import sap.type.StatusEnum;
import sap.utils.DateTimeUtils;
import sap.utils.FileUtils;
import sap.utils.OpenCsv;

@Slf4j
@Service
public class PopbillWebhookService {
	final static String IFRESULT = "IFRESULT";
	final static String IFMSG = "IFMSG";

	String status = StatusEnum.OK.code;

	@Autowired
	HTTaxinvoiceHeaderRFCMapper headerRFCMapper;
	@Autowired
	HTTaxinvoiceBadyRFCMapper badyRFCMapper;
	@Autowired
	HTTaxinvoiceDetailRFCMapper detailRFCMapper;

	private ObjectMapper objectMapper = new ObjectMapper();

	public ResponseResult getTaxInvoice(HttpServletRequest request) throws NullPointerException, Exception {
		log.info("==> PopbillWebhookService.getTaxInvoice Start");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String currentDate = DateTimeUtils.getCrementDateHHmmStr();
		InputStream inputStream = request.getInputStream();
		if (inputStream != null) {

			StringBuffer strBuffer = new StringBuffer();

			strBuffer.append(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
			if (strBuffer.indexOf("[") != 0) {
				strBuffer.insert(0, "[");
				strBuffer.append("]");
			}
			List<HTTaxinvoice> hTTaxinvoiceList = objectMapper.readValue(strBuffer.toString(), new TypeReference<List<HTTaxinvoice>>(){});

			// CSV파일 처리
			List<HTTaxinvoiceBodyCSVDto> bodylist = new LinkedList<>();
			List<HTTaxinvoiceHeaderCSVDto> headerlist = new LinkedList<>();

			// TODO 테스트용
//			headerlist.add(HTTaxinvoiceHeaderCSVDto.fromDto(request, 0, currentDate));
			headerlist.add(HTTaxinvoiceHeaderCSVDto.fromDto(request, hTTaxinvoiceList.size(), currentDate));
			// 헤더에서 받는 기업의 사업자 번호
			String headerCorpnum = headerlist.get(0).getWebhookCorpnum();
			for (int i = 0; i < hTTaxinvoiceList.size(); i++) {
				bodylist.add(HTTaxinvoiceBodyCSVDto.fromEntity(hTTaxinvoiceList.get(i), headerCorpnum, currentDate));
			}

			// CSV 파일 생성 부분
			FileUtils.createFolder();
			File csvfile = FileUtils.createCSVFile(bodylist.size(), "_Body");
			File headercsvfile = FileUtils.createCSVFile(headerlist.size(), "_Header");
			status = OpenCsv.writeCsvData(bodylist, HTTaxinvoiceBodyCSVDto.class, csvfile);
			status = OpenCsv.writeCsvData(headerlist, HTTaxinvoiceHeaderCSVDto.class, headercsvfile);

			// SAP RFC 연결후 데이터 전송
			// SAP 연결
			JCoDestination destination = null;
			JCoConnectionManager jCoConnectionManager = new JCoConnectionManager();

			destination = jCoConnectionManager.getConnectionSAP();

			// RFC Function 호출
			JCoFunction jCoFunction = destination.getRepository().getFunction("ZFI_RFC_SCRAP_DATA");

			// Header 데이터 insert
			listMap = new ArrayList<Map<String, Object>>();
			listMap = headerRFCMapper.insertHTTaxinvoiceHeader(headerlist, currentDate);
			jCoConnectionManager.setRFCImport(listMap, "T_IF_HEADER", jCoFunction);
			// Bady 데이터 insert
			listMap = new ArrayList<Map<String, Object>>();
			listMap = badyRFCMapper.insertHTTaxinvoiceBady(bodylist);
			jCoConnectionManager.setRFCImport(listMap, "T_IF_BODY", jCoFunction);
			// Detail 데이터 insert
			listMap = new ArrayList<Map<String, Object>>();

			listMap = detailRFCMapper.insertHTTaxinvoiceDetail(hTTaxinvoiceList, currentDate);
			jCoConnectionManager.setRFCImport(listMap, "T_IF_DETAIL", jCoFunction);
			jCoFunction.execute(destination);

			// 성공여부에 따라
			if (jCoFunction.getExportParameterList().getString(IFRESULT).equals("E")
					&& !jCoFunction.getExportParameterList().getString(IFMSG).equals("성공하였습니다.")) {
				log.error("Sap RFC failed " + jCoFunction.getExportParameterList().getString("IFMSG"));
				throw new BusinessExceptionHandler(SAP_DATA_FIELD_ERROR);
			}

		} else {
			throw new BusinessExceptionHandler(INPUT_DATA_NOT_FOUND);
		}
		inputStream.close();
		log.info("==> PopbillWebhookService.getTaxInvoice Service END");
		return ResponseResult.builder().result(status).build();
	}

	public ResponseResult getTaxInvoicaaae(HttpServletRequest request) throws NullPointerException, Exception {
		return ResponseResult.builder().result(status).build();
	}

}
