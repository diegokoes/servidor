package es.daw.jakarta.login;

import java.io.*;
import java.util.Optional;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Optional<String> usuario =
                Optional.ofNullable((String) request.getParameter(
                        "username"));
        Optional<String> password = Optional.ofNullable((String) request.getParameter(
                "password")).filter(s -> !s.isBlank());

        if (usuario.isEmpty() || password.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");             out.println("<html>");
                out.println("<head>");
                out.println("<title>Login correcto</title>");
                out.println("</title>");
                out.println("   <body>");
                out.println("       <h1>Login correcto!</h1>");
                out.println("       <h3>Hola " + usuario.get() + " has " +
                        "iniciado sesión con " +
                        "éxito!</h3>");
                out.println("   </body>");
                out.println("</html>");
            }
        }


    }
}