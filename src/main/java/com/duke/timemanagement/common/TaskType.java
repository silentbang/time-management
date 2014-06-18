package com.duke.timemanagement.common;

public enum TaskType {
	IMPORTANT_URGENT(1), IMPORTANT_NOTURGENT(2), NOTIMPORTANT_URGENT(3), NOTIMPORTANT_NOTURGENT(4);

	private int value;

	private TaskType(int value) {
		this.value = value;
	}

}
