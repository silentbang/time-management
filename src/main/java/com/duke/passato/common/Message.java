package com.duke.passato.common;

public class Message {

	private MessageType type;
	private final String key;
	private String param;

	public Message(MessageType type, String key, String param) {
		this.type = type;
		this.key = key;
		this.param = param;
	}

	public Message(MessageType type, String key) {
		this.type = type;
		this.key = key;
	}

	public MessageType getType() {
		return this.type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getKey() {
		return this.key;
	}

	public String getParam() {
		return this.param;
	}

}
