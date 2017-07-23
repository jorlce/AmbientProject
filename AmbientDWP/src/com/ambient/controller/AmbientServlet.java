package com.ambient.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ambient.dao.*;
import com.ambient.model.*;


/**
 * Servlet implementation class AmbientServlet
 */
@WebServlet("/AmbientServlet")
public class AmbientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SensorDAO dao;
    
	// Member data (1 instance only)
	HttpSession miSesion;
	String paginaForward = "";
	String paginaDestino = "";
	
	// Names and clues for JSP pages
	String PAGINA_ERROR = "/errores.jsp";
	String PAGINA_ESTADISTICAS = "/ambientStatistics.jsp";
	String PAGINA_CONSULTAS ="/ambientDatosMedidor.jsp";
	String PAGINA_GEOLOCALIZACION = "/ambientGeoLoc.jsp";
	String PAGINA_NUEVOSENSOR = "/ambientNewMedidor.jsp";
	String PAGINA_ADMIN_INICIO = "/adminInicio.jsp";
	String PAGINA_CONTACTO = "ambientContacto.jsp";
	String PAGINA_OPERACIONES = "/ambientAdmin.jsp";
	String PAGINA_NUEVO_SENSOR = "/ambientNewMedidor.jsp";
	String PAGINA_CONSULTA_SENSOR ="/ambientDatosMedidor.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmbientServlet() {
        //super();
        dao = new SensorDAOImplementation();
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		RequestDispatcher rd;
		miSesion=request.getSession(true);
		String busqueda;
	
		System.out.println(request.getParameter("ahref"));
		System.out.println(action);
		
		if (action=="") {
			System.out.println("Primera vez");
			paginaForward = PAGINA_ADMIN_INICIO;
		}
	
		else if (action.equalsIgnoreCase("statistics")){
			System.out.println("Pagina de Administración de Sensores");
			paginaForward = PAGINA_ESTADISTICAS;
		}
		else if (action.equalsIgnoreCase("geoloc")){
			System.out.println("Pagina de Gestion de Cuentas");
			paginaForward = PAGINA_GEOLOCALIZACION;
		}
		else if (action.equalsIgnoreCase("admin")){
			//System.out.println(request.getAttribute("Destino"));
			System.out.println("Pagina de Operaciones");
			paginaForward = PAGINA_OPERACIONES;
			System.out.println(paginaForward);
		}
		else if(action.equalsIgnoreCase("newMedidor")){
			paginaForward = PAGINA_NUEVO_SENSOR;
		}
		else if (action.equalsIgnoreCase("viewMedidor")){
			Medidor unMedidor = new Medidor();
			request.setAttribute("unSensor", unMedidor);
			paginaForward = PAGINA_CONSULTA_SENSOR;
		}
		else if (action.equalsIgnoreCase("contacto")){
			System.out.println("Pagina de Administración de Sensores");
			paginaForward = PAGINA_CONTACTO;
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
		String forward= "";
		//String action = request.getParameter("form");
		String sensorId = "";
		
		if(request.getParameterMap().containsKey("sensorFind")!=false)  			// Return a given Sensor
		{
			Medidor unMedidor = new Medidor();
			sensorId = request.getParameter("sensorFind");
			//System.out.println(sensorId);
			dao.getSensorById(sensorId, unMedidor);
			request.setAttribute("unSensor", unMedidor);
			forward = PAGINA_CONSULTAS;
		} else if (request.getParameterMap().containsKey("newId")!=false)  {  // Create a new Sensor
			float longitud, latitud;
		
			SensorData unSensorData = new SensorData();
			unSensorData.setId(request.getParameter("newId"));
			latitud = Float.parseFloat(request.getParameter("newLatitud"));
			unSensorData.setLatitud(latitud);
			longitud = Float.parseFloat(request.getParameter("newLongitud"));
			unSensorData.setLongitud(longitud);
			dao.addSensor(unSensorData);
			forward = PAGINA_OPERACIONES;
			
			
		} else {
			forward = PAGINA_ADMIN_INICIO;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}
}