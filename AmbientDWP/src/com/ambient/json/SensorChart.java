package com.ambient.json;

import java.util.List;
import java.util.Map;

public class SensorChart {
	
	 private List<Column> cols; // list of columns
	 private List<Row> rows; // list of rows
	    
	 // constructor, getters, and setters
	 public SensorChart(){
		 rows = null;
		 cols = null;
	 }
	    
	 public List<Column> getCols() {
		return cols;
	}

	public void setCols(List<Column> cols) {
		this.cols = cols;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

		    
	 



}
