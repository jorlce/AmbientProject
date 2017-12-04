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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" type="text/css"/>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
<script type="text/javascript">
    var elems = document.getElementsByClassName('confirmacion');
    var confirmIt = function (e) {
        if (!confirm('Van a eliminarse todos los datos y lecturas de este medidor')) e.preventDefault();
    };
    for (var i = 0, l = elems.length; i < l; i++) {
        elems[i].addEventListener('click', confirmIt, false);
    }
</script>
</head>

<body>

<div class="container">
  <div class="header"><a href="#"></a>
  	<ul>
  	 	<li><a  href="AmbientServlet?action=inicio">INICIO</a></li>
	  	<li><a class="active" href="AmbientServlet?action=admin">ADMINISTRACION</a></li>
	  	<li style="float:right"><a href="AmbientServlet?action=logout">LOGOUT</a></li>
	 </ul>
    <!-- end .header --></div>
  <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
  <h2 align="center" class="header"><strong>Administración</strong></h2>
   <div align="center">
      <form id="form1" name="form1" method="post" action="">
        <table width="418" border="1" cellpadding="1" bgcolor="#f1f8e9">
          <tr>
            <td colspan="2"><div align="center">LISTA SENSORES</div></td>
          </tr>
          <% List<SensorData> listaSensores = null;
            listaSensores = (List<SensorData>) request.getAttribute("listSensors");
            if (listaSensores != null) {
	        	Iterator it = listaSensores.iterator();
	        	SensorData nuevoSensor = null;
				String nuevoId = "";
	 			int contSensors = 0;
	 			System.out.println("Antes de recorrer la lista");
		    	while (it.hasNext()){
		 	  		nuevoSensor =(SensorData)it.next();
					nuevoId = nuevoSensor.getSensorlabel();
	      			contSensors++;
	      			String enlace ="\"AmbientServlet?action=delMedidor&param=" + nuevoId +"\"";
	      			System.out.println("Creado el enlace");
	      			System.out.println(nuevoId);
		  %>
          		<tr>
            		<td width="230"><div align="center">
            			<a href=<%=enlace%> onclick="return confirm('Van a eliminarse todos los datos y lecturas de este medidor')">
            			<font color="#0066FF"><%=nuevoId%></font></span></a>
            		</div></td>
          		</tr>
          <% 	} 
            } else {
        	  System.out.println("Session atribute null");
          	}
          %>
        </table>
      </form>
    </div>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <div align="center"></div>
  <!-- InstanceEndEditable --><!-- end .content --></div>
 
<!-- end .container --></div>
</body>
<!-- InstanceEnd --></html>
