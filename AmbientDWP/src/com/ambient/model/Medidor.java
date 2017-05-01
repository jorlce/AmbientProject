package com.ambient.model;

public class Medidor {

	protected String id;
	
	protected float temperatura;
	protected float humedad;
	protected float nivelCO;
	protected float nivelCO2;
	protected float nivelMetano;
	
	public Medidor() {
		id = "";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getTemperature() {
		return temperatura;
	}

	public void setTemperature(float temperatura) {
		this.temperatura = temperatura;
	}

	public float getHumedad() {
		return humedad;
	}

	public void setHumedad(float humedad) {
		this.humedad = humedad;
	}

	public float getNivelCO() {
		return nivelCO;
	}

	public void setNivelCO(float nivelCO) {
		this.nivelCO = nivelCO;
	}

	public float getNivelCO2() {
		return nivelCO2;
	}

	public void setNivelCO2(float nivelCO2) {
		this.nivelCO2 = nivelCO2;
	}

	public float getNivelMetano() {
		return nivelMetano;
	}

	public void setNivelMetano(float nivelMetano) {
		this.nivelMetano = nivelMetano;
	}
}