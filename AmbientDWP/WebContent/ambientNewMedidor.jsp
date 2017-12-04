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
<script type="text/javascript">
	function validar() {
		
		var datosOK = true;
		var newlat = parseFloat(document.getElementById("newLatitud").value);
		var newlon = parseFloat(document.getElementById("newLongitud").value);
		
		
		if (!((newlat >= -90) && (newlat <= 90))) {
			datosOK = false;
			alert('El campo Latitud no es válido');
		}
		
		if (!(( newlon >= -180) &&  (newlon <= 180))) {
			datosOK = false;
			alert('El campo Longitud no es válido');
		}
		
		return datosOK;
	}
</script>
</head>

<body>

<div class="container">

  <div class="header"><a href="#"></a>
  	<ul>
  	 	<li><a href="AmbientServlet?action=inicio">INICIO</a></li>
	  	<li><a class="active" href="AmbientServlet?action=admin">ADMINISTRACION</a></li>
	  	<li style="float:right"><a href="AmbientServlet?action=logout">LOGOUT</a></li>
	 </ul>
  
    
    <!-- end .header --></div>
  <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
    <h2 align="center" class="header"><strong>Administración</strong></h2>
    <div align="center"></div>
    <p>&nbsp;</p>
    <div align="center">
      <form id="formAddSensor" name="formAddSensor" method="post" action="AmbientServlet" 
      	onsubmit="return validar()">
        <table width="289" border="1" cellpadding="1">
          <tr>
            <td colspan="2"><div align="center">VALORES NUEVO MEDIDOR</div></td>
          </tr>
          <tr>
            <td width="92"><label>
              <div align="center">Identificador</div>
            </label></td>
            <td width="181"><div align="center">
              <input name="newId" type="text" id="newId" maxlength="20" />
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Latitud</div>
            </label></td>
            <td><div align="center">
              <input name="newLatitud" type="text" id="newLatitud" maxlength="12" />
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Longitud</div>
            </label></td>
            <td><div align="center">
              <input name="newLongitud" type="text" id="newLongitud" maxlength="12" />
            </div></td>
          </tr>
          <tr>
            <td><div align="center">
              <input type="submit" name="accept" id="accept" value="Aceptar" />
            </div></td>
            <td><div align="center">
              <input type="reset" name="cancel" id="cancel" value="Cancelar" />
            </div></td>
          </tr>
        </table>
      </form>
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
