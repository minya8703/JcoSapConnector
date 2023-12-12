package sap.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HTTaxinvoiceHeaderCSVDto {

	@CsvBindByName(column = "ZPDDT")
	@CsvBindByPosition(position = 0)
	private String infSendDate;
	@CsvBindByName(column = "PBTYP")
	@CsvBindByPosition(position = 1)
	private String webhooType;
	@CsvBindByName(column = "PBMID")
	@CsvBindByPosition(position = 2)
	private String webhookMID;
	@CsvBindByName(column = "PBNUM")
	@CsvBindByPosition(position = 3)
	private String webhookCorpnum;
	@CsvBindByName(column = "CONTP")
	@CsvBindByPosition(position = 4)
	private String contentType;
	@CsvBindByName(column = "TOTCN")
	@CsvBindByPosition(position = 5)
	private int totalCount;
	@CsvBindByName(column = "AUTHO")
	@CsvBindByPosition(position = 6)
	private String authorization;
	@CsvBindByName(column = "APIKY")
	@CsvBindByPosition(position = 7)
	private String xApiKey;


	public static HTTaxinvoiceHeaderCSVDto fromDto(HttpServletRequest request, int bodyTotal, String currentDate) {
		return HTTaxinvoiceHeaderCSVDto.builder()
				.infSendDate(currentDate)
				.webhooType(request.getHeader("pb-webhook-type"))
				.webhookMID(request.getHeader("pb-webhook-mid"))
				.webhookCorpnum(request.getHeader("pb-webhook-corpnum"))
				.contentType(request.getHeader("content-type"))
				.totalCount(bodyTotal)
				.authorization(request.getHeader("authorization"))
				.xApiKey(request.getHeader("x-api-key"))
				.build();
	}
}
