<%@ page contentType="text/html; charset=utf-8" language="java"  errorPage="" %>
<%@ page import="com.ambient.model.*" %>
<%@ page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	google.charts.load('current', {'packages': ['corechart', 'line']});
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {
		//var jsonData = document.getElementById("xID").value;
		 var data = new google.visualization.DataTable();
		
		data.addColumn('string', 'Lectura');
		data.addColumn('number', 'CO');
		data.addColumn('number', 'CO2');
		data.addColumn('number', 'Metano');
		
		data.addRows([<c:forEach items="${listCharts}" var="unMedidor">
	                        [ '${unMedidor.timelectura}', ${unMedidor.nivelCO}, ${unMedidor.nivelCO2}, ${unMedidor.nivelMetano} ],
	                    </c:forEach>
	                    ]); 
		
	/* 	var data = google.visualization.arrayToDataTable([
		              ['string', 'Lectura'],['number', 'CO'],['number', 'CO2'],['number', 'Metano'],
		              <c:forEach items="${listCharts}" var="unMedidor">
                      [ '${unMedidor.timelectura}', ${unMedidor.nivelCO}, ${unMedidor.nivelCO2}, ${unMedidor.nivelMetano} ],
                      </c:forEach>
                      ]); */
                      
			var header = '${sensorActual}';
		 	var options = {
				 	title: header,
					hAxis: {title: 'Lecturas'},
					vAxis: {title: 'ppm'},
					backgroundColor: '#f1f8e9'
					  };

		 	var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

	        chart.draw(data, options);
	} 

</script>
</head>
<%
String sensorActual = (String) request.getAttribute("sensorActual");
LoginCredential userActual = (LoginCredential) session.getAttribute("userActivo");
//String jsonChart = (String) request.getAttribute("listCharts");
System.out.println("Dentro jsp Estadistica");
System.out.println(sensorActual);
/* Medidor unMedidor = null;
unMedidor = (Medidor) request.getAttribute("unSensor"); */
String enlaceday, enlacemonth, enlaceyear;
enlaceday = "\"AmbientServlet?action=statistics&period=day&param=" + sensorActual +"\"";
enlacemonth = "\"AmbientServlet?action=statistics&period=month&param=" + sensorActual +"\"";
enlaceyear = "\"AmbientServlet?action=statistics&period=year&param=" + sensorActual +"\"";
%>


<body>
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
	  <li style="float:right"><a href="AmbientServlet?action=logout">LOGOUT</a></li>
	</ul>
    <!-- end .header --></div>
  <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
    <h1>&nbsp;</h1>
    <table align="center">
      <tr valign="top">
        <td>
          <div id="curve_chart" style="align: center; width: 700px; height: 300px;"></div>
        </td>
      </tr>
    </table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
<%
 String volver="AmbientServlet?action=viewMedidor";
%>
 <div align="center">
 	<a href="<%=volver%>" align="center">&raquo; VOLVER &laquo;</a>
 </div>
	
  <!-- InstanceEndEditable --><!-- end .content --></div>
 
<!-- end .container --></div>
</body>
<!-- InstanceEnd --></html>
