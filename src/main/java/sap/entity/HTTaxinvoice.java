package sap.entity;

import java.io.Serializable;
import java.util.List;

import com.popbill.api.hometax.HTTaxinvoiceDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HTTaxinvoice implements Serializable {

	private static final long serialVersionUID = 2727579833778637635L;

	private String writeDate;
	private String ntsconfirmNum;
	private String issueDT;
	private String sendDate;
	private Short invoiceType;
	private String taxType;
	private String taxTotal;
	private String supplyCostTotal;
	private String totalAmount;
	private String purposeType;
	private String serialNum;
	private String cash;
	private String chkBill;
	private String credit;
	private String note;
	private String remark1;
	private String remark2;
	private String remark3;
	private String invoicerCorpNum;
	private String invoicerMgtKey;
	private String invoicerTaxRegID;
	private String invoicerCorpName;
	private String invoicerCEOName;
	private String invoicerAddr;
	private String invoicerBizType;
	private String invoicerBizClass;
	private String invoicerContactName;
	private String invoicerDeptName;
	private String invoicerTEL;;
	private String invoicerEmail;
	private String invoiceeCorpNum;
	private String invoiceeType;
	private String invoiceeMgtKey;
	private String invoiceeTaxRegID;
	private String invoiceeCorpName;
	private String invoiceeCEOName;
	private String invoiceeAddr;
	private String invoiceeBizType;
	private String invoiceeBizClass;
	private String invoiceeContactName1;
	private String invoiceeDeptName1;
	private String invoiceeTEL1;
	private String invoiceeEmail1;
	private String invoiceeContactName2;
	private String invoiceeDeptName2;
	private String invoiceeTEL2;
	private String invoiceeEmail2;
	private String trusteeCorpNum;
	private String trusteeMgtKey;
	private String trusteeTaxRegID;
	private String trusteeCorpName;
	private String trusteeCEOName;
	private String trusteeAddr;
	private String trusteeBizType;
	private String trusteeBizClass;
	private String trusteeContactName;
	private String trusteeDeptName;
	private String trusteeTEL;
	private String trusteeEmail;
	private Short modifyCode;
	private String orgNTSConfirmNum;

    private List<HTTaxinvoiceDetail> detailList;
}
