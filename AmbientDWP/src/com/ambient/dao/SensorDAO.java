package com.ambient.dao;
 
import java.util.List;
import com.ambient.model.*;
 
public interface SensorDAO {
    public void addSensor( SensorData sensor );
    public void deleteSensor( String unSensorId );
   // public void updateSensor( Medidor sensor );
    public List<SensorData> getAllSensors();
    public void getSensorById( String sensorId, Medidor medidor );
}