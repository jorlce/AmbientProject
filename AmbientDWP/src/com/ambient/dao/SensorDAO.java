package com.ambient.dao;
 
import java.util.List;
 
import com.ambient.model.Medidor;
 
public interface SensorDAO {
    public void addSensor( Medidor sensor );
    public void deleteSensor( int studentId );
   // public void updateSensor( Medidor sensor );
   //public List<Student> getAllStudents();
    public void getSensorById( String sensorId, Medidor medidor );
}