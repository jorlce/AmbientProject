package com.ambient.json;

import java.util.Map;

public class Column {

	private String id; // id of column
	private String label; // label of column
	private String type; // type of column
	private Map<String, Object> p; // properties of column

	// constructor, getters, and setters
	public Column(String id, String label, String type) {
		this.id = id;
		this.label = label;
		this.type = type;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Map<String, Object> getP() {
		return p;
	}
	public void setP(Map<String, Object> p) {
		this.p = p;
	}

}


