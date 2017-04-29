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
import com.ambient.util.dbutil;
 
public class SensorDAOImplementation implements SensorDAO {
 
    private Connection conn;
 
    public SensorDAOImplementation() {
        conn = dbutil.getConnection();
    }
    
    @Override
    public void addSensor( Medidor sensor ) {
    	 	
        try {	
        	
            String query = "insert into Sensor_Values (NivelCO, NivelCO2, NivelMetano, Temperatura, Humedad) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setFloat( 1, sensor.getNivelCO() );
            preparedStatement.setFloat( 2, sensor.getNivelCO2() );
            preparedStatement.setFloat( 3, sensor.getNivelMetano() );
            preparedStatement.setFloat( 4, sensor.getTemperature() );
            preparedStatement.setFloat( 5, sensor.getHumedad() );
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteSensor( int sensorId ) {
        try {
            String query = "delete from Sensor_Values where idSensor_Values=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sensorId);
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
            String query = "select * from sensor_values where idSensor_ID_Value=? orderby Lectura DESC limit 1";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString(1, sensorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                medidor.setId( resultSet.getString( "idSensor_ID_Value" ) );
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
            String query = "update Sensor_Values set firstName=?, lastName=?, course=?, year=? where studentId=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, student.getFirstName() );
            preparedStatement.setString( 2, student.getLastName() );
            preparedStatement.setString( 3, student.getCourse() );
            preparedStatement.setInt( 4, student.getYear() );
            preparedStatement.setInt(5, student.getStudentId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from student" );
            while( resultSet.next() ) {
                Student student = new Student();
                student.setStudentId( resultSet.getInt( "studentId" ) );
                student.setFirstName( resultSet.getString( "firstName" ) );
                student.setLastName( resultSet.getString( "lastName" ) );
                student.setCourse( resultSet.getString( "course" ) );
                student.setYear( resultSet.getInt( "year" ) );
                students.add(student);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
 */
}