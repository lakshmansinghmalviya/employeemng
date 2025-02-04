package com.ls.project.model;

import java.sql.Timestamp;

public class SenderStatusTableDto {
	private long id;
	private long userId;
	private String content;
	private long count;
	private long sentCount;
	private long receivedCount;
	private double sentPercentage;
	private double receivedPercentage;
	private String time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSentCount() {
		return sentCount;
	}

	public void setSentCount(long sentCount) {
		this.sentCount = sentCount;
	}

	public long getReceivedCount() {
		return receivedCount;
	}

	public void setReceivedCount(long receivedCount) {
		this.receivedCount = receivedCount;
	}

	public double getSentPercentage() {
		return sentPercentage;
	}

	public void setSentPercentage(double sentPercentage) {
		this.sentPercentage = sentPercentage;
	}

	public double getReceivedPercentage() {
		return receivedPercentage;
	}

	public void setReceivedPercentage(double receivedPercentage) {
		this.receivedPercentage = receivedPercentage;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
