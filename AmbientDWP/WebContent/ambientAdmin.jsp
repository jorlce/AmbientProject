<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
<!-- InstanceEndEditable -->
</head>

<body>

<div class="container">
  <div class="header"><a href="#"></a>
  	<ul>
  	 	<li><a href="AmbientServlet?action=inicio">INICIO</a></li>
	  	<li><a class="active" href="AmbientServlet?action=admin">ADMINISTRACION</a></li>
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
    <h2 align="center" class="header"><strong>Administración</strong></h2>
    <div align="center">
      <table width="200" border="1" cellpadding="1">
        <tr>
          <td><div align="center"><a href="AmbientServlet?action=newMedidor">
          <font color="#0066FF">Crear Sensor</font></a></div></td>
        </tr>
        <tr>
          <td><div align="center"><a href="AmbientServlet?action=consultMedidor">
          <font color="#0066FF">Consultar Sensor</font></a></div></td>
        </tr>
        <tr>
          <td><div align="center"><a href="AmbientServlet?action=deleteMedidor">
          <font color="#0066FF">Eliminar Sensor</font></a></div></td>
        </tr>
      </table>
    </div>
    <p>&nbsp;</p>
    <h1>&nbsp;</h1>
  <!-- InstanceEndEditable --><!-- end .content --></div>
  
<!-- end .container --></div>
</body>
<!-- InstanceEnd --></html>
