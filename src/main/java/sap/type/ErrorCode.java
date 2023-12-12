package sap.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생하였습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
	INPUT_DATA_NOT_FOUND("입력 데이터가 없습니다."),
	INPUT_DATA_NOT_JSON("입력 데이터가 Json형식이 아닙니다."),
	SAP_DATA_FIELD_ERROR("SAP 전달 데이터에 오류가 발생하였습니다."),
	INPUT_DATA_NULL_POINTER("입력 값이 없습니다."),
	POPBILL_SERVER_ERROR("POPBILL 세금 계산서 전송 서버 오류가 발생하였습니다."),
	INPUT_DATA_PARSE_ERROR("데이터 변환 에러"),
	NOT_CREATE_FILE_ERROR("대상 파일을 만들 수 없습니다."),
	NOT_CREATE_SAP_SERVER_ERROR("서버를 만들 수 없습니다."),
	
	/**
     * ******************************* Global Error CodeList ***************************************
     * HTTP Status Code
     * 400 : Bad Request
     * 401 : Unauthorized
     * 403 : Forbidden
     * 404 : Not Found
     * 500 : Internal Server Error
     * *********************************************************************************************
     */
    // 잘못된 서버 요청400
    BAD_REQUEST_ERROR("Bad Request Exception"),

    // @RequestBody 데이터 미 존재400
    REQUEST_BODY_MISSING_ERROR("Required request body is missing"),

    // 유효하지 않은 타입400
    INVALID_TYPE_VALUE(" Invalid Type Value"),

    // Request Parameter 로 데이터가 전달되지 않을 경우400
    MISSING_REQUEST_PARAMETER_ERROR("Missing Servlet RequestParameter Exception"),

    // 입력/출력 값이 유효하지 않음400
    IO_ERROR("I/O Exception"),

    // com.google.gson JSON 파싱 실패400
    JSON_PARSE_ERROR("JsonParseException"),

    // com.fasterxml.jackson.core Processing Error400
    JACKSON_PROCESS_ERROR("com.fasterxml.jackson.core Exception"),

    // 권한이 없음403
    FORBIDDEN_ERROR("Forbidden Exception"),

    // 서버로 요청한 리소스가 존재하지 않음404
    NOT_FOUND_ERROR("Not Found Exception"),

    // NULL Point Exception 발생404
    NULL_POINT_ERROR("Null Point Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음404
    NOT_VALID_ERROR("handle Validation Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음404
    NOT_VALID_HEADER_ERROR("Header에 데이터가 존재하지 않는 경우 "),

    // 서버가 처리 할 방법을 모르는 경우 발생500
    //INTERNAL_SERVER_ERROR("Internal Server Error Exception"),


    /**
     * ******************************* Custom Error CodeList ***************************************
     * 200
     */
    // Transaction Insert Error
    INSERT_ERROR("Insert Transaction Error Exception"),

    // Transaction Update Error
    UPDATE_ERROR("Update Transaction Error Exception"),

    // Transaction Delete Error
    DELETE_ERROR("Delete Transaction Error Exception");
    private String description;
}
