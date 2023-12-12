package sap.mapper;

import java.util.List;
import java.util.Map;

import com.popbill.api.taxinvoice.Taxinvoice;


public interface TaxinvoiceMapper {
	Taxinvoice toEntity(Map<String, Object> taxinvoiceData, List<Map<String, Object>> detailList);
}
