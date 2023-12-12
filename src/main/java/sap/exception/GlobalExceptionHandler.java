package sap.exception;

import static sap.type.ErrorCode.*;

import java.io.IOException;

import org.apache.el.parser.ParseException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sap.conn.jco.ConversionException;

import sap.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("===========MethodArgumentNotValidException===========");
		log.error("MethodArgumentNotValidException is occurred.", e);
		return new ResponseResult(INTERNAL_SERVER_ERROR.getDescription(), INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseResult handleIOExceptionException(IOException e) {
		log.error("===========IOException===========");
		log.error("IOException is occurred.", e);
		return new ResponseResult(IO_ERROR.getDescription(), IO_ERROR);
	}
	
	@ExceptionHandler(ConversionException.class)
	public ResponseResult handleConversionException(ConversionException e) {
		log.error("===========ConversionException===========");
		log.error("ConversionException is occurred.", e);
		return new ResponseResult(SAP_DATA_FIELD_ERROR.getDescription(), SAP_DATA_FIELD_ERROR);
	}

	@ExceptionHandler(ParseException.class)
	public ResponseResult handleParseException(ParseException e) {
		log.error("===========ParseException===========");
		log.error("ParseException is occurred.", e);
		return new ResponseResult(INPUT_DATA_PARSE_ERROR.getDescription(), INPUT_DATA_PARSE_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseResult handleNullPointerException(NullPointerException e) {
		log.error("===========NullPointerException===========");
		log.error("NullPointerException is occurred.", e);
		return new ResponseResult(INPUT_DATA_NULL_POINTER.getDescription(), INPUT_DATA_NULL_POINTER);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseResult handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("===========IllegalArgumentException===========");
		log.error("IllegalArgumentException is occurred.", e);
		return new ResponseResult(INPUT_DATA_PARSE_ERROR.getDescription(), INPUT_DATA_PARSE_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseResult handleHttpRequestMethodNotSupportedException(Exception e) {
		log.error("===========HttpRequestMethodNotSupportedException===========");
		log.error("HttpRequestMethodNotSupportedException is occurred.", e);
		return new ResponseResult(MISSING_REQUEST_PARAMETER_ERROR.getDescription(), MISSING_REQUEST_PARAMETER_ERROR);
	}
	
	@ExceptionHandler(BusinessExceptionHandler.class)
	public ResponseResult handleCustomException(BusinessExceptionHandler e) {
		log.error("===========HTTaxinvoiceException===========");
		log.error("{} is occurred.", e.getErrorCode());
		return new ResponseResult(e.getErrorCode().toString(), INTERNAL_SERVER_ERROR);
	}
	
}