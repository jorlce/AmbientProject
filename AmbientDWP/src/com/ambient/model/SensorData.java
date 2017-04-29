package com.ambient.model;

public class SensorData {
	
	protected String idSensor_ID;

	protected float latitud;
	protected float longitud;
	
	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	
	
	public String getId() {
		return idSensor_ID;
	}

	public void setId(String idSensor_ID) {
		this.idSensor_ID = idSensor_ID;
	}


}
