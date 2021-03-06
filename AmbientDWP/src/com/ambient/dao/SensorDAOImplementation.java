package com.ambient.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

 

import com.ambient.model.Medidor;
import com.ambient.model.SensorData;
import com.ambient.util.dbutil;
 
public class SensorDAOImplementation implements SensorDAO {
 
    private Connection conn;
 
    public SensorDAOImplementation() {
        conn = dbutil.getConnection();
    }
    
    @Override
    public void addSensor( SensorData unSensorData ) {
    	 	
        try {	
        	
            String query = "insert into sensor_id (idsensor_ID, Longitud, Latitud) values (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, unSensorData.getSensorlabel() );
            preparedStatement.setFloat( 2, unSensorData.getLongitud());
            preparedStatement.setFloat( 3, unSensorData.getLatitud());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteSensor( String unSensorId ) {
        try {
            String query = "delete from sensor_id where idSensor_Values=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, unSensorId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void getSensorById(String sensorId, Medidor medidor) {
        //Medidor medidor = new Medidor();
        try {
            String query = "select * from sensor_values where sensoridfk=? order by Lectura DESC limit 1";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString(1, sensorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
            	System.out.println(resultSet.getString("sensoridfk"));
                medidor.setSensorlabel(resultSet.getString( "sensoridfk" ) );
                medidor.setNivelCO( resultSet.getFloat( "NivelCO" ) );
                medidor.setNivelCO2( resultSet.getFloat( "NivelCO2" ) );
                medidor.setNivelMetano( resultSet.getFloat( "NivelMetano" ) );
                medidor.setTemperature( resultSet.getFloat( "Temperatura" ) );
                medidor.setHumedad( resultSet.getFloat( "Humedad" ) );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return medidor;
    }
/*    
    @Override
    public void updateSensor( Medidor sensor ) {
        try {
            String query = "update Sensor_Values set latitud=?, longitud=? where idsensor_ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, sensor.getLatitud() );
            preparedStatement.setString( 2, student.getLongitud() );
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 */
    @Override
    public List<SensorData> getAllSensors() {
        List<SensorData> listaSensores = new ArrayList<SensorData>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from sensorid" );
            while( resultSet.next() ) {
                SensorData unSensorData = new SensorData();
                unSensorData.setId( resultSet.getString( "idsensor_ID" ) );
                unSensorData.setLatitud( resultSet.getFloat( "Latitud" ) );
                unSensorData.setLongitud( resultSet.getFloat( "Longitud" ) );
                listaSensores.add(unSensorData);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaSensores;
    }
    
}