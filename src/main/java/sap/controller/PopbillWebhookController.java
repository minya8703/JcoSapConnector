package sap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import sap.dto.ResponseResult;
import sap.service.PopbillWebhookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PopbillWebhookController {
	@Autowired
	private PopbillWebhookService popbillWebhookService;

	@PostMapping("/pbconnect")
	public ResponseResult popbillWebhook(HttpServletRequest request) throws NullPointerException, Exception {
		log.info("popbillWebhook @RestController Start");
		return popbillWebhookService.getTaxInvoice(request);

	}

}