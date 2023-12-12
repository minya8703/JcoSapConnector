package sap.mapper.lmpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import sap.entity.HTTaxinvoice;
import sap.mapper.HTTaxinvoiceDetailRFCMapper;
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
public class HTTaxinvoiceDetailRFCMapperImpl implements HTTaxinvoiceDetailRFCMapper {
	private List<Map<String, Object>> headerList;

	@Override
	public List<Map<String, Object>> insertHTTaxinvoiceDetail(List<HTTaxinvoice> hTTaxinvoiceList, String currentDate) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < hTTaxinvoiceList.size(); i++) {
			for (int j = 0; j < hTTaxinvoiceList.get(i).getDetailList().size(); j++) {
				Map<String, Object> map = new HashMap<>();
				map.put("ZWRDT", hTTaxinvoiceList.get(i).getWriteDate());
				map.put("ZTXNO", hTTaxinvoiceList.get(i).getNtsconfirmNum());
				map.put("ZPDDT", currentDate);
				map.put("ZSERI", hTTaxinvoiceList.get(i).getDetailList().get(j).getSerialNum());
				map.put("ZPUDT", hTTaxinvoiceList.get(i).getDetailList().get(j).getPurchaseDT());
				map.put("ZITEM", hTTaxinvoiceList.get(i).getDetailList().get(j).getItemName());
				map.put("ZSPEC", hTTaxinvoiceList.get(i).getDetailList().get(j).getSpec());
				map.put("ZQTY", hTTaxinvoiceList.get(i).getDetailList().get(j).getQty());
				map.put("ZUCOST", hTTaxinvoiceList.get(i).getDetailList().get(j).getUnitCost());
				map.put("ZBASE", hTTaxinvoiceList.get(i).getDetailList().get(j).getSupplyCost());
				map.put("ZTXAM", hTTaxinvoiceList.get(i).getDetailList().get(j).getTax());
				map.put("ZMARK", hTTaxinvoiceList.get(i).getDetailList().get(j).getRemark());
				list.add(map);
			}
		}
		return list;
	}

}
