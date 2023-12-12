package sap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popbill.api.IssueResponse;
import com.popbill.api.PopbillException;
import com.popbill.api.TaxinvoiceService;
import com.popbill.api.taxinvoice.Taxinvoice;
import com.popbill.api.taxinvoice.TaxinvoiceAddContact;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import lombok.extern.slf4j.Slf4j;
import sap.dto.ResponseResult;
import sap.mapper.TaxinvoiceMapper;
import sap.type.StatusEnum;

@Slf4j
@Service
public class HTTaxinvoiceIssueService {

	@Autowired
	private TaxinvoiceService taxinvoiceService;

	@Autowired
	private TaxinvoiceMapper taxinvoiceMapper;

	IssueResponse issueRes = null;

	public ResponseResult setHTTaxinvoiceIssue(List<Map<String, Object>> taxinvoiceList,
			List<Map<String, Object>> detailList, JCoFunction jCofunction) {
		log.info("==> PopbillWebhookService.getTaxInvoice Start");		
		for (int i = 0; i < taxinvoiceList.size(); i++) {
//		for (Map<String, Object> taxinvoicedata : taxinvoiceList) {
			Taxinvoice taxinvoice = new Taxinvoice();
			taxinvoice = taxinvoiceMapper.toEntity(taxinvoiceList.get(i), detailList);
			// 팝빌회원 사업자번호
			String CorpNum = "1234567890";
			/**********************************************************************
			 * 추가담당자 정보 - 세금계산서 발행 안내 메일을 수신받을 공급받는자 담당자가 다수인 경우 - 담당자 정보를 추가하여 발행 안내메일을
			 * 다수에게 전송할 수 있습니다. (최대 5명)
			 *********************************************************************/

			taxinvoice.setAddContactList(new ArrayList<TaxinvoiceAddContact>());

			TaxinvoiceAddContact addContact = new TaxinvoiceAddContact();

			// TODO
			addContact.setSerialNum(1);
			addContact.setContactName("담당자이름");
			addContact.setEmail("minya8703@gmail.com");

			taxinvoice.getAddContactList().add(addContact);

			// 거래명세서 동시작성여부 (true / false 중 택 1)
			// └ true = 사용 , false = 미사용
			// - 미입력 시 기본값 false 처리
			Boolean WriteSpecification = false;

			// {writeSpecification} = true인 경우, 거래명세서 문서번호 할당
			// - 미입력시 기본값 세금계산서 문서번호와 동일하게 할당
			String DealInvoiceKey = null;

			// 즉시발행 메모
			String Memo = "즉시발행 메모";

			// 지연발행 강제여부 (true / false 중 택 1)
			// └ true = 가능 , false = 불가능
			// - 미입력 시 기본값 false 처리
			// - 발행마감일이 지난 세금계산서를 발행하는 경우, 가산세가 부과될 수 있습니다.
			// - 가산세가 부과되더라도 발행을 해야하는 경우에는 forceIssue의 값을
			// true로 선언하여 발행(Issue API)를 호출하 시면 됩니다.
			Boolean ForceIssue = true;
			JCoTable jCoTable = jCofunction.getTableParameterList().getTable("T_TAXINVOICE");
			jCoTable.setRow(i);
			try {
				issueRes = taxinvoiceService.registIssue(CorpNum, taxinvoice, WriteSpecification, Memo, ForceIssue,
						DealInvoiceKey);
				log.info(taxinvoice.getInvoicerMgtKey() + " 전자 세금 계산서를 발행 하였습니다.! ");
				jCoTable.setValue("ZERCD", "S");
				jCoTable.setValue("ZERMSG", "SUCCESS");
			} catch (PopbillException e) {
				log.error("PopbillException 오류 코드" + e.getCode() + " : " + e.getMessage());
				log.error(taxinvoice.getInvoicerMgtKey() + " 전자 세금 계산서를 발행에 실패 하였습니다.! ");
				jCoTable.setValue("ZERCD", e.getCode());
				jCoTable.setValue("ZERMSG",e.getMessage());
			}
		}
		log.info("==> PopbillWebhookService.getTaxInvoice Service END");
		return ResponseResult.builder().result(StatusEnum.OK.code).build();
	}
	
//	public ResponseResult setHTTaxinvoiceCancelIssue(List<Map<String, Object>> taxinvoiceList,
//			List<Map<String, Object>> detailList, JCoFunction jCofunction) {
//		log.info("==> PopbillWebhookService.getTaxInvoice Start");
//		String status = "OK";
//		try {
//			taxinvoiceService.cancelIssue(status, null, status, status, status);
//		} catch (PopbillException e) {
//			log.error("PopbillException 오류 코드" + e.getCode() + " : " + e.getMessage());
////			log.error(taxinvoice.getInvoicerMgtKey() + " 전자 세금 계산서를 발행에 실패 하였습니다.! ");
//			e.printStackTrace();
//		}
//		log.info("==> PopbillWebhookService.getTaxInvoice Service END");
//		return ResponseResult.builder().result(StatusEnum.OK.code).build();
//		
//	}

}
