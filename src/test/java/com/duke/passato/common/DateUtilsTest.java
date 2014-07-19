package com.duke.passato.common;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.duke.passato.util.CustomAbstractTransactionalJUnit4SpringContextTest;

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

	@Test
	public void testIsToday_caseToday_true() {
		DateUtils dateUtilsSpy = Mockito.spy(new DateUtils());
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(this.date);

		assertEquals(true, dateUtilsSpy.isToday(this.date));
	}

	@Test
	public void testIsToday_caseFarFromToday_false() {
		DateUtils dateUtilsSpy = Mockito.spy(new DateUtils());
		// Future
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(new Date(115, 8, 21, 14, 21, 31));
		assertEquals(false, dateUtilsSpy.isToday(this.date));
		// Past
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(new Date(113, 8, 21, 14, 21, 31));
		assertEquals(false, dateUtilsSpy.isToday(this.date));
	}

	@Test
	public void testIsToday_caseWithinToday_true() {
		DateUtils dateUtilsSpy = Mockito.spy(new DateUtils());

		// Beginning of today
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(new Date(114, 7, 20, 0, 0, 0));
		assertEquals(true, dateUtilsSpy.isToday(this.date));
		// 1 hour later
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(new Date(114, 7, 20, 14, 20, 30));
		assertEquals(true, dateUtilsSpy.isToday(this.date));
		// End of today
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(new Date(114, 7, 20, 23, 59, 59));
		assertEquals(true, dateUtilsSpy.isToday(this.date));
	}

	@Test
	public void testIsToday_case1DayFromToday_false() {
		DateUtils dateUtilsSpy = Mockito.spy(new DateUtils());

		// Future
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(new Date(114, 7, 21, 13, 20, 30));
		assertEquals(false, dateUtilsSpy.isToday(this.date));
		// Past
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(new Date(114, 7, 19, 13, 20, 30));
		assertEquals(false, dateUtilsSpy.isToday(this.date));
	}

	@Test
	public void testIsWithin3Days_caseDaysLater_true() {
		DateUtils dateUtilsSpy = Mockito.spy(new DateUtils());
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(this.date);

		assertEquals(true, dateUtilsSpy.isWithin3Days(new Date(114, 7, 21, 13, 20, 30)));
		assertEquals(true, dateUtilsSpy.isWithin3Days(new Date(114, 7, 22, 13, 20, 30)));
		assertEquals(true, dateUtilsSpy.isWithin3Days(new Date(114, 7, 23, 13, 20, 30)));
	}

	@Test
	public void testIsWithin3Days_caseDaysBefore_true() {
		DateUtils dateUtilsSpy = Mockito.spy(new DateUtils());
		Mockito.when(dateUtilsSpy.getCurrentDate()).thenReturn(this.date);

		assertEquals(true, dateUtilsSpy.isWithin3Days(new Date(114, 7, 19, 13, 20, 30)));
		assertEquals(true, dateUtilsSpy.isWithin3Days(new Date(114, 7, 18, 13, 20, 30)));
		assertEquals(true, dateUtilsSpy.isWithin3Days(new Date(114, 7, 17, 13, 20, 30)));
	}
}
