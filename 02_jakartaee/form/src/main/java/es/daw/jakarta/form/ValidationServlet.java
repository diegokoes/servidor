package es.daw.jakarta.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/ValidationServlet")
public class ValidationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> nombre = Optional.of(req.getParameter("username"));
        Optional<String>  password = Optional.of(req.getParameter("password"));
        Optional<String>  email = Optional.of(req.getParameter("email"));
        Optional<String> pais = Optional.ofNullable(req.getParameter("pais"));
        List<String> lenguajes = req.getParameterValues("lenguajes") == null
                ? List.of()
                : Arrays.stream(req.getParameterValues("lenguajes")).toList();
// Still wrong! Same issue.        List<String> roles = List.of(req.getParameterValues("roles"));
        Optional<String>  idioma = Optional.ofNullable(req.getParameter("idioma"));
        Optional<String> habilitar = Optional.ofNullable(req.getParameter("habilitar"));
        Optional<String> secreto = Optional.ofNullable(req.getParameter("secreto"));

        Map<String, String> errores = new HashMap<>();

        if(nombre.get().isBlank()) errores.put("username", "El nombre es " +
                "obligatorio");
        if(lenguajes.isBlank()) errores.put("lenguajes","Elige un lenguaje");
        if(password.isBlank()) errores.put("password","Elige un rol");
        if(!password.matches("[a-zA-Z")) errores.put("password","tiene que " +
                "cumplir el patron");
    }
}
