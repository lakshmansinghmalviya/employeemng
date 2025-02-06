package com.ls.project.model;

public class Change {
	private Value value;
	private String field;

	@Override
	public String toString() {
		return "Change [value=" + value + ", field=" + field + "]";
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
