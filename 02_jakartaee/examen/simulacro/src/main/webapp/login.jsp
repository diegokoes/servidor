<%-- Created by IntelliJ IDEA. User: koes Date: 27/10/25 Time: 10:22 To change
this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
    <form method="post" action="login">
      <div>
        <label for="username">Username</label>
        <input id="username" name="username" type="text" required />
      </div>
      <div>
        <label for="password">Password</label>
        <input id="password" name="password" type="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
  <a href="<%=request.getContextPath()%>/welcome.jsp">Ir a welcome.jsp</a>
  </body>
</html>
