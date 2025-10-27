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
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(PreferenciasServlet.class.getName());


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
                tipoUser = usuario.get().getAdmin()? "admin" : "user";
            }
        }

        Cookie cookie = new Cookie("tipoUser", tipoUser);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath() + "/welcome.jsp");

    }
}