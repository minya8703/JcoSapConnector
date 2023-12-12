package sap.utils;

import static sap.type.ErrorCode.INPUT_DATA_NOT_FOUND;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import sap.exception.BusinessExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateTimeUtils {

	public static String getHyphenCrementTimeStr() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss"));
	}

	public static String getHyphenCrementDateStr() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static String getCrementDateTimeStr() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss.SSS"));
	}

	public static String getCrementDateHHmmStr() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
	}

	public static String getCrementDateStr() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		return now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	public static Date getCrementDateTime() {
		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();
		// 포맷팅
		return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date getDateFromString(String dt, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getDefault());
		Date uDate = formatter.parse(dt);

		return uDate;
	}

	public static String getDateToYMDStr(Date dateTime) {
		if (dateTime == null) {
			log.error("Error - getDateFromString ");
			throw new BusinessExceptionHandler(INPUT_DATA_NOT_FOUND);
		}
		DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = dateTime;
		assert sdFormat.format(nowDate) != null : "";
		// 포맷팅
		return sdFormat.format(nowDate);
	}

	public static String getDateToYMDHMSStr(Date dateTime) {
		if (dateTime == null) {
			log.error("Error - getDateFromString ");
			throw new BusinessExceptionHandler(INPUT_DATA_NOT_FOUND);
		}
		DateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowDate = dateTime;
		// 포맷팅
		return sdFormat.format(nowDate);
	}

}
