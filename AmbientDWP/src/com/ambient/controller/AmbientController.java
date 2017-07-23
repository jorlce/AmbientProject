package com.ambient.controller;
 
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.ambient.dao.SensorDAO;
import com.ambient.dao.SensorDAOImplementation;
import com.ambient.model.Medidor;
 
@WebServlet("/AmbientController")
public class AmbientController extends HttpServlet {
 
    private SensorDAO dao;
    private static final long serialVersionUID = 1L;

 
    public AmbientController() {
        dao = new SensorDAOImplementation();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    }
}