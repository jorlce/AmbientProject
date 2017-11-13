package com.ambient.json;

import java.util.Map;

public class Cell {

	private Object v; // value of cell
	private String f; // formatted value of cell
	private Map<String, Object> p; // properties of cell

	// constructor, getters, and setters
	public Cell(float vfloat, String fformat){
		this.v = vfloat;
		this.f = fformat;
	}

	public Object getV() {
		return v;
	}
	public void setV(Object v) {
		this.v = v;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	public Map<String, Object> getP() {
		return p;
	}
	public void setP(Map<String, Object> p) {
		this.p = p;
	}

}

