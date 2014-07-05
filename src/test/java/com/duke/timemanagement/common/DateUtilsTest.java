package com.duke.timemanagement.common;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.duke.timemanagement.util.CustomAbstractTransactionalJUnit4SpringContextTest;

public class DateUtilsTest extends CustomAbstractTransactionalJUnit4SpringContextTest {
	@SuppressWarnings("deprecation")
	// 2014-08-20 13:20:30
	private final Date date = new Date(114, 7, 20, 13, 20, 30);

	@Autowired
	private DateUtils dateUtils;

	@Before
	public void setUp() {

	}

	@Test
	public void testConvertToDateText() {
		assertEquals("2014-08-20", this.dateUtils.convertToDateText(this.date));
	}

	@Test
	public void testConvertToTimeText() {
		assertEquals("13:20:30", this.dateUtils.convertToTimeText(this.date));
	}

	@Test
	public void testConvertToDateTimeText() {
		assertEquals("2014-08-20 13:20:30", this.dateUtils.convertToDateTimeText(this.date));
	}

}
