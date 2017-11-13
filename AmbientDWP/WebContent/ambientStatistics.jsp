<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.ambient.model.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/AmbientTmp.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>AmbientWeb</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" type="text/css" />
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {'packages': ['corechart']});
	google.charts.setOnLoadCallback(drawSort);
	
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Lectura');
	data.addColumn('number', 'CO');
	data.addColumn('number', 'CO2');
	data.addColumn('number', 'Metano');
	
	function addValues(String lectura, float nivelCO, float nivelCO2, float metano) {
		
	}
	
	function drawSort() {
		 var data = google.visualization.arrayToDataTable([
		                                                   ['Lecturas', 'CO', 'CO2', 'Metano'],
		                                                   ['1',  1000,      400, 1000],
		                                                   ['2',  1170,      460, 1100],
		                                                   ['3',  660,       1120, 900],
		                                                   ['4',  1030,      540, 2000]
		                                                 ]);
		
		
		 var options = {
		          title: 'Lecturas de Hoy',
		          curveType: 'function',
		          legend: { position: 'bottom' }
		        };
		 
		 var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

	        chart.draw(data, options);
	}

</script>
</head>

<body bgcolor="#66CC99">
<%
String sensorActual = (String) request.getAttribute("sensorActual");
Medidor unMedidor = null;
unMedidor = (Medidor) request.getAttribute("unSensor");
String enlaceday, enlacemonth, enlaceyear;
enlaceday = "\"AmbientServlet?action=viewMedidor&period=day&param=" + unMedidor.getSensorlabel() +"\"";
enlacemonth = "\"AmbientServlet?action=viewMedidor&period=month&param=" + unMedidor.getSensorlabel() +"\"";
enlaceyear = "\"AmbientServlet?action=viewMedidor&period=year&param=" + unMedidor.getSensorlabel() +"\"";
%>
<div class="container">
  <div class="header"><a href="#"></a>
  <ul>
	  <li><a href="AmbientServlet?action=inicio">INICIO</a></li>
	  <li><a href="AmbientServlet?action=admin">ADMINISTRACION</a></li>
	  <li class="dropdown">
	   <a class="active" href="javascript:void(0)" class="dropbtn">ESTADISTICA</a>
	    <div class="dropdown-content">
	      <a href=<%=enlaceday%>>DIA EN CURSO</a>
	      <a href=<%=enlacemonth%>>MES EN CURSO</a>
	      <a href=<%=enlaceyear%>>AÑO EN CURSO</a>
	    </div>
	  </li>
	  <li><a href="AmbientServlet?action=contact">CONTACTO</a></li>
	  <li style="float:right"><a href="#about">LOGOUT</a></li>
	</ul>
	<!-- 
    <table width="961" border="1">
      <tr bgcolor="#66CC99">
        <th width="107"><div align="center"><a href="AmbientServlet?action=inicio">INICIO</a></div></th>
        <th width="294"><div align="center"><a href="AmbientServlet?action=geoloc">GEOLOCALIZACION</a></div></th>
        <th width="153"><div align="center"><a href="AmbientServlet?action=statistics">ESTADISTICAS</a></div></th>
        <th width="260"><div align="center"><a href="AmbientServlet?action=admin">ADMINISTRACION</a></div></th>
        <th width="113"><div align="center"><a href="AmbientServlet?action=contact">CONTACTO</a></div></th>
      </tr>
    </table> 
-->    
    <!-- end .header --></div>
  <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
    <h1>&nbsp;</h1>
    <table align="center">
<%
	List<Medidor> listaSensores = null;
    listaLecturas = (List<Medidor>) request.getAttribute("listCharts");
            if (listaLecturas != null) {
	        	Iterator it = listaLecturas.iterator();
	        	Medidor unMedidor = null;
				String nuevoId = "";
	 			int contSensors = 0;
	 			System.out.println("Antes de recorrer la lista");
		    	while (it.hasNext()){
		    		
		    	}
            }
%>
      <tr valign="top">
        <td>
          <div id="curve_chart" style="align: center; width: 700px; height: 300px;"></div>
        </td>
      </tr>
    </table>
  <!-- InstanceEndEditable --><!-- end .content --></div>
 
<!-- end .container --></div>
</body>
<!-- InstanceEnd --></html>
