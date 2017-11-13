package com.ambient.json;

import java.util.List;

import com.ambient.model.*;
import com.fasterxml.jackson.databind.JsonNode;

public interface SensorJSON {

	public SensorData findBySensorLabel(String unSensorJson);
	public String addSensorData(SensorData unSensor);
	public Medidor findSensorMeasure(String measureJson);
	public List<SensorData> listaSensores(String arraySensorJson);
	//public List<Medidor> entreFechasMeasure(String arraySensorJson);
	public JsonNode entreFechasMeasure(String arraySensorJson);
	public String jsonForChart(List<Medidor> listMedidor);
}
