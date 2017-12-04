package com.ambient.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.ambient.model.*;
import com.ambient.json.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class AmbientServlet
 */

@WebServlet("/AmbientServlet")
public class AmbientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SensorJSON jdao;
    
	// Member data (1 instance only)
	HttpSession session;
	String paginaForward = "";
	String paginaDestino = "";
	
	//EndPoints
	String ENDPOINT_LOCALIZA_SENSOR = "/getSensor"; 
	String ENDPOINT_CONSULTA_SENSOR = "/consultSensor/";
	String ENDPOINT_LISTA_SENSORES = "/listSensors";
	String ENDPOINT_ALTA_SENSOR = "/addSensor";
	String ENDPOINT_BAJA_SENSOR = "/deleteSensor/";
	String ENDPOINT_LOGIN = "/login/";
	String ENDPOINT_ESTADISTICA = "/chart/";
	String ENDPOINT_FRECUENCIA = "/cambiafreq";
	
	//Base URL for MicroService
	String BASE_URL = "http://jorlce.ddns.net:8080/ambientService";
	
	// Names and clues for JSP pages
	String PAGINA_ERROR = "/errores.jsp";
	String PAGINA_ESTADISTICAS = "/ambientStatistics.jsp";
	String PAGINA_CONSULTAS ="/ambientDatosMedidor.jsp";
	String PAGINA_NUEVOSENSOR = "/ambientNewMedidor.jsp";
	String PAGINA_ADMIN_INICIO = "/adminInicio.jsp";
	String PAGINA_OPERACIONES = "/ambientAdmin.jsp";
	String PAGINA_NUEVO_SENSOR = "/ambientNewMedidor.jsp";
	String PAGINA_BAJA_SENSOR = "/ambientDelMedidor.jsp";
	String PAGINA_CONSULTA_SENSOR ="/ambientDatosMedidor.jsp";
	String PAGINA_LISTA_SENSORES ="/ambientListaSensores.jsp";
	String PAGINA_LOGIN ="/adminLogin.jsp";
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmbientServlet() {
        //super();
        //dao = new SensorDAOImplementation();
        jdao = new SensorJSONImplementation();
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		String action = request.getParameter("action");
		String endPoint = "";
		String resEndPoint = "";
		
		RequestDispatcher rd;
		//miSesion=request.getSession(true);
		String busqueda;
	
		System.out.println(request.getParameter("ahref"));
		System.out.println(action);
		
		if (action.equalsIgnoreCase("inicio")) {
			System.out.println("Primera vez");
			paginaForward = PAGINA_ADMIN_INICIO;
		}
		else if (action.equalsIgnoreCase("admin")){
			//System.out.println(request.getAttribute("Destino"));
			System.out.println("Pagina de Operaciones");
			session.removeAttribute("medidorActual");
			//session.removeAttribute("listSensors");
			paginaForward = PAGINA_OPERACIONES;
			System.out.println(paginaForward);
		}
		else if(action.equalsIgnoreCase("newMedidor")){
			System.out.println("Pagina Crear un Sensor");
			paginaForward = PAGINA_NUEVO_SENSOR;
		}
		else if (action.equalsIgnoreCase("viewMedidor")){ 
			System.out.println("Pagina de Ver Datos Sensor");
			Medidor unMedidor = null;
			if ((session.getAttribute("medidorActual") == null)) {			
				endPoint = BASE_URL + ENDPOINT_CONSULTA_SENSOR +  (String) request.getParameter("param");
				resEndPoint = callEndPoint2("GET", "", endPoint);
				if ((resEndPoint.length()>0) && (!resEndPoint.equalsIgnoreCase("EMPTY"))) {
					unMedidor = jdao.findSensorMeasure(resEndPoint);
					//unMedidor.setSensorlabel((String) request.getParameter("param"));
					if (unMedidor != null) {
						System.out.println("Medidor:");
						System.out.println(unMedidor.getSensorMedido().getSensorlabel());
						System.out.println(unMedidor.getTemperature());
						System.out.println(unMedidor.getHumedad());
						System.out.println(unMedidor.getNivelCO());
						System.out.println(unMedidor.getNivelCO2());
						System.out.println(unMedidor.getNivelMetano());
						System.out.println(unMedidor.getTimelectura());
					}
					//request.setAttribute("unSensor", unMedidor);
					session.setAttribute("medidorActual", unMedidor);
				} else {
					System.out.println("No hay datos de este Sensor");
	
					request.setAttribute("errorMessage", "No hay datos de este Sensor");
				}
			}
			paginaForward = PAGINA_CONSULTA_SENSOR;
			
		} else if (action.equalsIgnoreCase("statistics")){
			System.out.println("Pagina para Ver Estadísticas Sensor");
			List<Medidor> listMedidor = null;
			String sensorActual = (String) request.getParameter("param");
			endPoint = BASE_URL + ENDPOINT_ESTADISTICA +  (String) request.getParameter("param") 
					+ "/" + (String) request.getParameter("period");
			resEndPoint = callEndPoint2("GET", "", endPoint);
			listMedidor = jdao.listaLecturas(resEndPoint);
			//String arrayJson = jdao.jsonForChart(listMedidor);
			//JsonNode rootArray = jdao.entreFechasMeasure(resEndPoint);
			//String arrayJson = jdao.entreFechasMeasure(resEndPoint);
			//request.setAttribute("listCharts", rootArray);
			request.setAttribute("listCharts", listMedidor);
			request.setAttribute("sensorActual", sensorActual);
			//System.out.println(listMedidor);
			paginaForward = PAGINA_ESTADISTICAS;
		} 
		else if (action.equalsIgnoreCase("frequency")){
			String sensorActual = (String) request.getParameter("param");
			
			//SensorData unSensor = (SensorData) request.getAttribute("sensorActual");
			
			endPoint = BASE_URL + ENDPOINT_FRECUENCIA;
			Medidor unMedidor = (Medidor) session.getAttribute("medidorActual");
			if (unMedidor != null) {
				String freq = (String) request.getParameter("freq");
				unMedidor.getSensorMedido().setFrecuencia(Integer.parseInt(freq));
				callEndPoint2("POST",jdao.addSensorData(unMedidor.getSensorMedido()), endPoint); 
				session.setAttribute("medidorActual", unMedidor);
				paginaForward = PAGINA_OPERACIONES;
			}
			/*List<SensorData> listaSensores = (List<SensorData>) session.getAttribute("listSensors");
			 if (listaSensores != null) {
		        	Iterator it = listaSensores.iterator();
		        	
					while ((it.hasNext()) && (!inLista)){
						unSensor =(SensorData)it.next();						
			 	  		if (sensorActual.equals(unSensor.getSensorlabel())) {
			 	  			inLista = true;
			 	  			String freq = (String) request.getParameter("freq");
							unSensor.setFrecuencia(Integer.parseInt(freq));
							callEndPoint2("POST",jdao.addSensorData(unSensor), endPoint); 
							paginaForward = PAGINA_OPERACIONES;
			 	  		}
					}
			}*/
		}
		else if (action.equalsIgnoreCase("removeMedidor")){ //Show list of Sensors before deleting
			System.out.println("Pagina de Lista Sensores");
			endPoint = BASE_URL + ENDPOINT_LISTA_SENSORES;
			resEndPoint = callEndPoint2("GET", "", endPoint);
			List<SensorData> listSensors = null;
			if (resEndPoint.length() > 0) {
				System.out.println("Mapping the result");
				
				listSensors = jdao.listaSensores(resEndPoint);
				request.setAttribute("listSensors", listSensors);
				Iterator it = listSensors.iterator();
	        	SensorData nuevoSensor = null;
			
	 			
		    	while (it.hasNext()){
		 	  		nuevoSensor =(SensorData)it.next();
		 	  		System.out.println("Sensor: ");
		 	  		System.out.println(nuevoSensor.getSensorlabel());
		 	  		System.out.println("Latitud: ");
		 	  		System.out.println(nuevoSensor.getLatitud());
		 	  		System.out.println("Longtud: ");
		 	  		System.out.println(nuevoSensor.getLongitud());
		    	}
					
			}
			//Medidor unMedidor = new Medidor();
			//callEndPoint(request,"consultMedidor");
			
			//request.setAttribute("unSensor", unMedidor);
			paginaForward = PAGINA_BAJA_SENSOR;
		}
		else if (action.equalsIgnoreCase("delMedidor")) { // Delete a given Sensor
			
			endPoint = BASE_URL + ENDPOINT_BAJA_SENSOR +  (String) request.getParameter("param");
			resEndPoint = callEndPoint2("DELETE","", endPoint);
			paginaForward = PAGINA_OPERACIONES;
		}
		else if (action.equalsIgnoreCase("consultMedidor")){ //Show list of Sensors for consulting one
			System.out.println("Pagina de Lista Sensores");
			endPoint = BASE_URL + ENDPOINT_LISTA_SENSORES;
			resEndPoint = callEndPoint2("GET", "", endPoint);
			List<SensorData> listSensors = null;
			if (resEndPoint.length() > 0) {
				System.out.println("Mapping the result");
				
				listSensors = jdao.listaSensores(resEndPoint);
				request.setAttribute("listSensors", listSensors);
				//session.setAttribute("listSensors", listSensors);
				Iterator it = listSensors.iterator();
	        	SensorData nuevoSensor = null;
			
	 			
		    	while (it.hasNext()){
		 	  		nuevoSensor =(SensorData)it.next();
		 	  		System.out.println("Sensor: ");
		 	  		System.out.println(nuevoSensor.getSensorlabel());
		 	  		System.out.println("Latitud: ");
		 	  		System.out.println(nuevoSensor.getLatitud());
		 	  		System.out.println("Longtud: ");
		 	  		System.out.println(nuevoSensor.getLongitud());
		    	}
					
			}
			//Medidor unMedidor = new Medidor();
			//callEndPoint(request,"consultMedidor");
			
			//request.setAttribute("unSensor", unMedidor);
			paginaForward = PAGINA_LISTA_SENSORES;
		}
		 else if (action.equalsIgnoreCase("logout")) {
			session.invalidate();
			paginaForward = PAGINA_LOGIN;
		}
		/*
		if ((request.getParameter("usuario")!=null) && 
			(request.getParameter("clave")!=null)) {
				registro(request);
				paginaDestino = PAGINA_SALUDO;
			}*/
		// Forward to the right jps page 
		//request.setAttribute("Destino",paginaDestino);
		rd = request.getRequestDispatcher(paginaForward);
		rd.forward(request,response);
		
        RequestDispatcher view = request.getRequestDispatcher( paginaForward );
        view.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String forward= "";
		//String action = request.getParameter("form");
		String sensorId = "";
		session = request.getSession(true);
		String endPoint = "";
		String resEndPoint = "";
		
		if(request.getParameterMap().containsKey("sensorFind")!=false)  			// Returns a given Sensor
		{
			Medidor unMedidor = new Medidor();
			sensorId = request.getParameter("sensorFind");
			//System.out.println(sensorId);
			//dao.getSensorById(sensorId, unMedidor);
			callEndPointPost("sensorFind",sensorId, request);
			//request.setAttribute("unSensor", unMedidor);
			paginaForward = PAGINA_CONSULTAS;
			
		}	else if (request.getParameterMap().containsKey("newId")!=false)  {  // Create a new Sensor
			float longitud, latitud;
		
			SensorData unSensorData = new SensorData();
			unSensorData.setId(request.getParameter("newId"));
			latitud = Float.parseFloat(request.getParameter("newLatitud"));
			unSensorData.setLatitud(latitud);
			longitud = Float.parseFloat(request.getParameter("newLongitud"));
			unSensorData.setLongitud(longitud);
			//dao.addSensor(unSensorData);
			callEndPointPost("newMedidor",jdao.addSensorData(unSensorData), request);
			paginaForward = PAGINA_OPERACIONES;
			
			
		} else if (request.getParameterMap().containsKey("user")!=false) { //Check Login data
			String user, pass;
			LoginCredential userLogged;
			user = request.getParameter("user");
			pass = request.getParameter("pass");
			endPoint = BASE_URL + ENDPOINT_LOGIN +  user + "/" + pass;
			resEndPoint = callEndPoint2("GET", "", endPoint);
			if (!(resEndPoint.equals("ERROR"))) {
				if (resEndPoint.equals("ACCEPTED")) {
					userLogged = new LoginCredential();
					userLogged.setLogin(user);
					session.setAttribute("userActivo", userLogged);
					paginaForward = PAGINA_ADMIN_INICIO;

				} else {
					//session.invalidate();
					request.setAttribute("errorMessage", "Usuario o Contraseñas no válidos");
					paginaForward = PAGINA_LOGIN;
				}

			} else {
				//session.invalidate();
				request.setAttribute("errorMessage", "Sin conexión al Servidor");
				paginaForward = PAGINA_LOGIN;
			}
			
		} 
		else {
			paginaForward = PAGINA_LOGIN;
		}
		RequestDispatcher view = request.getRequestDispatcher(paginaForward);
		view.forward(request, response);

	}
	private String callEndPoint2(String method, String param, String endPoint) {
		String responseString = "";
		String outputString = "";
		
		switch (method) {
			case "GET":
	
				try {
					System.out.println(endPoint);
					URL url = new URL(endPoint);
					URLConnection connection = url.openConnection();
					HttpURLConnection httpConn = (HttpURLConnection)connection;
					//httpConn.setRequestMethod("GET");
					httpConn.setRequestMethod(method);
					httpConn.setRequestProperty("Content-length", "0");
					httpConn.connect();
					
					//Read the response.
					InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
					BufferedReader in = new BufferedReader(isr);
					
					//Write the SOAP message response to a String.
					while ((responseString = in.readLine()) != null) {
					outputString = outputString + responseString;
					}
					httpConn.disconnect();
					
					System.out.println("Respuesta del microservicio");
					System.out.println(outputString);
				} catch (MalformedURLException e) {
			         e.printStackTrace();
			     } catch (ProtocolException e) {
			         e.printStackTrace();
			     } catch (IOException e) {
			         e.printStackTrace();
			     } 
				break;
			case "POST":
				try {
					
					URL url = new URL(endPoint);
					URLConnection connection = url.openConnection();
					HttpURLConnection httpConn = (HttpURLConnection)connection;
					httpConn.setRequestProperty("Content-Length", String.valueOf(param.length()));
					httpConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
					
					httpConn.setRequestMethod("POST");
					
					httpConn.setDoOutput(true);
					httpConn.setDoInput(true);
					OutputStream out = httpConn.getOutputStream();
					
					//Write the content of the request to the outputstream of the HTTP Connection.
					out.write(param.getBytes("UTF-8"));
					out.close();
					//Ready with sending the request.
					//Read the response.
					InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
					BufferedReader in = new BufferedReader(isr);
					 
					//Write the SOAP message response to a String.
					while ((responseString = in.readLine()) != null) {
					outputString = outputString + responseString;
					}
					httpConn.disconnect();
					
				 } catch (MalformedURLException e) {
			         e.printStackTrace();
			     } catch (ProtocolException e) {
			         e.printStackTrace();
			     } catch (IOException e) {
			         e.printStackTrace();
			     }
				break;
			case "DELETE":	
				try {
					System.out.println(endPoint);
					URL url = new URL(endPoint);
					URLConnection connection = url.openConnection();
					HttpURLConnection httpConn = (HttpURLConnection)connection;
					//httpConn.setRequestMethod("GET");
					httpConn.setRequestMethod(method);
					httpConn.setRequestProperty("Content-length", "0");
					httpConn.connect();
					
					//Read the response.
					InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
					BufferedReader in = new BufferedReader(isr);
					
					//Write the SOAP message response to a String.
					while ((responseString = in.readLine()) != null) {
					outputString = outputString + responseString;
					}
					httpConn.disconnect();
					
					System.out.println("Respuesta del microservicio");
					System.out.println(outputString);
				} catch (MalformedURLException e) {
			         e.printStackTrace();
			     } catch (ProtocolException e) {
			         e.printStackTrace();
			     } catch (IOException e) {
			         e.printStackTrace();
			     } 
				break;
		}
		if (outputString.length()>0) {
			return outputString;
		} else return "ERROR";
	}
	
	private void callEndPoint(HttpServletRequest request, String call) {
		String endPoint = "";
 
		//Code to make a webservice HTTP request
		String responseString = "";
		String outputString = "";
		List<SensorData> listSensors = null;
			
		
		switch (call) {
		case "consultMedidor" :
			System.out.println("Antes de llamar al Endpoint");
			endPoint = BASE_URL + ENDPOINT_LISTA_SENSORES;

			try {
				System.out.println(endPoint);
				URL url = new URL(endPoint);
				URLConnection connection = url.openConnection();
				HttpURLConnection httpConn = (HttpURLConnection)connection;
				httpConn.setRequestMethod("GET");
				httpConn.setRequestProperty("Content-length", "0");
				httpConn.connect();
				
				//Read the response.
				InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
				BufferedReader in = new BufferedReader(isr);
				
				//Write the SOAP message response to a String.
				while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
				}
				httpConn.disconnect();
				
				System.out.println("Respuesta del microservicio");
				System.out.println(outputString);
				
				if (outputString.length() > 0) {
					System.out.println("Mapping the result");
					
					listSensors = jdao.listaSensores(outputString);
					request.setAttribute("listSensors", listSensors);
					Iterator it = listSensors.iterator();
		        	SensorData nuevoSensor = null;
				
		 			
			    	while (it.hasNext()){
			 	  		nuevoSensor =(SensorData)it.next();
			 	  		System.out.println("Sensor: ");
			 	  		System.out.println(nuevoSensor.getSensorlabel());
			 	  		System.out.println("Latitud: ");
			 	  		System.out.println(nuevoSensor.getLatitud());
			 	  		System.out.println("Longtud: ");
			 	  		System.out.println(nuevoSensor.getLongitud());
			    	}
						
				}
			 } catch (MalformedURLException e) {
		         e.printStackTrace();
		     } catch (ProtocolException e) {
		         e.printStackTrace();
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
			break;
		case "newMedidor":
			break;
		case "viewMedidor":
			endPoint = BASE_URL + ENDPOINT_CONSULTA_SENSOR +  (String) request.getParameter("param");
			System.out.println(endPoint);
			try {
				URL url = new URL(endPoint);
				URLConnection connection = url.openConnection();
				HttpURLConnection httpConn = (HttpURLConnection)connection;
				httpConn.setRequestMethod("GET");
				httpConn.setRequestProperty("Content-length", "0");
				httpConn.connect();
				
				//Read the response.
				InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
				BufferedReader in = new BufferedReader(isr);
				
				//Write the SOAP message response to a String.
				while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
				}
				httpConn.disconnect();
				
				System.out.println("Respuesta del microservicio");
				System.out.println(outputString);
			 } catch (MalformedURLException e) {
		         e.printStackTrace();
		     } catch (ProtocolException e) {
		         e.printStackTrace();
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
			break;
		}
		
		/*byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		String SOAPAction = "http://litwinconsulting.com/webservices/GetWeather";
		
		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		
		httpConn.setRequestMethod("POST");
		
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();*/
		
		/*// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", SOAPAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();*/
		
		
		 /* client.println("POST /ambientService/uploadSensor HTTP/1.1"); 
	      client.println("Host: 95.23.53.203:8080");
	      client.println("User-Agent: Arduino/unowifi");
	      client.println("Connection: close");
	     
	      client.println("Content-Type:text/plain"); 
	      client.print("Content-Length: "); 
	      client.println(longitudMensaje);
	      //client.println(strlen(cadena));  
	      client.println();
	      client.print(cadena);
	      client.println(cadena2);*/
	      
		/*//Write the content of the request to the outputstream of the HTTP Connection.
		out.write(b);
		out.close();
		//Ready with sending the request.
		 
		//Read the response.
		InputStreamReader isr =
		new InputStreamReader(httpConn.getInputStream());
		BufferedReader in = new BufferedReader(isr);
		 
		//Write the SOAP message response to a String.
		while ((responseString = in.readLine()) != null) {
		outputString = outputString + responseString;
		}
		
		//Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
		Document document = parseXmlFile(outputString);
		NodeList nodeLst = document.getElementsByTagName("GetWeatherResult");
		String weatherResult = nodeLst.item(0).getTextContent();
		System.out.println("Weather: " + weatherResult);
		 
		//Write the SOAP message formatted to the console.
		String formattedSOAPResponse = formatXML(outputString);
		System.out.println(formattedSOAPResponse);
		return weatherResult;*/
		
	}
	
	private void callEndPointPost(String call, String paramJson, HttpServletRequest request) {
		String endPoint = "";
		 
		//Code to make a webservice HTTP request
		String responseString = "";
		String outputString = "";
		
		switch (call) {
		case "newMedidor":
			endPoint = BASE_URL + ENDPOINT_ALTA_SENSOR;
			
			try {
			
				URL url = new URL(endPoint);
				URLConnection connection = url.openConnection();
				HttpURLConnection httpConn = (HttpURLConnection)connection;
				httpConn.setRequestProperty("Content-Length", String.valueOf(paramJson.length()));
				httpConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
				
				httpConn.setRequestMethod("PUT");
				
				httpConn.setDoOutput(true);
				httpConn.setDoInput(true);
				OutputStream out = httpConn.getOutputStream();
				
				//Write the content of the request to the outputstream of the HTTP Connection.
				out.write(paramJson.getBytes("UTF-8"));
				out.close();
				//Ready with sending the request.
				//Read the response.
				InputStreamReader isr =
				new InputStreamReader(httpConn.getInputStream());
				BufferedReader in = new BufferedReader(isr);
				 
				//Write the SOAP message response to a String.
				while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
				}
				httpConn.disconnect();
				
			 } catch (MalformedURLException e) {
		         e.printStackTrace();
		     } catch (ProtocolException e) {
		         e.printStackTrace();
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
			break;
		case "sensorFind":
			endPoint = BASE_URL + ENDPOINT_CONSULTA_SENSOR;
			
			try {
				
				URL url = new URL(endPoint);
				URLConnection connection = url.openConnection();
				HttpURLConnection httpConn = (HttpURLConnection)connection;
				httpConn.setRequestProperty("Content-Length", String.valueOf(paramJson.length()));
				httpConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
				
				httpConn.setRequestMethod("POST");
				
				httpConn.setDoOutput(true);
				httpConn.setDoInput(true);
				OutputStream out = httpConn.getOutputStream();
				
				//Write the content of the request to the outputstream of the HTTP Connection.
				out.write(paramJson.getBytes("UTF-8"));
				out.close();
				//Ready with sending the request.
				//Read the response.
				InputStreamReader isr =
				new InputStreamReader(httpConn.getInputStream());
				BufferedReader in = new BufferedReader(isr);
				 
				//Write the SOAP message response to a String.
				while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
				}
				httpConn.disconnect();
				
			 } catch (MalformedURLException e) {
		         e.printStackTrace();
		     } catch (ProtocolException e) {
		         e.printStackTrace();
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
			break;
		} 
	}
	
}