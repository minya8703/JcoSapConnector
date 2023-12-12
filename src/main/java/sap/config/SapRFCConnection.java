package sap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SapRFCConnection {
	@Autowired
    private JCoConnectionManager connManager;
	@PostConstruct
    public void init() {
        log.info("RFC Server Start");
        connManager.stepRfcServer("ZFI_SALES_TAX_TO_NTS");
    }
}