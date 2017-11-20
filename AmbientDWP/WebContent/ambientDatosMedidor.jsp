<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.ambient.controller.*,com.ambient.dao.*, com.ambient.model.*,java.util.*" %>
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
<% Medidor unMedidor = null;
unMedidor = (Medidor) request.getAttribute("unSensor");
LoginCredential userActual = (LoginCredential) session.getAttribute("userActivo");
String enlaceday, enlacemonth, enlaceyear;
System.out.println(unMedidor.getSensorlabel());
enlaceday = "\"AmbientServlet?action=statistics&period=day&param=" + unMedidor.getSensorlabel() +"\"";
enlacemonth = "\"AmbientServlet?action=statistics&period=month&param=" + unMedidor.getSensorlabel() +"\"";
enlaceyear = "\"AmbientServlet?action=statistics&period=year&param=" + unMedidor.getSensorlabel() +"\"";
System.out.println(enlaceday);
System.out.println(enlacemonth);
System.out.println(enlaceyear);

%>
<div class="container">
  <div class="header"><a href="#"></a>
  <ul>
	  <li><a href="AmbientServlet?action=inicio">INICIO</a></li>
	  <li><a class="active" href="AmbientServlet?action=admin">ADMINISTRACION</a></li>
	  <li class="dropdown">
	   <a href="javascript:void(0)" class="dropbtn">ESTADISTICA</a>
	    <div class="dropdown-content">
	      <a href=<%=enlaceday%>>DIA EN CURSO</a>
	      <a href=<%=enlacemonth%>>MES EN CURSO</a>
	      <a href=<%=enlaceyear%>>AÑO EN CURSO</a>
	    </div>
	  </li>
	  <li><a href="AmbientServlet?action=contact">CONTACTO</a></li>
	  <li style="float:right"><a href="AmbientServlet?action=logout">LOGOUT</a></li>
	</ul>
   <!--  <table width="961" border="1">
      <tr bgcolor="#66CC99">
        <th width="107"><div align="center"><a href="AmbientServlet?action=inicio">INICIO</a></div></th>
        <th width="294"><div align="center"><a href="AmbientServlet?action=geoloc">GEOLOCALIZACION</a></div></th>
        <th width="153"><div align="center"><a href="AmbientServlet?action=statistics">ESTADISTICAS</a></div></th>
        <th width="260"><div align="center"><a href="AmbientServlet?action=admin">ADMINISTRACION</a></div></th>
        <th width="113"><div align="center"><a href="AmbientServlet?action=contact">CONTACTO</a></div></th>
      </tr>
    </table>  -->
    
    <!-- end .header --></div>
    <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
    	<h2 align="center" class="header"><strong>Administración</strong></h2>
    	<div align="center"></div>
    	<p>&nbsp;</p>
    	<div align="center">
<%
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
	       <td colspan="2"><div align="center">NO HAY DATO DEL SENSOR</div></td>
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
              <%=unMedidor.getNivelCO() %>
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
              <%=unMedidor.getNivelCO2() %>
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
              <%=unMedidor.getNivelMetano() %>
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
<%} 
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
