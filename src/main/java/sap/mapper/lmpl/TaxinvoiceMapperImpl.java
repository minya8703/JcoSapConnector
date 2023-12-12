package sap.mapper.lmpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.popbill.api.taxinvoice.Taxinvoice;
import com.popbill.api.taxinvoice.TaxinvoiceDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import sap.mapper.TaxinvoiceMapper;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TaxinvoiceMapperImpl implements TaxinvoiceMapper {


	// 팝빌회원 사업자번호
	private String CorpNum = "1234567890";
	// 팝빌회원 아이디
	private String UserID = "testkorea";

	@Override
	public Taxinvoice toEntity(Map<String, Object> taxinvoiceData, List<Map<String, Object>> detailList) {
		log.info("팝빌 Service 접속 정보 입력.");
		Taxinvoice taxinvoice = new Taxinvoice();
		// 작성일자, 날짜형식(yyyyMMdd)
		taxinvoice.setWriteDate(taxinvoiceData.get("WRITEDATE").toString().replaceAll("-", ""));

		// 과금방향, [정과금, 역과금] 중 선택기재
		// └ 정과금 = 공급자 과금 , 역과금 = 공급받는자 과금
		// -"역과금"은 역발행 세금계산서 발행 시에만 이용가능
		taxinvoice.setChargeDirection(taxinvoiceData.get("CHARGEDIRECTION").toString());

		// 발행형태, [정발행, 역발행, 위수탁] 중 기재
		taxinvoice.setIssueType(taxinvoiceData.get("ISSUETYPE").toString());

		// [영수, 청구, "없음] 중 기재
		taxinvoice.setPurposeType(taxinvoiceData.get("PURPOSETYPE").toString());

		// 과세형태, [과세, 영세, 면세] 중 기재
		taxinvoice.setTaxType(taxinvoiceData.get("TAXTYPE").toString());

		/**********************************************************************
		 * 공급자 정보
		 *********************************************************************/

		// 공급자 사업자번호
//		taxinvoice.setInvoicerCorpNum(taxinvoiceData.get("INVOICERCORPNUM").toString());
		taxinvoice.setInvoicerCorpNum(CorpNum);

		// 공급자 종사업장 식별번호, 필요시 기재. 형식은 숫자 4자리.
		taxinvoice.setInvoicerTaxRegID(taxinvoiceData.get("INVOICERTAXREGID").toString());

		// 공급자 상호
		taxinvoice.setInvoicerCorpName(taxinvoiceData.get("INVOICERCORPNAME").toString());

		// 공급자 문서번호, 1~24자리 (숫자, 영문, '-', '_') 조합으로 사업자 별로 중복되지 않도록 구성
		taxinvoice.setInvoicerMgtKey(taxinvoiceData.get("INVOICERMGTKEY").toString());

		// 공급자 대표자 성명
		taxinvoice.setInvoicerCEOName(taxinvoiceData.get("INVOICERCEONAME").toString());

		// 공급자 주소
		taxinvoice.setInvoicerAddr(taxinvoiceData.get("INVOICERADDR").toString());

		// 공급자 종목
		taxinvoice.setInvoicerBizClass(taxinvoiceData.get("INVOICERBIZCLASS").toString());

		// 공급자 업태
		taxinvoice.setInvoicerBizType(taxinvoiceData.get("INVOICERBIZTYPE").toString());

		// 공급자 담당자 성명
		taxinvoice.setInvoicerContactName(taxinvoiceData.get("INVOICERCONTACTNAME").toString());

		// 공급자 담당자 메일주소
		taxinvoice.setInvoicerEmail(taxinvoiceData.get("INVOICEREMAIL").toString());

		// 공급자 담당자 연락처
		taxinvoice.setInvoicerTEL(taxinvoiceData.get("INVOICERTEL").toString());

		// 공급자 담당자 휴대폰번호
		taxinvoice.setInvoicerHP(taxinvoiceData.get("INVOICERHP").toString());

		// 발행 안내 문자 전송여부 (true / false 중 택 1)
		// └ true = 전송 , false = 미전송
		// └ 공급받는자 (주)담당자 휴대폰번호 {invoiceeHP1} 값으로 문자 전송
		// - 전송 시 포인트 차감되며, 전송실패시 환불처리
		taxinvoice.setInvoicerSMSSendYN(Boolean.parseBoolean(taxinvoiceData.get("INVOICERSMSSEND").toString()));

		/**********************************************************************
		 * 공급받는자 정보
		 *********************************************************************/

		// 공급받는자 구분, [사업자, 개인, 외국인] 중 기재
		taxinvoice.setInvoiceeType(taxinvoiceData.get("INVOICEETYPE").toString());

		// 공급받는자 사업자번호
		// - {invoiceeType}이 "사업자" 인 경우, 사업자번호 (하이픈 ('-') 제외 10자리)
		// - {invoiceeType}이 "개인" 인 경우, 주민등록번호 (하이픈 ('-') 제외 13자리)
		// - {invoiceeType}이 "외국인" 인 경우, "9999999999999" (하이픈 ('-') 제외 13자리)
		taxinvoice.setInvoiceeCorpNum(taxinvoiceData.get("INVOICEECORPNUM").toString());
		// TODO 공급받는자 종사업장 식별번호, 필요시 숫자4자리 기재
//        taxinvoice.setInvoiceeTaxRegID(taxinvoiceData.get("").toString());

		// 공급받는자 상호
		taxinvoice.setInvoiceeCorpName(taxinvoiceData.get("INVOICEECORPNAME").toString());

		// [역발행시 필수] 공급받는자 문서번호, 1~24자리 (숫자, 영문, '-', '_') 를 조합하여 사업자별로 중복되지 않도록 구성
		taxinvoice.setInvoiceeMgtKey(taxinvoiceData.get("INVOICEEMGTKEY").toString());

		// 공급받는자 대표자 성명
		taxinvoice.setInvoiceeCEOName(taxinvoiceData.get("INVOICEECEONAME").toString());

		// 공급받는자 주소
		taxinvoice.setInvoiceeAddr(taxinvoiceData.get("INVOICEEADDR").toString());

		// 공급받는자 종목
		taxinvoice.setInvoiceeBizClass(taxinvoiceData.get("INVOICEEBIZCLASS").toString());

		// 공급받는자 업태
		taxinvoice.setInvoiceeBizType(taxinvoiceData.get("INVOICEEBIZTYPE").toString());

		// 공급받는자 담당자 성명
		taxinvoice.setInvoiceeContactName1(taxinvoiceData.get("INVOICEECONTACTNAME1").toString());

		// 공급받는자 담당자 메일주소
		// 팝빌 개발환경에서 테스트하는 경우에도 안내 메일이 전송되므로,
		// 실제 거래처의 메일주소가 기재되지 않도록 주의 TODO
		taxinvoice.setInvoiceeEmail1(taxinvoiceData.get("INVOICEEEMAIL1").toString());

		// 공급받는자 담당자 연락처
		taxinvoice.setInvoiceeTEL1(taxinvoiceData.get("INVOICEETEL1").toString());

		// 공급받는자 담당자 휴대폰번호
		taxinvoice.setInvoiceeHP1(taxinvoiceData.get("INVOICEEHP1").toString());

		// 역발행 안내 문자 전송여부 (true / false 중 택 1)
		// └ true = 전송 , false = 미전송
		// └ 공급자 담당자 휴대폰번호 {invoicerHP} 값으로 문자 전송
		// - 전송 시 포인트 차감되며, 전송실패시 환불처리
		taxinvoice.setInvoiceeSMSSendYN(Boolean.parseBoolean(taxinvoiceData.get("INVOICEESMSSENDYN").toString()));

		/**********************************************************************
		 * 세금계산서 기재정보
		 *********************************************************************/

		// 공급가액 합계
		taxinvoice.setSupplyCostTotal(taxinvoiceData.get("SUPPLYCOSTTOTAL").toString());

		// 세액 합계
		taxinvoice.setTaxTotal(taxinvoiceData.get("TAXTOTAL").toString());

		// 합계금액, 공급가액 + 세액
		taxinvoice.setTotalAmount(taxinvoiceData.get("TOTALAMOUNT").toString());

		// 일련번호
		taxinvoice.setSerialNum(taxinvoiceData.get("SERIALNUM").toString());

		// 현금
		taxinvoice.setCash(taxinvoiceData.get("CASH").toString());

		// 수표
		taxinvoice.setChkBill(taxinvoiceData.get("CHKBILL").toString());

		// 어음
		taxinvoice.setNote(taxinvoiceData.get("NOTE").toString());

		// 외상미수금
		taxinvoice.setCredit(taxinvoiceData.get("CREDIT").toString());

		// 비고
		// {invoiceeType}이 "외국인" 이면 remark1 필수
		// - 외국인 등록번호 또는 여권번호 입력
		taxinvoice.setRemark1(taxinvoiceData.get("REMARK1").toString());
		taxinvoice.setRemark2(taxinvoiceData.get("REMARK2").toString());
		taxinvoice.setRemark3(taxinvoiceData.get("REMARK3").toString());

		// 책번호 '권' 항목, 최대값 32767
		if (!taxinvoiceData.get("KWON").toString().isEmpty()) {
			taxinvoice.setKwon((short) taxinvoiceData.get("KWON"));
		}
		if (!taxinvoiceData.get("HO").toString().isEmpty()) {
			// 책번호 '호' 항목, 최대값 32767
			taxinvoice.setHo((short) taxinvoiceData.get("HO"));
		}
		// 사업자등록증 이미지 첨부여부 (true / false 중 택 1)
		// └ true = 첨부 , false = 미첨부(기본값)
		// - 팝빌 사이트 또는 인감 및 첨부문서 등록 팝업 URL (GetSealURL API) 함수를 이용하여 등록
		taxinvoice.setBusinessLicenseYN(Boolean.parseBoolean(taxinvoiceData.get("BUSINESSLICENSEYN").toString()));

		// 통장사본 이미지 첨부여부 (true / false 중 택 1)
		// └ true = 첨부 , false = 미첨부(기본값)
		// - 팝빌 사이트 또는 인감 및 첨부문서 등록 팝업 URL (GetSealURL API) 함수를 이용하여 등록
		taxinvoice.setBankBookYN(Boolean.parseBoolean(taxinvoiceData.get("BANKBOOKYN").toString()));

		/**********************************************************************
		 * 수정세금계산서 정보 (수정세금계산서 작성시 기재) - 수정세금계산서 작성방법 안내
		 * [https://developers.popbill.com/guide/taxinvoice/java/introduction/modified-taxinvoice]
		 *********************************************************************/
		// 수정사유코드, 수정사유에 따라 1~6 중 선택기재.
		if (!taxinvoiceData.get("MODIFYCODE").toString().isEmpty()) {
			taxinvoice.setModifyCode((short) taxinvoiceData.get("MODIFYCODE"));
		}
		if (!taxinvoiceData.get("ORGNTSCONFIRMNUM").toString().isEmpty()) {
			// 수정세금계산서 작성시 원본세금계산서 국세청 승인번호 기재
			taxinvoice.setOrgNTSConfirmNum(taxinvoiceData.get("ORGNTSCONFIRMNUM").toString());
		}
		/**********************************************************************
		 * 상세항목(품목) 정보
		 *********************************************************************/

		taxinvoice.setDetailList(new ArrayList<TaxinvoiceDetail>());
		for (Map<String, Object> detailMap : detailList) {
			// 상세항목 객체
			TaxinvoiceDetail detail = new TaxinvoiceDetail();
			if (detailMap.get("INVOICERMGTKEY").equals(taxinvoiceData.get("INVOICERMGTKEY"))) {
				detail.setSerialNum((short) Integer.parseInt((String) detailMap.get("SERIALNUM"))); // 일련번호, 1부터 순차기재
				detail.setPurchaseDT(detailMap.get("PURCHASEDT").toString()); // 거래일자
				detail.setItemName(detailMap.get("ITEMNAME").toString()); // 품목명
				detail.setSpec(detailMap.get("SPEC").toString()); // 규격
				detail.setQty(detailMap.get("QTY").toString()); // 수량
				detail.setUnitCost(detailMap.get("UNITCOST").toString()); // 단가
				detail.setSupplyCost(detailMap.get("SUPPLYCOST").toString()); // 공급가액
				detail.setTax(detailMap.get("TAX").toString()); // 세액
				detail.setRemark(detailMap.get("REMARK").toString()); // 비고
				taxinvoice.getDetailList().add(detail);
			}
		}
		return taxinvoice;
	}
}
