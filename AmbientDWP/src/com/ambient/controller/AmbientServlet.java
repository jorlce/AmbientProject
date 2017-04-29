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
    
	// Datos Miembro (1 sola instancia)
	HttpSession miSesion;
	String paginaForward = "";
	String paginaDestino = "";
	
	// Constantes para Pistas y Nombres de Paginas JSP de la aplicacion
	String PAGINA_ERROR = "/errores.jsp";
	String PAGINA_ESTADISTICAS = "/ambientStatistics.jsp";
	String PAGINA_CONSULTAS ="/ambientDatosMedidor.jsp";
	String PAGINA_GEOLOCALIZACION = "/ambientGeoLoc.jsp";
	String PAGINA_NUEVOSENSOR = "/ambientNewMedidor.jsp";
	String PAGINA_ADMIN_INICIO = "/adminInicio.jsp";
	String PAGINA_CONTACTO = "ambientContacto.jsp";
	//String PAGINA_CUENTAS = "/lstCuentas.jsp";
	/*String PAGINA_OBRAS = "/temporal.jsp";
	String PAGINA_OPERACIONES = "/operaciones.jsp";
	String PAGINA_LOGIN = "/login.jsp";
	String PAGINA_CLIENTES = "/clientes.jsp";
	String PAGINA_CUENTAS = "/cuentas.jsp";
	String PAGINA_SALUDO = "/saludo.jsp";*/
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmbientServlet() {
        super();
        //dao = new SensorDAOImplementation();
    }

    
/*    public void service(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException,IOException{ 	
    		// Declaraciones locales
    		
    	}*/
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
		/*
		else if (request.getParameter("ahref").equals("login")){
			System.out.println("Pagina de Login");
			miSesion.invalidate();
			paginaForward = PAGINA_LOGIN;
			
			//paginaForward = "/principal.jsp";
		}*/
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
			//paginaForward = SERVLET_OPERACIONES;
			System.out.println(paginaForward);
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
		// Hacer forward a la JSP Conveniente 
		//request.setAttribute("Destino",paginaDestino);
		rd = request.getRequestDispatcher(paginaForward);
		rd.forward(request,response);
		 /*  String forward = "";
        String action = request.getParameter( "action" );
 
        if( action.equalsIgnoreCase( "delete" ) ) {
            forward = lIST_STUDENT;
            int studentId = Integer.parseInt( request.getParameter("studentId") );
            dao.deleteStudent(studentId);
            request.setAttribute("students", dao.getAllStudents() );
        }
        else if( action.equalsIgnoreCase( "edit" ) ) {
            forward = INSERT_OR_EDIT;
            int studentId = Integer.parseInt( request.getParameter("studentId") );
            Student student = dao.getStudentById(studentId);
            request.setAttribute("student", student);
        }
        else if( action.equalsIgnoreCase( "insert" ) ) {
            forward = INSERT_OR_EDIT;
        }
        else {
            forward = lIST_STUDENT;
            request.setAttribute("students", dao.getAllStudents() );
        }
        */
        RequestDispatcher view = request.getRequestDispatcher( paginaForward );
        view.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward= "";
		String action = request.getParameter("action");
		String sensorId = "";
		
		if (action.equalsIgnoreCase("FetchSensor"))
		{
			Medidor unMedidor = new Medidor();
			sensorId = request.getParameter("sensorFind");
			dao.getSensorById(sensorId, unMedidor);
			request.setAttribute("unSensor", unMedidor);
			forward = PAGINA_CONSULTAS;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		} else if (action.equalsIgnoreCase("addSensor")) {
			float longitud, latitud;
		
			SensorData unSensorData = new SensorData();
			unSensorData.setId(request.getParameter("newId"));
			latitud = Float.parseFloat(request.getParameter("newLatitud"));
			unSensorData.setLatitud(latitud);
			longitud = Float.parseFloat(request.getParameter("newLongitud"));
			unSensorData.setLongitud(longitud);
			forward = PAGINA_ADMIN_INICIO;
			
		       /* Student student = new Student();
		        student.setFirstName( request.getParameter( "firstName" ) );
		        student.setLastName( request.getParameter( "lastName" ) );
		        student.setCourse( request.getParameter( "course" ) );
		        student.setYear( Integer.parseInt( request.getParameter( "year" ) ) );
		        String studentId = request.getParameter("studentId");
		 
		        if( studentId == null || studentId.isEmpty() )
		            dao.addStudent(student);
		        else {
		            student.setStudentId( Integer.parseInt(studentId) );
		            dao.updateStudent(student);
		        }
		        RequestDispatcher view = request.getRequestDispatcher( lIST_STUDENT );
		        request.setAttribute("students", dao.getAllStudents());
		        view.forward(request, response); */
		}

	}
}