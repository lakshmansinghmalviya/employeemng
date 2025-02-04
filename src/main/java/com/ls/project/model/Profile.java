package com.ls.project.model;

public class Profile {
	private String name;

	@Override
	public String toString() {
		return "Profile [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
