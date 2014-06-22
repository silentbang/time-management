package com.duke.timemanagement.common;

public enum TaskType {
	IMPORTANT_URGENT(1, "IMPORTANT_URGENT", "red"), IMPORTANT_NOTURGENT(2, "IMPORTANT_NOTURGENT", "green"), NOTIMPORTANT_URGENT(3, "NOTIMPORTANT_URGENT", "blue"), NOTIMPORTANT_NOTURGENT(4,
			"NOTIMPORTANT_NOTURGENT", "purple");

	private int value;
	private String typeName;
	private String color;

	private TaskType(int value, String typeName, String color) {
		this.value = value;
		this.typeName = typeName;
		this.color = color;
	}

	public int getValue() {
		return this.value;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public String getColor() {
		return this.color;
	}

}
