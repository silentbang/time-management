package com.duke.passato.common;

public enum MessageType {
	SUCCESS(1, "success", "successMessages"), INFO(2, "info", "infoMessages"), WARNING(
			3, "warning", "warningMessages"), ERROR(4, "error", "errorMessages");

	private final int value;
	private final String typeName;
	private final String typeTitle;

	private MessageType(int value, String typeName, String typeTitle) {
		this.value = value;
		this.typeName = typeName;
		this.typeTitle = typeTitle;
	}

	public int getValue() {
		return this.value;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public String getTypeTitle() {
		return this.typeTitle;
	}
}
