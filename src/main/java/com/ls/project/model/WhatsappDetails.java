package com.ls.project.model;

import java.util.List;

public class WhatsappDetails {
	private String object;
	private List<Entry> entry;

	@Override
	public String toString() {
		return "WhatsappDetails [object=" + object + ", entry=" + entry + "]";
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}
}
