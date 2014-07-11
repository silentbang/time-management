package com.duke.passato.common;

import static org.junit.Assert.*;

import org.junit.Test;

import com.duke.passato.common.Constant;
import com.duke.passato.common.UIUtils;

public class UIUtilsTest {

	@Test
	public void testComputeProgressBarCSSClass_null_emptyString() {
		assertEquals(Constant.EMPTY, UIUtils.getInstance().computeProgressBarCSSClass(null));
	}

	@Test
	public void testComputeProgressBarCSSClass_emptyString_emptyString() {
		assertEquals(Constant.EMPTY, UIUtils.getInstance().computeProgressBarCSSClass(Constant.EMPTY));
	}

	@Test(expected = NumberFormatException.class)
	public void testComputeProgressBarCSSClass_notDigit_numberFormatException() {
		UIUtils.getInstance().computeProgressBarCSSClass("NotDigit");
	}

	@Test
	public void testComputeProgressBarCSSClass_lessThan1AndGreaterThan100_info() {
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_INFO, UIUtils.getInstance().computeProgressBarCSSClass("0"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_INFO, UIUtils.getInstance().computeProgressBarCSSClass("-1"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_INFO, UIUtils.getInstance().computeProgressBarCSSClass("101"));
	}

	@Test
	public void testComputeProgressBarCSSClass_between1And25_danger() {
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_DANGER, UIUtils.getInstance().computeProgressBarCSSClass("1"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_DANGER, UIUtils.getInstance().computeProgressBarCSSClass("10"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_DANGER, UIUtils.getInstance().computeProgressBarCSSClass("25"));
	}

	@Test
	public void testComputeProgressBarCSSClass_between26And50_warning() {
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_WARNING, UIUtils.getInstance().computeProgressBarCSSClass("26"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_WARNING, UIUtils.getInstance().computeProgressBarCSSClass("35"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_WARNING, UIUtils.getInstance().computeProgressBarCSSClass("50"));
	}

	@Test
	public void testComputeProgressBarCSSClass_between51And75_success() {
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_SUCCESS, UIUtils.getInstance().computeProgressBarCSSClass("51"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_SUCCESS, UIUtils.getInstance().computeProgressBarCSSClass("68"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_SUCCESS, UIUtils.getInstance().computeProgressBarCSSClass("75"));
	}

	@Test
	public void testComputeProgressBarCSSClass_between76And100_info() {
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_INFO, UIUtils.getInstance().computeProgressBarCSSClass("76"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_INFO, UIUtils.getInstance().computeProgressBarCSSClass("81"));
		assertEquals(Constant.UI_CSS_CLASS.PROGRESS_BAR_INFO, UIUtils.getInstance().computeProgressBarCSSClass("100"));
	}
}
