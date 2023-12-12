package sap.mapper;

import java.util.List;
import java.util.Map;

import sap.entity.HTTaxinvoice;

public interface HTTaxinvoiceDetailRFCMapper {
	List<Map<String, Object>> insertHTTaxinvoiceDetail(List<HTTaxinvoice> hTTaxinvoiceList, String currentDate);
}
