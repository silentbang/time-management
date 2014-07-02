package com.duke.timemanagement.common;

public class UIUtils {

	private static volatile UIUtils instance = new UIUtils();

	private UIUtils() {
	}

	public static UIUtils getInstance() {
		return instance;
	}

	public String computeProgressBarCSSClass(String progressText) {
		if (progressText.equals(Constant.EMPTY)) {
			return Constant.EMPTY;
		}

		double progress = Double.parseDouble(progressText);

		if (progress > 0 && progress <= 25) {
			return "progress-bar-danger";
		}
		else if (progress > 25 && progress <= 50) {
			return "progress-bar-warning";
		}
		else if (progress > 50 && progress <= 75) {
			return "progress-bar-success";
		}
		else {
			return "progress-bar-info";
		}
	}
}
