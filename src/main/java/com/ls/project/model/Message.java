package com.ls.project.model;

public class Message {
	private String from;
	private String id;
	private String timestamp;
	private Text text;
	private String type;
	@Override
	public String toString() {
		return "Message [from=" + from + ", id=" + id + ", timestamp=" + timestamp + ", text=" + text + ", type=" + type
				+ "]";
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
