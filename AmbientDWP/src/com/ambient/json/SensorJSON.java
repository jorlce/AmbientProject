package com.ambient.json;

import java.util.List;

import com.ambient.model.*;

public interface SensorJSON {

	public SensorData findBySensorLabel(String unSensorJson);
	public String addSensorData(SensorData unSensor);
	public Medidor findSensorMeasure(String measureJson);
	public List<SensorData> listaSensores(String arraySensorJson);
	
}
