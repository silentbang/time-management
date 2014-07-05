package com.duke.timemanagement.common;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class HelperTest {
	@SuppressWarnings("deprecation")
	// 2014-08-20 13:20:30
	private final Date date = new Date(114, 7, 20, 13, 20, 30);

	@Test
	public void testConvertToDateText() {
		assertEquals("2014-08-20", Helper.convertToDateText(this.date));
	}

	@Test
	public void testConvertToTimeText() {
		assertEquals("13:20:30", Helper.convertToTimeText(this.date));
	}

	@Test
	public void testConvertToDateTimeText() {
		assertEquals("2014-08-20 13:20:30", Helper.convertToDateTimeText(this.date));
	}

}
