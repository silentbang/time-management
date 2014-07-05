package com.duke.timemanagement.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {

	// private static volatile DateUtils instance = new DateUtils();

	public DateUtils() {

	}

	// private DateUtils() {
	// }

	// public static DateUtils getInstance() {
	// return instance;
	// }

	public Date getCurrentDate() {
		// Instantiate to current date-time
		return new Date();
	}

	public String convertToDateText(Date date) {
		return new SimpleDateFormat(Constant.FORMAT_DATE).format(date);
	}

	public String convertToDateTimeText(Date date) {
		return new SimpleDateFormat(Constant.FORMAT_DATE_TIME).format(date);
	}

	public String convertToTimeText(Date date) {
		return new SimpleDateFormat(Constant.FORMAT_TIME).format(date);
	}
}
