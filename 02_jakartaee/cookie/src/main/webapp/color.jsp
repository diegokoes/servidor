<%--
  Created by IntelliJ IDEA.
  User: koes
  Date: 29/9/25
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Elige color de fondo</title>

</head>
<body>
<h1>Color de fondo:</h1>
<form method="post" action="cookie-color-fondo">
    <label for="color">
        Color:
    </label>

    <input id="color" type="color" name="color" value="<%=color%>">
    <button type="submit">Guardar</button>
</form>
<p>
    <a href="index.jsp">Volver</a>

</p>
</body>
</html>
