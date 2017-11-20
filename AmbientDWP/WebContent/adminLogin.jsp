<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="../../../../Documents/AmbientProject/Templates/AmbientTmp.dwt.jsp" codeOutsideHTMLIsLocked="false" -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>AmbientWeb</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css" />
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<script language="JavaScript" type="text/javascript">
function enviar(datos){
	document.formLink.ahref.value=datos;
    document.formLink.submit();
}
</script>
</head>
 
<body bgcolor="#66CC99">

<div class="container">
  <div class="header"><a href="#"></a>
      <table width="961" border="1">
      <tr bgcolor="#66CC99">
        <th width="98%"><div align="center">PROYECTO AMBIANT</div></th>
      </tr>
    </table>     
    <!-- end .header --></div>
  <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
  <center>
	<form name="formlogin" action="AmbientServlet" method="post">
		<h2 align="center" class="header"><strong>IDENTIFICACION DE USUARIO</strong></h2>
		<div align="center">
		<table width="200" border="1" cellpadding="1">
			<tr>
				<td width="55%"><div align="left"><span class="textogranate">USUARIO: </span></div></td>
				<td width="45%"><input name="user" type="text" class="textogranatepequeño" maxlength="30"></td>
			</tr>
			<tr>
				<td width="55%"><div align="left"><span class="textogranate">CONTRASEÑA: </span></div></td>
				<td width="45%"><input name="pass" type="password" class="textogranatepequeño" maxlength="30"></td>
			</tr>
			<tr>
				<td colspan="2"><div align="center">
		 		 <input name="loginAceptar" type="submit" class="boton" value="ENTRAR">
	   			 </div></td>
			</tr>
		</table>
	</form>
	<table width="98%">
		<tr>
    	<td class="textogranate">
    	<% if (session != null) {  
    		if (null!=request.getAttribute("errorMessage"))   {
        		out.println(request.getAttribute("errorMessage"));
    		}
    	}
    	%>
    	</td></tr>
	</table>
</center>
 
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  <!-- InstanceEndEditable --><!-- end .content --></div>

<!-- end .container --></div>
</body>
<!-- InstanceEnd --></html>
