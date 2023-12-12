package sap.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sap.conn.jco.AbapClassException;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

import sap.config.JCoConnectionManager;
import sap.service.HTTaxinvoiceIssueService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HTTaxinvoiceIssueController implements JCoServerFunctionHandler {

	@Autowired
	private HTTaxinvoiceIssueService hTTaxinvoiceIssueService;
	@Autowired
	private JCoConnectionManager jCoConnManager;

	@Override
	public void handleRequest(JCoServerContext serverCtx, JCoFunction function)
			throws AbapException, AbapClassException {
		log.info("==> HTTaxinvoiceIssueService.handleRequest Start");
		try {
			List<Map<String, Object>> taxinvoiceList = jCoConnManager.setRFCExport("T_TAXINVOICE", function);
			List<Map<String, Object>> detailList = jCoConnManager.setRFCExport("T_DETAIL", function);
			// TODO 확인용 데이터 지울 예정
			log.info("taxinvoiceList : "+taxinvoiceList.toString());
			log.info("detailList : "+detailList.toString());
 			hTTaxinvoiceIssueService.setHTTaxinvoiceIssue(taxinvoiceList, detailList, function);
		} catch (Exception e) {
			log.error("handleRequest : "+e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

}