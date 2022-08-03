package com.ea.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	public static Date getDateTime(String date) {
		Date returndate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			returndate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return returndate;
	}

	public static String getLoggerDateTime(long date) {
		Date loggerDate = new Date(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
		String returnDate = simpleDateFormat.format(loggerDate);
		return returnDate;
	}

}
