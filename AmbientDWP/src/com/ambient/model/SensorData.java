package com.ambient.model;

//Bean for table sensor_id
public class SensorData {
	
	protected String sensorlabel;

	protected float latitud;
	protected float longitud;
	protected int frecuencia;
	
	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

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
	
	
	public String getSensorlabel() {
		return sensorlabel;
	}

	public void setId(String sensorlabel) {
		this.sensorlabel = sensorlabel;
	}


}
