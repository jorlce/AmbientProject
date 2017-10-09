package com.ambient.model;

import java.sql.Timestamp;

//Bean for table sensor_values
public class Medidor {

	protected long idLectura;
	protected Timestamp timelectura;
	protected float temperatura;
	protected float humedad;
	protected float nivelCO;
	protected float nivelCO2;
	protected float nivelMetano;
	protected String sensorlabel;

	
	public Timestamp getTimelectura() {
		return timelectura;
	}

	public void setTimelectura(Timestamp timelectura) {
		this.timelectura = timelectura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}

	public String getSensorlabel() {
		return sensorlabel;
	}

	public void setSensorlabel(String sensorlabel) {
		this.sensorlabel = sensorlabel;
	}

	public void setIdLectura(long idLectura) {
		this.idLectura = idLectura;
	}
	
	public long getIdLectura() {
		return idLectura;
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