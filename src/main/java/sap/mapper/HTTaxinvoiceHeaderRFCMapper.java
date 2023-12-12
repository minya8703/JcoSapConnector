package sap.mapper;

import java.util.List;
import java.util.Map;

import sap.dto.HTTaxinvoiceHeaderCSVDto;

public interface HTTaxinvoiceHeaderRFCMapper {
	List<Map<String, Object>> insertHTTaxinvoiceHeader(List<HTTaxinvoiceHeaderCSVDto> dtoList, String crementDate);
}
