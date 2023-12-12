package sap.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import sap.entity.HTTaxinvoice;
import sap.utils.DateTimeUtils;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HTTaxinvoiceBodyCSVDto {

	@NotEmpty(message = "writeDate는 필수 입력 값입니다.")
	@DateTimeFormat
	@CsvBindByName(column = "ZWRDT")
	@CsvBindByPosition(position = 0)
	@CsvDate(value = "yyyy-MM-dd")
	private Date writeDate;
	@CsvBindByName(column = "ZTXNO")
	@CsvBindByPosition(position = 1)
	private String ntsconfirmNum;
	@CsvBindByName(column = "ZPDDT")
	@CsvBindByPosition(position = 2)
	private String infSendDate;


	@CsvBindByName(column = "ZISDT")
	@CsvBindByPosition(position = 3)
	@CsvDate(value = "yyyy-MM-dd hh:mm:ss")
	private Date issueDT;
	@CsvBindByName(column = "ZSDDT")
	@CsvBindByPosition(position = 4)
	private String sendDate;
	@CsvBindByName(column = "ZIVTY")
	@CsvBindByPosition(position = 5)
	private String invoiceType;
	@CsvBindByName(column = "ZTXTP")
	@CsvBindByPosition(position = 6)
	private String taxType;
	@CsvBindByName(column = "ZTXAM")
	@CsvBindByPosition(position = 7)
	private String taxTotal;
	@CsvBindByName(column = "ZBSAM")
	@CsvBindByPosition(position = 8)
	private String supplyCostTotal;
	@CsvBindByName(column = "ZTOAM")
	@CsvBindByPosition(position = 9)
	private String totalAmount;
	@CsvBindByName(column = "ZBILTY")
	@CsvBindByPosition(position = 10)
	private String purposeType;
	@CsvBindByName(column = "ZARAP")
	@CsvBindByPosition(position = 11)
	private String accountsType;
	@CsvBindByName(column = "ZSEQN")
	@CsvBindByPosition(position = 12)
	private String serialNum;
	@CsvBindByName(column = "ZCASH")
	@CsvBindByPosition(position = 13)
	private String cash;
	@CsvBindByName(column = "ZCHKN")
	@CsvBindByPosition(position = 14)
	private String chkBill;
	@CsvBindByName(column = "ZCRDT")
	@CsvBindByPosition(position = 15)
	private String credit;
	@CsvBindByName(column = "ZNOTE")
	@CsvBindByPosition(position = 16)
	private String note;
	@CsvBindByName(column = "ZREMK1")
	@CsvBindByPosition(position = 17)
	private String remark1;
	@CsvBindByName(column = "ZREMK2")
	@CsvBindByPosition(position = 18)
	private String remark2;
	@CsvBindByName(column = "ZREMK3")
	@CsvBindByPosition(position = 19)
	private String remark3;
	@CsvBindByName(column = "ZOTXNO")
	@CsvBindByPosition(position = 20)
	private String invoicerCorpNum;
	@CsvBindByName(column = "ZODONO")
	@CsvBindByPosition(position = 21)
	private String invoicerMgtKey;
	@CsvBindByName(column = "ZOJONG")
	@CsvBindByPosition(position = 22)
	private String invoicerTaxRegID;
	@CsvBindByName(column = "ZONAME")
	@CsvBindByPosition(position = 23)
	private String invoicerCorpName;
	@CsvBindByName(column = "ZOCEOM")
	@CsvBindByPosition(position = 24)
	private String invoicerCEOName;
	@CsvBindByName(column = "ZOADDR")
	@CsvBindByPosition(position = 25)
	private String invoicerAddr;
	@CsvBindByName(column = "ZOUPTA")
	@CsvBindByPosition(position = 26)
	private String invoicerBizType;
	@CsvBindByName(column = "ZOJMOK")
	@CsvBindByPosition(position = 27)
	private String invoicerBizClass;
	@CsvBindByName(column = "ZONAME")
	@CsvBindByPosition(position = 28)
	private String invoicerContactName;
	@CsvBindByName(column = "ZODEPT")
	@CsvBindByPosition(position = 29)
	private String invoicerDeptName;
	@CsvBindByName(column = "ZOTELN")
	@CsvBindByPosition(position = 30)
	private String invoicerTEL;
	@CsvBindByName(column = "ZOMAIL")
	@CsvBindByPosition(position = 31)
	private String invoicerEmail;
	@CsvBindByName(column = "ZCTXNO")
	@CsvBindByPosition(position = 32)
	private String invoiceeCorpNum;
	@CsvBindByName(column = "ZCGUBUN")
	@CsvBindByPosition(position = 33)
	private String invoiceeType;
	@CsvBindByName(column = "ZCDONO")
	@CsvBindByPosition(position = 34)
	private String invoiceeMgtKey;
	@CsvBindByName(column = "ZCJONG")
	@CsvBindByPosition(position = 35)
	private String invoiceeTaxRegID;
	@CsvBindByName(column = "ZCNAME")
	@CsvBindByPosition(position = 36)
	private String invoiceeCorpName;
	@CsvBindByName(column = "ZCCEOM")
	@CsvBindByPosition(position = 37)
	private String invoiceeCEOName;
	@CsvBindByName(column = "ZCADDR")
	@CsvBindByPosition(position = 38)
	private String invoiceeAddr;
	@CsvBindByName(column = "ZCUPTA")
	@CsvBindByPosition(position = 39)
	private String invoiceeBizType;
	@CsvBindByName(column = "ZCJMOK")
	@CsvBindByPosition(position = 40)
	private String invoiceeBizClass;
	@CsvBindByName(column = "ZCNAM1")
	@CsvBindByPosition(position = 41)
	private String invoiceeContactName1;
	@CsvBindByName(column = "ZCDPT1")
	@CsvBindByPosition(position = 42)
	private String invoiceeDeptName1;
	@CsvBindByName(column = "ZCTEL1")
	@CsvBindByPosition(position = 43)
	private String invoiceeTEL1;
	@CsvBindByName(column = "ZCMAIL1")
	@CsvBindByPosition(position = 44)
	private String invoiceeEmail1;
	@CsvBindByName(column = "ZCNAM2")
	@CsvBindByPosition(position = 45)
	private String invoiceeContactName2;
	@CsvBindByName(column = "ZCDPT2")
	@CsvBindByPosition(position = 46)
	private String invoiceeDeptName2;
	@CsvBindByName(column = "ZCTEL2")
	@CsvBindByPosition(position = 47)
	private String invoiceeTEL2;
	@CsvBindByName(column = "ZCMAIL2")
	@CsvBindByPosition(position = 48)
	private String invoiceeEmail2;
	@CsvBindByName(column = "ZSUTXN")
	@CsvBindByPosition(position = 49)
	private String trusteeCorpNum;
	@CsvBindByName(column = "ZSUDOC")
	@CsvBindByPosition(position = 50)
	private String trusteeMgtKey;
	@CsvBindByName(column = "ZSUJONG")
	@CsvBindByPosition(position = 51)
	private String trusteeTaxRegID;
	@CsvBindByName(column = "ZSUNM")
	@CsvBindByPosition(position = 52)
	private String trusteeCorpName;
	@CsvBindByName(column = "ZSUCEO")
	@CsvBindByPosition(position = 53)
	private String trusteeCEOName;
	@CsvBindByName(column = "ZSUADDR")
	@CsvBindByPosition(position = 54)
	private String trusteeAddr;
	@CsvBindByName(column = "ZSUUPT")
	@CsvBindByPosition(position = 55)
	private String trusteeBizType;
	@CsvBindByName(column = "ZSUJONG")
	@CsvBindByPosition(position = 56)
	private String trusteeBizClass;
	@CsvBindByName(column = "ZSUNM")
	@CsvBindByPosition(position = 57)
	private String trusteeContactName;
	@CsvBindByName(column = "ZSUDPT")
	@CsvBindByPosition(position = 58)
	private String trusteeDeptName;
	@CsvBindByName(column = "ZSUTEL")
	@CsvBindByPosition(position = 59)
	private String trusteeTEL;
	@CsvBindByName(column = "ZSUEMAL")
	@CsvBindByPosition(position = 60)
	private String trusteeEmail;
	@CsvBindByName(column = "ZMODY")
	@CsvBindByPosition(position = 61)
	private String modifyCode;
	@CsvBindByName(column = "ZSRCTXN")
	@CsvBindByPosition(position = 62)
	private String orgNTSConfirmNum;

	public static HTTaxinvoiceBodyCSVDto fromEntity(HTTaxinvoice hTTaxinvoice, String headerCorpnum, String currentDate)
			throws Exception {
		return HTTaxinvoiceBodyCSVDto.builder()
				.infSendDate(currentDate)
				.writeDate(DateTimeUtils.getDateFromString(hTTaxinvoice.getWriteDate(), "yyyyMMdd"))
				.ntsconfirmNum(hTTaxinvoice.getNtsconfirmNum())
				.issueDT(DateTimeUtils.getDateFromString(hTTaxinvoice.getIssueDT(), "yyyyMMddhhmmss"))
				.sendDate(hTTaxinvoice.getSendDate())
				.invoiceType(String.valueOf(hTTaxinvoice.getInvoiceType()))
				.taxType(hTTaxinvoice.getTaxType())
				.taxTotal(hTTaxinvoice.getTaxTotal())
				.supplyCostTotal(hTTaxinvoice.getSupplyCostTotal())
				.totalAmount(hTTaxinvoice.getTotalAmount())
				.purposeType(hTTaxinvoice.getPurposeType())
				.accountsType(headerCorpnum.equals(hTTaxinvoice.getInvoicerCorpNum())? "AR" : "AP")
				.serialNum(hTTaxinvoice.getSerialNum())
				.cash(hTTaxinvoice.getCash())
				.chkBill(hTTaxinvoice.getChkBill())
				.credit(hTTaxinvoice.getCredit())
				.note(hTTaxinvoice.getNote())
				.remark1(hTTaxinvoice.getRemark1())
				.remark2(hTTaxinvoice.getRemark2())
				.remark3(hTTaxinvoice.getRemark3())
				.invoicerCorpNum(hTTaxinvoice.getInvoicerCorpNum())
				.invoicerMgtKey(hTTaxinvoice.getInvoicerMgtKey())
				.invoicerTaxRegID(hTTaxinvoice.getInvoicerTaxRegID())
				.invoicerCorpName(hTTaxinvoice.getInvoicerCorpName())
				.invoicerCEOName(hTTaxinvoice.getInvoicerCEOName())
				.invoicerAddr(hTTaxinvoice.getInvoicerAddr())
				.invoicerBizType(hTTaxinvoice.getInvoicerBizType())
				.invoicerBizClass(hTTaxinvoice.getInvoicerBizClass())
				.invoicerContactName(hTTaxinvoice.getInvoicerContactName())
				.invoicerDeptName(hTTaxinvoice.getInvoicerDeptName())
				.invoicerTEL(hTTaxinvoice.getInvoicerTEL())
				.invoicerEmail(hTTaxinvoice.getInvoicerEmail())
				.invoiceeCorpNum(hTTaxinvoice.getInvoiceeCorpNum())
				.invoiceeType(hTTaxinvoice.getInvoiceeType())
				.invoiceeMgtKey(hTTaxinvoice.getInvoiceeMgtKey())
				.invoiceeTaxRegID(hTTaxinvoice.getInvoiceeTaxRegID())
				.invoiceeCorpName(hTTaxinvoice.getInvoiceeCorpName())
				.invoiceeCEOName(hTTaxinvoice.getInvoiceeCEOName())
				.invoiceeAddr(hTTaxinvoice.getInvoiceeAddr())
				.invoiceeBizType(hTTaxinvoice.getInvoiceeBizType())
				.invoiceeBizClass(hTTaxinvoice.getInvoiceeBizClass())
				.invoiceeContactName1(hTTaxinvoice.getInvoiceeContactName1())
				.invoiceeDeptName1(hTTaxinvoice.getInvoiceeDeptName1())
				.invoiceeTEL1(hTTaxinvoice.getInvoiceeTEL1())
				.invoiceeEmail1(hTTaxinvoice.getInvoiceeEmail1())
				.invoiceeContactName2(hTTaxinvoice.getInvoiceeContactName2())
				.invoiceeDeptName2(hTTaxinvoice.getInvoiceeDeptName2())
				.invoiceeTEL2(hTTaxinvoice.getInvoiceeTEL2())
				.invoiceeEmail2(hTTaxinvoice.getInvoiceeEmail2())
				.trusteeCorpNum(hTTaxinvoice.getTrusteeCorpNum())
				.trusteeMgtKey(hTTaxinvoice.getTrusteeMgtKey())
				.trusteeTaxRegID(hTTaxinvoice.getTrusteeTaxRegID())
				.trusteeCorpName(hTTaxinvoice.getTrusteeCorpName())
				.trusteeCEOName(hTTaxinvoice.getTrusteeCEOName())
				.trusteeAddr(hTTaxinvoice.getTrusteeAddr())
				.trusteeBizType(hTTaxinvoice.getTrusteeBizType())
				.trusteeBizClass(hTTaxinvoice.getTrusteeBizClass())
				.trusteeContactName(hTTaxinvoice.getTrusteeContactName())
				.trusteeDeptName(hTTaxinvoice.getTrusteeDeptName())
				.trusteeTEL(hTTaxinvoice.getTrusteeTEL())
				.trusteeEmail(hTTaxinvoice.getTrusteeEmail())
				.modifyCode(String.valueOf(hTTaxinvoice.getModifyCode()))
				.orgNTSConfirmNum(hTTaxinvoice.getOrgNTSConfirmNum())
				.build();
	}

}
