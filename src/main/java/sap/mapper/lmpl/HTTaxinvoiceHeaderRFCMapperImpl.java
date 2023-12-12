package sap.mapper.lmpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import sap.dto.HTTaxinvoiceHeaderCSVDto;
import sap.mapper.HTTaxinvoiceHeaderRFCMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class HTTaxinvoiceHeaderRFCMapperImpl implements HTTaxinvoiceHeaderRFCMapper {
	private Map<String, Object> headerList;

	@Override
	public List<Map<String, Object>> insertHTTaxinvoiceHeader(List<HTTaxinvoiceHeaderCSVDto> dtoList,
			String crementDate) {
		List<Map<String, Object>>  list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("ZPDDT", crementDate);
		map.put("PBTYP", dtoList.get(0).getWebhooType());
		map.put("PBMID", dtoList.get(0).getWebhookMID());
		map.put("PBNUM", dtoList.get(0).getWebhookCorpnum());
		map.put("CONTP", dtoList.get(0).getContentType());
		map.put("TOTCN", String.valueOf(dtoList.get(0).getTotalCount()));
		map.put("AUTHO", dtoList.get(0).getAuthorization());
		map.put("APIKY", dtoList.get(0).getXApiKey());
		list.add(map);
		return list;
	}

}
