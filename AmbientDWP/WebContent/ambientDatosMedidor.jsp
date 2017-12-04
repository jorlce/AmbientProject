<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.ambient.controller.*, com.ambient.model.*,java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/AmbientTmp.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>AmbientWeb</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" type="text/css"/>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->

</head>

<body>
<%
Medidor unMedidor = null;
String enlaceday = ""; String enlacemonth = ""; String enlaceyear = ""; 
String enlace60m = ""; String enlace30m = ""; 
String enlace15m = "";String enlace5m = "";

if (request.getAttribute("errorMessage") == null) {
	
	unMedidor = (Medidor) session.getAttribute("medidorActual");
	//unSensor = (SensorData) session.getAttribute("sensorActual");
	LoginCredential userActual = (LoginCredential) session.getAttribute("userActivo");
	
	System.out.println(unMedidor.getSensorlabel());
	enlaceday = "\"AmbientServlet?action=statistics&period=day&param=" + unMedidor.getSensorlabel() +"\"";
	enlacemonth = "\"AmbientServlet?action=statistics&period=month&param=" + unMedidor.getSensorlabel() +"\"";
	enlaceyear = "\"AmbientServlet?action=statistics&period=year&param=" + unMedidor.getSensorlabel() +"\"";
	enlace60m = "\"AmbientServlet?action=frequency&freq=1&param=" + unMedidor.getSensorlabel() +"\"";
	enlace30m = "\"AmbientServlet?action=frequency&freq=2&param=" + unMedidor.getSensorlabel() +"\"";
	enlace15m = "\"AmbientServlet?action=frequency&freq=3&param=" + unMedidor.getSensorlabel() +"\"";
	enlace5m = "\"AmbientServlet?action=frequency&freq=4&param=" + unMedidor.getSensorlabel() +"\"";
	System.out.println(enlaceday);
	System.out.println(enlacemonth);
	System.out.println(enlaceyear);
	System.out.print("Frecuencia: ");
	System.out.println(unMedidor.getSensorMedido().getFrecuencia());
}
%>
<div class="container">
  <div class="header"><a href="#"></a>
  <ul>
	  <li><a href="AmbientServlet?action=inicio">INICIO</a></li>
	  <li><a class="active" href="AmbientServlet?action=admin">ADMINISTRACION</a></li>
<% if (request.getAttribute("errorMessage") == null) {%>	  
	  	  
	  <li class="dropdown">
	   <a href="javascript:void(0)" class="dropbtn">ESTADISTICA</a>
	    <div class="dropdown-content">
	      <a href=<%=enlaceday%>>DIA EN CURSO</a>
	      <a href=<%=enlacemonth%>>MES EN CURSO</a>
	      <a href=<%=enlaceyear%>>AÑO EN CURSO</a>
	    </div>
	  </li>
	  <li class="dropdown">
	   <a href="javascript:void(0)" class="dropbtn">FRECUENCIA</a>
	    <div class="dropdown-content">
	      <a href=<%=enlace60m%>>
	      	<%if (unMedidor.getSensorMedido().getFrecuencia() == 1) {%>&#10003;<%} %>60 MINUTOS</a>
	      <a href=<%=enlace30m%>>
	      	<%if (unMedidor.getSensorMedido().getFrecuencia() == 2) {%>&#10003;<%} %>30 MINUTOS</a>
	      <a href=<%=enlace15m%>>
	      	<%if (unMedidor.getSensorMedido().getFrecuencia() == 3) {%>&#10003;<%} %>15 MINUTOS</a>
	      <a href=<%=enlace5m%>>
	      	<%if (unMedidor.getSensorMedido().getFrecuencia() == 4) {%>&#10003;<%} %>5 MINUTOS</a>
	    </div>
	  </li>
<%} %>
	  <li style="float:right"><a href="AmbientServlet?action=logout">LOGOUT</a></li>
	</ul>
    
    <!-- end .header --></div>
    <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
    	<h2 align="center" class="header"><strong>Administración</strong></h2>
    	<div align="center"></div>
    	<p>&nbsp;</p>
    	<div align="center">
    	
<% if (request.getAttribute("errorMessage") == null) {
	

	/* Medidor unMedidor = null;
	unMedidor = (Medidor) request.getAttribute("unSensor"); */
	if (unMedidor == null){
		System.out.println("Datos de Sensor vacios en ambientDatosMedidor.jsp");
	%>
		 <table width="418" border="1" cellpadding="1">
		     <tr>
		       <td colspan="2"><div align="center">VALORES  MEDIDOR</div></td>
		     </tr>
		     <tr>
		       <td colspan="2"><div align="center">NO HAY DATOS DEL SENSOR</div></td>
		     </tr>
	      </table>
	<%
	} else {	
		System.out.println("Medidor:");
		System.out.println(unMedidor.getSensorlabel());
		System.out.println(unMedidor.getTemperature());
		System.out.println(unMedidor.getHumedad());
		System.out.println(unMedidor.getNivelCO());
		System.out.println(unMedidor.getNivelCO2());
		System.out.println(unMedidor.getNivelMetano());
%>
      <form id="formProg" name="formProg" method="post" action="AmbientServlet">
        <table width="418" border="1" cellpadding="1" bgcolor="#f1f8e9">
          <tr>
            <td colspan="2"><div align="center">VALORES  SENSOR</div></td>
          </tr>
          <tr>
            <td width="172"><label>
              <div align="center">Identificador</div></label></td>
            <td width="230"><div align="center">
              <%=unMedidor.getSensorlabel() %>
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Temperatura</div></label></td>
            <td><div align="center">
              <%=unMedidor.getTemperature() %>
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Humedad</div></label></td>
            <td><div align="center">
              <%=unMedidor.getHumedad() %>
            </div></td>
          </tr>
          <tr>
            <td><label><div align="center">Nivel CO</div></label></td>
            <td><div align="center">
               <%
               	if ((unMedidor.getNivelCO() > 11) && (unMedidor.getNivelCO() < 30)) { %>
               		<span class="textoWarn">
               <%   
               } else if (unMedidor.getNivelCO() >= 30) { %>
            	   <span class="textoAlarm">
               <%   
               } else {%>
            	   <span class="textoOK">
                   
               <%} %>
              <%=unMedidor.getNivelCO() %></span>
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Nivel CO2</div></label></td>
            <td><div align="center">
            	<%
               	if ((unMedidor.getNivelCO2() > 1500) && (unMedidor.getNivelCO2() < 30000)) { %>
               		<span class="textoWarn">
               <%   
               } else if (unMedidor.getNivelCO2() >= 30000) { %>
            	   <span class="textoAlarm">
               <%   
               } else {%>
            	   <span class="textoOK">
                   
               <%} %>
              <%=unMedidor.getNivelCO2() %></span>
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Nivel Metano</div></label></td>
            <td><div align="center">
           		 <%
               	if ((unMedidor.getNivelMetano() > 5000) && (unMedidor.getNivelMetano() < 10000)) { %>
               		<span class="textoWarn">
               <%   
               } else if (unMedidor.getNivelMetano() >= 10000) { %>
            	   <span class="textoAlarm">
               <%   
               } else {%>
            	   <span class="textoOK">
                   
               <%} %>
              <%=unMedidor.getNivelMetano() %></span>
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Ultima Lectura</div></label></td>
            <td><div align="center">
              <%=unMedidor.getTimelectura() %>
            </div></td>
          </tr>
         <!--  <tr>
            <td colspan="2"><div align="center">
              <input type="button" name="accept" id="accept" value="Programar" />
            </div></td>
          </tr> -->
        </table>
      </form>
<%	}
} else {%>
	<h2><span class="textoAlarm"><% out.println(request.getAttribute("errorMessage"));%></h2>
<%
}
	String volver="AmbientServlet?action=consultMedidor";
%>
	<a href="<%=volver%>" align="center">&raquo; VOLVER &laquo;</a>
    </div>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <div align="center"></div>
    <p>&nbsp;</p>
  <!-- InstanceEndEditable --><!-- end .content --></div>

<!-- end .container --></div>
</body>
<!-- InstanceEnd --></html>
