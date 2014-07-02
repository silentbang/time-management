package com.duke.timemanagement.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

	public static String convertToDateText(Date date) {
		return new SimpleDateFormat(Constant.FORMAT_DATE).format(date);
	}

	public static String convertToDateTimeText(Date date) {
		return new SimpleDateFormat(Constant.FORMAT_DATE_TIME).format(date);
	}

	public static String convertToTimeText(Date date) {
		return new SimpleDateFormat(Constant.FORMAT_TIME).format(date);
	}
}
