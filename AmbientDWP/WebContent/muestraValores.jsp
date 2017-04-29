<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Muestra Valores de Prueba</title>
</head>
<% String temp = request.getParameter("temp1");
   String hum = request.getParameter("hum1"); 
%>
<body>
 <table>
        <tbody>
                <tr>
                    <td>Temperatura:</td>
                    <td><%=temp %></td>
                </tr>
                <tr>
                    <td>Humedad: </td>
                    <td><%=hum %></td>
                </tr>
        </tbody>
    </table>

</body>
</html>