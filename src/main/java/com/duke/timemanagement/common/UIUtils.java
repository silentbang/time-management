package com.duke.timemanagement.common;

public class UIUtils {

	private static volatile UIUtils instance = new UIUtils();

	private UIUtils() {
	}

	public static UIUtils getInstance() {
		return instance;
	}

	public String computeProgressBarCSSClass(String progressText) {
		if (progressText == null || progressText.equals(Constant.EMPTY)) {
			return Constant.EMPTY;
		}

		double progress = Double.parseDouble(progressText);

		if (progress > 0 && progress <= 25) {
			return Constant.UI_CSS_CLASS.PROGRESS_BAR_DANGER;
		}
		else if (progress > 25 && progress <= 50) {
			return Constant.UI_CSS_CLASS.PROGRESS_BAR_WARNING;
		}
		else if (progress > 50 && progress <= 75) {
			return Constant.UI_CSS_CLASS.PROGRESS_BAR_SUCCESS;
		}
		else {
			return Constant.UI_CSS_CLASS.PROGRESS_BAR_INFO;
		}
	}
}
