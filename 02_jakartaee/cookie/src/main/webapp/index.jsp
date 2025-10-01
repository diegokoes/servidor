<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bienvenido</title>
</head>
<body>
<%
    String color = "#ffffff";
    Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
    Arrays.stream(cookies)
            .filter(c -> "colorFondo".equals(c.getName()))
            .map(Cookie::getValue);
%>
<h1><strong>Bienvenido!!</strong></h1>
<br/>
<p>Color de fondo actual:<span style="font-weight: bolder"><%=color%></span>
</p>
<a href="color.jsp">Cambiar color</a>
<a href="cookie-color-fondo">Borrar la preferencia</a>
</body>
</html>