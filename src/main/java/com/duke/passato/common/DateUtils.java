package com.duke.passato.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
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

	public boolean isToday(Date date) {
		Days days = this.daysFromToday(date);
		return Math.abs(days.getDays()) == 0;
	}

	public boolean isWithin3Days(Date date) {
		Days days = this.daysFromToday(date);
		return days.getDays() <= 3 && days.getDays() > 0;
	}

	private Days daysFromToday(Date date) {
		DateTime today = new DateTime(this.getCurrentDate());
		DateTime convertedDate = new DateTime(date);

		Days days = Days.daysBetween(today, convertedDate);
		return days;
	}
}
