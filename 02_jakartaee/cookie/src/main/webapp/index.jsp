        <%@ page import="java.util.Optional" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%
            Cookie[] cookies = request.getCookies();
            boolean hasColorCookie = false;
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("color")) {
                        hasColorCookie = true;
                        break;
                    }
                }
            }
            if (!hasColorCookie) {
                Cookie newCookie = new Cookie("color", "#ffffff");
                response.addCookie(newCookie);
                response.sendRedirect("index.jsp");
                return;
            }
        %>

<!DOCTYPE html>
<html>
<head>
    <title>Bienvenido</title>
</head>
<style>
    body {
        background-color: ${cookie.color.value}
    }
</style>
<body>

<h1><strong>Bienvenido!!</strong></h1>
<br/>
<p>Color de fondo actual:<span style="font-weight: bolder">${cookie.color.value}</span>
</p>
<a href="color.jsp?color=${cookie.color.value}">Cambiar color</a>
<a href="cookie-color-fondo">Borrar la preferencia</a>
</body>
</html>