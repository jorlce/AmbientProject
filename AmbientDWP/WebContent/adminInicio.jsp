<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.ambient.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="../../../../Documents/AmbientProject/Templates/AmbientTmp.dwt.jsp" codeOutsideHTMLIsLocked="false" -->

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
LoginCredential userActual = (LoginCredential) session.getAttribute("userActivo");
if (userActual == null) {
	System.out.println("Usuario no Encontrado");
%>
<jsp:include page = "errorLogin.jsp"/>
<%
} else {
	System.out.println("Usuario Encontrado");
%>
<div class="container">
  <div class="header"><a href="#"></a>
  	 <ul>
  	 	<li><a class="active" href="#">INICIO</a></li>
	  	<li><a href="AmbientServlet?action=admin">ADMINISTRACION</a></li>
	  	<li><a href="AmbientServlet?action=contact">CONTACTO</a></li>
	  	<li style="float:right"><a href="AmbientServlet?action=logout">LOGOUT</a></li>
	 </ul>
     <!-- end .header --></div>
  <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
    <h1>&nbsp;</h1>
    <div ice:editable="*">
      <h2 align="center"><strong><font color="#0066FF">Bienvenido <%=userActual.getLogin() %></font></strong></h2>
      <h1>&nbsp;</h1>
      <h2 align="center"><strong><font color="#0066FF">Esta es la página de Inicio del Proyecto Ambiant Sensors</font></strong></h2>
    </div>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  <!-- InstanceEndEditable --><!-- end .content --></div>
  <div class="footer">
    <p>Footer</p>
    <!-- end .footer --></div>
<!-- end .container --></div>
<%}%>
</body>
<!-- InstanceEnd --></html>
