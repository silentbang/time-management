package com.duke.passato.common;

public enum TaskType {
	IMPORTANT_URGENT(1, "IMPORTANT_URGENT", "label.taskType.importantUrgent", "red"), IMPORTANT_NOTURGENT(2, "IMPORTANT_NOTURGENT", "label.taskType.importantNotUrgent", "green"), NOTIMPORTANT_URGENT(
			3, "NOTIMPORTANT_URGENT", "label.taskType.notImportantUrgent", "blue"), NOTIMPORTANT_NOTURGENT(4, "NOTIMPORTANT_NOTURGENT", "label.taskType.notImportantNotUrgent", "purple");

	private int value;
	private String typeName;
	private String displayName;
	private String color;

	private TaskType(int value, String typeName, String displayName, String color) {
		this.value = value;
		this.typeName = typeName;
		this.displayName = displayName;
		this.color = color;
	}

	public int getValue() {
		return this.value;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getColor() {
		return this.color;
	}

}
