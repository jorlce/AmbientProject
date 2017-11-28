package com.ambient.model;

import java.sql.Timestamp;
import java.util.Date;





import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

//Bean for table sensor_values
public class Medidor {

	protected long idLectura;
	protected long timelectura;
	protected float temperatura;
	protected float humedad;
	protected float nivelCO;
	protected float nivelCO2;
	protected float nivelMetano;
	protected SensorData sensorMedido;

	//Returns the Timestamp in format ('dd MM YY hh:mm')
	public String getTimelectura() {
		LocalDateTime localDateTime = new LocalDateTime(timelectura);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
	    String sqlTimeString = fmt.print(localDateTime);
	    System.out.println(sqlTimeString);
		//Date trueDate = localDateTime.toDate(DateTimeZone.UTC.toTimeZone());
		return sqlTimeString;
	}

	public void setTimelectura(long timelectura) {
		this.timelectura = timelectura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}
	
	public SensorData getSensorMedido() {
		return this.sensorMedido;
	}
	
	public void setSensorMedido(SensorData unSensor) {
		this.sensorMedido = unSensor;
	}

	public String getSensorlabel() {
		return sensorMedido.getSensorlabel();
	}

	public void setSensorlabel(String sensorlabel) {
		this.sensorMedido.setId(sensorlabel);
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