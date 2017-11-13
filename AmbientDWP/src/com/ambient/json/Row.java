package com.ambient.json;

import java.util.List;
import java.util.Map;


public class Row {
	private List<Cell> c; // list of cells
	private Map<String, Object> p; // properties of row

	// constructor, getters, and setters
	public Row(){
	}

	public List<Cell> getC() {
		return c;
	}

	public void setC(List<Cell> c) {
		this.c = c;
	}

	public Map<String, Object> getP() {
		return p;
	}

	public void setP(Map<String, Object> p) {
		this.p = p;
	}

}
