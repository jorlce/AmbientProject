package com.ambient.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ambient.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.core.type.TypeReference;


public class SensorJSONImplementation implements SensorJSON {
	
	private ObjectMapper mapper;
	
	public SensorJSONImplementation() {
		System.out.println("Create object SensorJSON");
	}
	
	public SensorData findBySensorLabel(String unSensorJson) {
		ObjectMapper mapper = new ObjectMapper();
		SensorData unSensor = null;
		try {
			unSensor = mapper.readValue(unSensorJson, SensorData.class);
			System.out.println("1. Convert JSON to SensorData:");
	    	System.out.println(unSensor.getSensorlabel());
	    	System.out.println(" - ");
	    	System.out.println(unSensor.getLatitud());
	    	System.out.println(" - ");
	    	System.out.println(unSensor.getLongitud());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return unSensor;
	}
	
	//Creates a new sensor in the database
	public String addSensorData(SensorData unsensor) {
		ObjectMapper mapper = new ObjectMapper();
		String unSensorJson = "";
		try {
			unSensorJson = mapper.writeValueAsString(unsensor);
			System.out.println("1. Convert SensorData to JSON:");
	    	System.out.println(unSensorJson);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return unSensorJson;
	}
	
	//Returns the measure data of the sensor from Json sent by the service
/*	public Medidor findSensorMeasure(String measureJson) {
		ObjectMapper mapper = new ObjectMapper();
		Medidor unMeasure = null;
		try {
			//unMeasure = measureRepository.findTopByOrderByTimelecturaDesc(idUnse);
			unMeasure = mapper.readValue(measureJson, Medidor.class);
			//jsonSensor = mapper.writerWithView(ViewsJson.Normal.class).writeValueAsString(unMeasure);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return unMeasure;
	}*/
	
	public List<SensorData> listaSensores(String arraySensorJson) {
		System.out.println("Dentro de Jdao");
		System.out.println(arraySensorJson);
		ObjectMapper mapper = new ObjectMapper();
		List<SensorData> listaSensores = null;
		try {
			TypeReference<List<SensorData>> mapType = new TypeReference<List<SensorData>>() {};
			System.out.println("Antes del mapping");
			listaSensores = mapper.readValue(arraySensorJson, mapType);	
			System.out.println("Después del mapping");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaSensores;
	}
	
	private String TimeLecturatoString(long lectura) {
	//Returns the Timestamp in format ('dd MM YY hh:mm')
			LocalDateTime localDateTime = new LocalDateTime(lectura);
			DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
		    String sqlTimeString = fmt.print(localDateTime);
		    System.out.println(sqlTimeString);
			//Date trueDate = localDateTime.toDate(DateTimeZone.UTC.toTimeZone());
			return sqlTimeString;
		}
	
	public List<Medidor> listaLecturas(String arraySensorJson) {
		ObjectMapper mapper = new ObjectMapper();
		List<Medidor> listaSensores = new ArrayList<Medidor>();
		//SensorData unSensor = new SensorData();
		
		
		try {
			JsonNode rootList = mapper.readTree(arraySensorJson);
			
			for(JsonNode root : rootList) {
				Medidor unMedidor = new Medidor();
				SensorData unSensor = new SensorData();
		
				// Get measure values
				unMedidor.setTimelectura(root.path("timelectura").longValue());
				unMedidor.setTemperatura(root.path("temperatura").floatValue()); 
				unMedidor.setHumedad(root.path("humedad").floatValue()); 
				unMedidor.setNivelCO(root.path("nivelCO").floatValue()); 
				unMedidor.setNivelCO2(root.path("nivelCO2").floatValue()); 
				unMedidor.setNivelMetano(root.path("metano").floatValue()); 
		
		
				//System.out.println("id : ");
	
				// Get Sensor values
				JsonNode sensorNode = root.path("sensorID");
				if (sensorNode.isMissingNode()) {
					unMedidor.setSensorlabel("ERROR");
				} else {
					unSensor.setId(sensorNode.path("sensorlabel").textValue());
					unSensor.setLatitud(sensorNode.path("latitud").floatValue());
					unSensor.setLongitud(sensorNode.path("longitud").floatValue());
					unSensor.setFrecuencia(sensorNode.path("frecuencia").intValue());
					//unMedidor.setSensorlabel(sensorNode.path("sensorlabel").textValue());
					/*unSensor.setId(unMedidor.getSensorlabel());
					unSensor.setLatitud(sensorNode.path("latitud").floatValue());
					unSensor.setLongitud(sensorNode.path("longitud").floatValue());*/
					unMedidor.setSensorMedido(unSensor);
				}
			
				listaSensores.add(unMedidor);
			}

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaSensores;
	}
	
	public JsonNode entreFechasMeasure(String arraySensorJson) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectMapper mapperChart = new ObjectMapper();
		JsonNode rootChart = mapperChart.createObjectNode();
		String jsonChart = "";
		
		try {
			JsonNode rootArray = mapper.readTree(arraySensorJson);
			
			JsonNode arrayCols = mapperChart.createArrayNode();
			JsonNode rootCols = mapperChart.createObjectNode();
			
			((ObjectNode) rootCols).put("id", "Lecturas");
			((ObjectNode) rootCols).put("label", "Lecturas");
			((ObjectNode) rootCols).put("type", "string");			
			((ArrayNode)arrayCols).add(rootCols);
			
			JsonNode rootCols1 = mapperChart.createObjectNode();
			
			((ObjectNode) rootCols1).put("id", "CO");
			((ObjectNode) rootCols1).put("label", "CO");
			((ObjectNode) rootCols1).put("type", "number");		
			((ArrayNode)arrayCols).add(rootCols1);
			
			JsonNode rootCols2 = mapperChart.createObjectNode();
			
			((ObjectNode) rootCols2).put("id", "CO2");
			((ObjectNode) rootCols2).put("label", "CO2");
			((ObjectNode) rootCols2).put("type", "number");
			((ArrayNode)arrayCols).add(rootCols2);
			
			JsonNode rootCols3 = mapperChart.createObjectNode();
			
			((ObjectNode) rootCols3).put("id", "Metano");
			((ObjectNode) rootCols3).put("label", "Metano");
			((ObjectNode) rootCols3).put("type", "number");	
			
			((ArrayNode)arrayCols).add(rootCols3);
			((ObjectNode) rootChart).set("cols",arrayCols);
		
			JsonNode rootArrayRows = mapperChart.createArrayNode();
			for(JsonNode root : rootArray) {
				JsonNode arrayRows = mapperChart.createArrayNode();
				JsonNode rootRows = mapperChart.createObjectNode();
				JsonNode rootCells = mapperChart.createObjectNode();
				JsonNode rootCells1 = mapperChart.createObjectNode();
				JsonNode rootCells2 = mapperChart.createObjectNode();
				JsonNode rootCells3 = mapperChart.createObjectNode();
				
				String lectura = TimeLecturatoString(root.path("timelectura").longValue());
				
				((ObjectNode) rootCells).put("v", lectura);
				//((ObjectNode) rootCells).put("f", "2/28/08 12:31 AM");
				((ArrayNode) arrayRows).add(rootCells);
				((ObjectNode) rootCells1).put("v", root.path("nivelCO").floatValue());
				((ObjectNode) rootCells1).put("f", "7.00");
				((ArrayNode) arrayRows).add(rootCells1);
				((ObjectNode) rootCells2).put("v", root.path("nivelCO2").floatValue());
				((ObjectNode) rootCells2).put("f", "7.00");
				((ArrayNode) arrayRows).add(rootCells2);
				((ObjectNode) rootCells3).put("v", root.path("metano").floatValue());
				((ObjectNode) rootCells3).put("f", "7.00");
				((ArrayNode) arrayRows).add(rootCells3);
				
				((ObjectNode) rootRows).set("c",arrayRows);
				((ArrayNode) rootArrayRows).add(rootRows);
							}
			((ObjectNode) rootChart).set("rows",rootArrayRows);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*System.out.println("Recibidas lecturas");
		System.out.println(listaSensores.size());
		return listaSensores;*/
		jsonChart = rootChart.toString();
		System.out.println(jsonChart);
		//return jsonChart;
		return rootChart;
	}
	
	public String jsonForChart(List<Medidor> listMedidor){
		ObjectMapper mapper = new ObjectMapper();
		SensorChart senChart = new SensorChart();
		String arrayJson = "";

		 if (listMedidor != null) {
			 		 
			 senChart.getCols().add(new Column("CO","CO","number"));
			 senChart.getCols().add(new Column("CO2","CO2","number"));
			 senChart.getCols().add(new Column("Metano","Metano","number"));
		 
	        Iterator it = listMedidor.iterator();
	        Medidor unMedidor = null;		
		    	
	        while (it.hasNext()){
	        	unMedidor =(Medidor)it.next();
	        	Row row = new Row();
	        	row.getC().add(new Cell(unMedidor.getNivelCO(),"5.00"));
	        	row.getC().add(new Cell(unMedidor.getNivelCO2(),"5.00"));
	        	row.getC().add(new Cell(unMedidor.getNivelMetano(),"5.00"));
	        	
	        	senChart.getRows().add(row);
	        }
	        
	        try {
	        	arrayJson = mapper.writeValueAsString(senChart);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		 } 
		return arrayJson;
	}
	
	//Returns the measure data of the sensor from Json sent by the service
	public Medidor findSensorMeasure(String JsonMeasure ){
		
		ObjectMapper mapper = new ObjectMapper();
		Medidor unMedidor = new Medidor();
		SensorData unSensor = new SensorData();
		
		try {
			JsonNode root = mapper.readTree(JsonMeasure);
			
			    // Get measure values
				unMedidor.setTimelectura(root.path("timelectura").longValue());
				unMedidor.setTemperatura(root.path("temperatura").floatValue()); 
				unMedidor.setHumedad(root.path("humedad").floatValue()); 
				unMedidor.setNivelCO(root.path("nivelCO").floatValue()); 
				unMedidor.setNivelCO2(root.path("nivelCO2").floatValue()); 
				unMedidor.setNivelMetano(root.path("metano").floatValue()); 
				
				
				//System.out.println("id : ");

				// Get Sensor values
				JsonNode sensorNode = root.path("sensorID");
				if (sensorNode.isMissingNode()) {
					// if "name" node is missing
				} else {
					unSensor.setId(sensorNode.path("sensorlabel").textValue());
					//unMedidor.setSensorlabel(sensorNode.path("sensorlabel").textValue());
					//unSensor.setId(unMedidor.getSensorlabel());
					unSensor.setLatitud(sensorNode.path("latitud").floatValue());
					unSensor.setLongitud(sensorNode.path("longitud").floatValue());
					unSensor.setFrecuencia(sensorNode.path("frecuencia").intValue());
					unMedidor.setSensorMedido(unSensor);
				}
			
		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return unMedidor;
	}
}
