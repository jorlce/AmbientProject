<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/AmbientTmp.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Untitled Document</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
<style type="text/css">
<!--
<script src="http://maps.google.com/maps?file=api&v=2&key=AIzaSyAPbBpKkcg2ViWEeA7-yg9FgijcwI7nm4M" type="text/javascript"></script>
<script type="text/javascript">
 
 	  
      /*google.charts.setOnLoadCallback(initialize); 

       function initialize() {
        // The URL of the spreadsheet to source data from.
        var query = new google.visualization.Query(
            'https://spreadsheets.google.com/pub?key=pCQbetd-CptF0r8qmCOlZGg');
        query.send(draw);
      } */
      
      	function load() {
	       /*  if (response.isError()) {
	          alert('Error in query');
	        } */
	      if (GBrowserIsCompatible()) {  
	      
	        google.charts.load('current', {'packages': ['table', 'map']});
	        
	        var geoData = google.visualization.arrayToDataTable([
	          ['Latitud', 'Longitud', 'Sensor'],
	          [51.5072, -0.1275, 'SPA001'],
	          [48.8567, 2.3508, 'SPA002'],
	          [55.7500, 37.6167, 'SPA003']]);
	          
	        var geoView = new google.visualization.DataView(geoData);
        	geoView.setColumns([0, 1]);
        	
        	 var table = new google.visualization.Table(document.getElementById('table_div'));
        	table.draw(geoData, {showRowNumber: false, width: '100%', height: '100%'});
        	
        	var map = new google.visualization.Map(document.getElementById('map_div'));
        	map.draw(geoView, {showTip: true});

        	// Set a 'select' event listener for the table.
        	// When the table is selected, we set the selection on the map.
        	google.visualization.events.addListener(table, 'select',
            function() {
              map.setSelection(table.getSelection());
            });
            
             // Set a 'select' event listener for the map.
       		 // When the map is selected, we set the selection on the table.
        	google.visualization.events.addListener(map, 'select',
            function() {
              table.setSelection(map.getSelection());
            });
        } else {
        	System.out.println("Navegador no compatible");
        }
        	
   }
   
 </script>
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

<body bgcolor="#66CC99" onload="load()" onunload="GUnload()">

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
     <div class="content"><!-- InstanceBeginEditable name="EditRegion3" -->
    <table align="center">
      <tr valign="top">
        <td style="width: 50%;">
          <div id="map_div" style="width: 400px; height: 300;"></div>
        </td>
        <td style="width: 50%;">
          <div id="table_div"></div>
        </td>
      </tr>
    </table>
    <h1>&nbsp;</h1>
  <!-- InstanceEndEditable --><!-- end .content --></div>
	
  
<!-- end .container --></div>
</body>
<!-- InstanceEnd --></html>
