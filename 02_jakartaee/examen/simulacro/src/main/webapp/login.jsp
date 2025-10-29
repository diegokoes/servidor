<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%-- Created by IntelliJ IDEA. User: koes Date: 27/10/25 Time: 10:22 To change
this template use File | Settings | File Templates. --%>
<%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<%
    Map<String, String> errores = (HashMap<String, String>) request.getAttribute("errores");
%>
<body>
<form action=<%=request.getContextPath()%>/login>
    <div>
        <label for="username">Username</label>
        <input id="username" name="username" type="text"/>
        <% if (errores != null) {q
            for (Map.Entry<String, String> error : errores.entrySet()) {
                if (error.getKey().equals("username")) {%>
        <p><%=error.getValue()%>
        </p>
        <%
                    }
                }
            }
        %>
    </div>
    <div>
        <label for="password">Password</label>
        <input id="password" name="password" type="password"/>
        <% if (errores != null) {
            for (Map.Entry<String, String> error : errores.entrySet()) {
                if (error.getKey().equals("password")) {%>
        <p><%=error.getValue()%>
        </p>
        <%
                    }
                }
            }
        %>
    </div>
    <button type="submit">Login</button>
</form>
<a href="<%=request.getContextPath()%>/welcome.jsp">Ir a welcome.jsp</a>
</body>
</html>
