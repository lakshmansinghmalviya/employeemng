package com.ls.project.model;

import java.util.List;

public class Entry {
 private String id;
 private List<Change> changes;
 
@Override
public String toString() {
	return "Entry [id=" + id + ", changes=" + changes + "]";
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public List<Change> getChanges() {
	return changes;
}

public void setChanges(List<Change> changes) {
	this.changes = changes;
}
}
