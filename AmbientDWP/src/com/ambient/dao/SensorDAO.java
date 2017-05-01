package com.ambient.dao;
 
import java.util.List;
import com.ambient.model.*;
 
public interface SensorDAO {
    public void addSensor( SensorData sensor );
    public void deleteSensor( int studentId );
   // public void updateSensor( Medidor sensor );
   //public List<Student> getAllStudents();
    public void getSensorById( String sensorId, Medidor medidor );
}