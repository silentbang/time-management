package com.duke.passato.common;

public class Constant {

	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_TIME = "HH:mm:ss";

	public static final String EMPTY = "";
	public static final String SPACE = " ";

	// The maximum delta between expected and actual for which both numbers are
	// still considered equal (JUnit)
	public static final double DELTA = 1e-15;

	public static final String MESSAGE_LIST = "messages";

	public class PAGING {
		public static final int DISPLAYED_PAGES_COUNT = 5;
		public static final int MAX_RESULTS_PER_PAGE = 3;
	}

	public class Tag {
		public static final String SUM = "sum";
		public static final String SUM_ESTIMATEDDURATION = "SUM(\"estimatedDuration\")";
		public static final String SUM_TOTALESTIMATEDDURATION = "totalEstimatedDuration";
		public static final String SUM_TOTALACTUALDURATION = "totalActualDuration";
		public static final String SUM_AVERAGEPROGRESS = "averageProgress";

		public static final String TASK_TASKTYPEID = "taskTypeId";
	}

	public class UI_CSS_CLASS {
		public static final String PROGRESS_BAR_DANGER = "progress-bar-danger";
		public static final String PROGRESS_BAR_WARNING = "progress-bar-warning";
		public static final String PROGRESS_BAR_SUCCESS = "progress-bar-success";
		public static final String PROGRESS_BAR_INFO = "progress-bar-info";
	}
}
