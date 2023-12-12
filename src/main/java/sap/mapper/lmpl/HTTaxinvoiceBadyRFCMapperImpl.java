package sap.mapper.lmpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import sap.dto.HTTaxinvoiceBodyCSVDto;
import sap.mapper.HTTaxinvoiceBadyRFCMapper;
import sap.utils.DateTimeUtils;
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
public class HTTaxinvoiceBadyRFCMapperImpl implements HTTaxinvoiceBadyRFCMapper{
	private List<Map<String, Object>> bodyList;

	@Override
	public List<Map<String, Object>> insertHTTaxinvoiceBady(List<HTTaxinvoiceBodyCSVDto> dtoList) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < dtoList.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("ZWRDT", DateTimeUtils.getDateToYMDStr(dtoList.get(i).getWriteDate()));
			map.put("ZTXNO", dtoList.get(i).getNtsconfirmNum());
			map.put("ZPDDT", dtoList.get(i).getInfSendDate());
			map.put("ZISDT", DateTimeUtils.getDateToYMDHMSStr(dtoList.get(i).getIssueDT()));
			map.put("ZSDDT", dtoList.get(i).getSendDate());
			map.put("ZIVTY", dtoList.get(i).getInvoiceType());
			map.put("ZTXTP", dtoList.get(i).getTaxType());
			map.put("ZTXAM", dtoList.get(i).getTaxTotal());
			map.put("ZBSAM", dtoList.get(i).getSupplyCostTotal());
			map.put("ZTOAM", dtoList.get(i).getTotalAmount());
			map.put("ZBILTY", dtoList.get(i).getPurposeType());
			map.put("ZARAP", dtoList.get(i).getAccountsType());
			map.put("ZSEQN", dtoList.get(i).getSerialNum());
			map.put("ZCASH", dtoList.get(i).getCash());
			map.put("ZCHKN", dtoList.get(i).getChkBill());
			map.put("ZCRDT", dtoList.get(i).getCredit());
			map.put("ZNOTE", dtoList.get(i).getNote());
			map.put("ZREMK1", dtoList.get(i).getRemark1());
			map.put("ZREMK2", dtoList.get(i).getRemark2());
			map.put("ZREMK3", dtoList.get(i).getRemark3());
			map.put("ZOTXNO", dtoList.get(i).getInvoicerCorpNum());
			map.put("ZODONO", dtoList.get(i).getInvoicerMgtKey());
			map.put("ZOJONG", dtoList.get(i).getInvoicerTaxRegID());
			map.put("ZONAME", dtoList.get(i).getInvoicerCorpName());
			map.put("ZOCEOM", dtoList.get(i).getInvoicerCEOName());
			map.put("ZOADDR", dtoList.get(i).getInvoicerAddr());
			map.put("ZOUPTA", dtoList.get(i).getInvoicerBizType());
			map.put("ZOJMOK", dtoList.get(i).getInvoicerBizClass());
			map.put("ZONAME", dtoList.get(i).getInvoicerContactName());
			map.put("ZODEPT", dtoList.get(i).getInvoicerDeptName());
			map.put("ZOTELN", dtoList.get(i).getInvoicerTEL());
			map.put("ZOMAIL", dtoList.get(i).getInvoicerEmail());
			map.put("ZCTXNO", dtoList.get(i).getInvoiceeCorpNum());
			map.put("ZCGUBUN", dtoList.get(i).getInvoiceeType());
			map.put("ZCDONO", dtoList.get(i).getInvoiceeMgtKey());
			map.put("ZCJONG", dtoList.get(i).getInvoiceeTaxRegID());
			map.put("ZCNAME", dtoList.get(i).getInvoiceeCorpName());
			map.put("ZCCEOM", dtoList.get(i).getInvoiceeCEOName());
			map.put("ZCADDR", dtoList.get(i).getInvoiceeAddr());
			map.put("ZCUPTA", dtoList.get(i).getInvoiceeBizType());
			map.put("ZCJMOK", dtoList.get(i).getInvoiceeBizClass());
			map.put("ZCNAM1", dtoList.get(i).getInvoiceeContactName1());
			map.put("ZCDPT1", dtoList.get(i).getInvoiceeDeptName1());
			map.put("ZCTEL1", dtoList.get(i).getInvoiceeTEL1());
			map.put("ZCMAIL1", dtoList.get(i).getInvoiceeEmail1());
			map.put("ZCNAM2", dtoList.get(i).getInvoiceeContactName2());
			map.put("ZCDPT2", dtoList.get(i).getInvoiceeDeptName2());
			map.put("ZCTEL2", dtoList.get(i).getInvoiceeTEL2());
			map.put("ZCMAIL2", dtoList.get(i).getInvoiceeEmail2());
			map.put("ZSUTXN", dtoList.get(i).getTrusteeCorpNum());
			map.put("ZSUDOC", dtoList.get(i).getTrusteeMgtKey());
			map.put("ZSUJONG", dtoList.get(i).getTrusteeTaxRegID());
			map.put("ZSUNM", dtoList.get(i).getTrusteeCorpName());
			map.put("ZSUCEO", dtoList.get(i).getTrusteeCEOName());
			map.put("ZSUADDR", dtoList.get(i).getTrusteeAddr());   
			map.put("ZSUUPT", dtoList.get(i).getTrusteeBizType());
			map.put("ZSUJONG", dtoList.get(i).getTrusteeBizClass());
			map.put("ZSUNM", dtoList.get(i).getTrusteeContactName());
			map.put("ZSUDPT", dtoList.get(i).getTrusteeDeptName());
			map.put("ZSUTEL", dtoList.get(i).getTrusteeTEL());    
			map.put("ZSUEMAL", dtoList.get(i).getTrusteeEmail());  
			map.put("ZMODY", dtoList.get(i).getModifyCode());
			map.put("ZSRCTXN", dtoList.get(i).getOrgNTSConfirmNum());
			list.add(map);
		}
		return list;
	}

}
