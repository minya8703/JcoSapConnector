package sap.mapper;

import java.util.List;
import java.util.Map;

import sap.dto.HTTaxinvoiceBodyCSVDto;

public interface HTTaxinvoiceBadyRFCMapper {
	List<Map<String, Object>> insertHTTaxinvoiceBady(List<HTTaxinvoiceBodyCSVDto> dtoList);
}
