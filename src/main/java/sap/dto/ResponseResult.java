package sap.dto;

import sap.type.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseResult {

	private String result;

	@Builder
	public ResponseResult(String result, ErrorCode errorCode) {
//		if (errorCode==null) {
			this.result = result;
//		} else {
//			this.result = result + " : " + errorCode.getDescription();
//		}
	}
}