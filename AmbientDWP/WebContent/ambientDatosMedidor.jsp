<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.ambient.controller.*,com.ambient.dao.*, com.ambient.model.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/AmbientTmp.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Untitled Document</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<style type="text/css">
#apDiv1 {
	position: absolute;
	width: 200px;
	height: 115px;
	z-index: 1;
	left: 401px;
	top: 115px;
}
#apDiv2 {
	position: absolute;
	width: 282px;
	height: 310px;
	z-index: 1;
	left: 410px;
	top: 130px;
}
</style>
<!-- InstanceEndEditable -->
<style type="text/css">
<!--
body {
	font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif;
	background-color: #66CC99;
	margin: 0;
	padding: 0;
}

/* ~~ Element/tag selectors ~~ */
ul, ol, dl { /* Due to variations between browsers, it's best practices to zero padding and margin on lists. For consistency, you can either specify the amounts you want here, or on the list items (LI, DT, DD) they contain. Remember that what you do here will cascade to the .nav list unless you write a more specific selector. */
	padding: 0;
	margin: 0;
}
h1, h2, h3, h4, h5, h6, p {
	margin-top: 0;	 /* removing the top margin gets around an issue where margins can escape from their containing div. The remaining bottom margin will hold it away from any elements that follow. */
	padding-right: 15px;
	padding-left: 15px; /* adding the padding to the sides of the elements within the divs, instead of the divs themselves, gets rid of any box model math. A nested div with side padding can also be used as an alternate method. */
}
a img { /* this selector removes the default blue border displayed in some browsers around an image when it is surrounded by a link */
	border: none;
}
/* ~~ Styling for your site's links must remain in this order - including the group of selectors that create the hover effect. ~~ */
a:link {
	color: #42413C;
	text-decoration: underline; /* unless you style your links to look extremely unique, it's best to provide underlines for quick visual identification */
}
a:visited {
	color: #6E6C64;
	text-decoration: underline;
	background-color: #0C9;
}
a:hover, a:active, a:focus { /* this group of selectors will give a keyboard navigator the same hover experience as the person using a mouse. */
	text-decoration: none;
}

/* ~~ this fixed width container surrounds the other divs ~~ */
.container {
	width: 960px;
	background-color: #FFF;
	margin: 0 auto; /* the auto value on the sides, coupled with the width, centers the layout */
}

/* ~~ the header is not given a width. It will extend the full width of your layout. It contains an image placeholder that should be replaced with your own linked logo ~~ */
.header {
	background-color: #ADB96E;
}

/* ~~ This is the layout information. ~~ 

1) Padding is only placed on the top and/or bottom of the div. The elements within this div have padding on their sides. This saves you from any "box model math". Keep in mind, if you add any side padding or border to the div itself, it will be added to the width you define to create the *total* width. You may also choose to remove the padding on the element in the div and place a second div within it with no width and the padding necessary for your design.

*/

.content {

	padding: 10px 0;
}

/* ~~ The footer ~~ */
.footer {
	padding: 10px 0;
	background-color: #CCC49F;
}

/* ~~ miscellaneous float/clear classes ~~ */
.fltrt {  /* this class can be used to float an element right in your page. The floated element must precede the element it should be next to on the page. */
	float: right;
	margin-left: 8px;
}
.fltlft { /* this class can be used to float an element left in your page. The floated element must precede the element it should be next to on the page. */
	float: left;
	margin-right: 8px;
}
.clearfloat { /* this class can be placed on a <br /> or empty div as the final element following the last floated div (within the #container) if the #footer is removed or taken out of the #container */
	clear:both;
	height:0;
	font-size: 1px;
	line-height: 0px;
}
.container .header a #Insert_logo {
	color: #00D96C;
}
.container .header a #Insert_logo {
	color: #00D96C;
}
-->
</style></head>

<body bgcolor="#66CC99">

<div class="container">
  <div class="header"><a href="#"></a>
    <table width="961" border="1">
      <tr bgcolor="#66CC99">
        <th width="107"><div align="center"><a href="AmbientServlet?action=inicio">INICIO</a></div></th>
        <th width="294"><div align="center"><a href="AmbientServlet?action=geoloc">GEOLOCALIZACION</a></div></th>
        <th width="153"><div align="center"><a href="AmbientServlet?action=statistics">ESTADISTICAS</a></div></th>
        <th width="260"><div align="center"><a href="AmbientServlet?action=admin">ADMINISTRACION</a></div></th>
        <th width="113"><div align="center"><a href="AmbientServlet?action=contact">CONTACTO</a></div></th>
      </tr>
    </table> 
    
    <!-- end .header --></div>
<%
Medidor unMedidor;
unMedidor = (Medidor) request.getAttribute("unSensor");
if (unMedidor.getSensorlabel()==""){
	System.out.println("Datos de Sensor vacios en ambientDatosMedidor.jsp");

} else {	
%>
  <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
    <h2 align="center" class="header"><strong>Administración</strong></h2>
    <div align="center"></div>
    <p>&nbsp;</p>
    <div align="center">
      <form id="formProg" name="formProg" method="post" action="AmbientServlet">
        <table width="418" border="1" cellpadding="1">
          <tr>
            <td colspan="2"><div align="center">VALORES  MEDIDOR</div></td>
          </tr>
          <tr>
            <td width="172"><label>
              <div align="center">Identificador</div></label></td>
            <td width="230"><div align="center">
              <%=unMedidor.getSensorlabel() %> />
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
              <%=unMedidor.getNivelCO() %>
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Nivel CO2</div></label></td>
            <td><div align="center">
              <%=unMedidor.getNivelCO2() %>
            </div></td>
          </tr>
          <tr>
            <td><label>
              <div align="center">Nivel Metano</div></label></td>
            <td><div align="center">
              <%=unMedidor.getNivelMetano() %>
            </div></td>
          </tr>
          <tr>
            <td><div align="center">
              <input type="button" name="accept" id="accept" value="Programar" />
            </div></td>
            <td><div align="center">
              <input type="button" name="volver" id="volver" value="volver" />
            </div></td>
          </tr>
        </table>
      </form>
<%} 
String volver="AmbientServlet?action=consultMedidor";
%>
	<a href="<%=volver%>" align="center">VOLVER &raquo;</a>
    </div>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <div align="center"></div>
    <p>&nbsp;</p>
  <!-- InstanceEndEditable --><!-- end .content --></div>
  <div class="footer">
    <p>Footer</p>
    <!-- end .footer --></div>
<!-- end .container --></div>
</body>
<!-- InstanceEnd --></html>
