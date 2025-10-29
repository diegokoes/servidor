package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Usuario;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(PreferenciasServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String, String> errores = new HashMap<>();

        if (username.isBlank()) {
            errores.put("username", "el nombre no puede estar vacío");
        } else if (username.length() < 3) {
            errores.put("username", "el nombre debe tener al menos 3 caracteres");
        } else if (username.length() > 20) {
            errores.put("username", "menos de 20 caracteres");
        } else if (username.matches("^.*\\D+.*$")) {
            errores.put("username", "sin números, solo letras");
        }
        ;

        if (password.isBlank()) {
            errores.put("password", "la password no puede estar vacía");
        } else if (password.length() < 8) {
            errores.put("password", "la password debe tener al menos 8 caracteres");
        } else if (password.matches("^.*\\w+\\d+[A-Z]+.*$")) {
            errores.put("password", "debe tener al menos una mayuscula y " +
                    "numeros");
        }

        if (errores.isEmpty()) {
            doPost(req, resp);
        } else {
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Usuario: " + username);
        log.info("Password: " + password);


        Optional<Usuario> usuario;
        try {
            GenericDAO<Usuario, String> daoU = new UsuarioDAO();
            usuario = daoU.findById(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String tipoUser = "invalido";

        if (!username.isBlank() && !password.isBlank() && usuario.isPresent()) {
            if (usuario.get().getUsername().equals(username) && usuario.get().getPassword().equals(password)) {
                tipoUser = usuario.get().getAdmin() ? "admin" : "user";
            }
        }

        Cookie cookie = new Cookie("tipoUser", tipoUser);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath() + "/welcome.jsp");

    }
}