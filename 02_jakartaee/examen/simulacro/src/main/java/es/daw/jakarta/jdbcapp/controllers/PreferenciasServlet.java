package es.daw.jakarta.jdbcapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/preferencias")
public class PreferenciasServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(PreferenciasServlet.class.getName());


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String preferencia = request.getParameter("mostrar").trim().toLowerCase();
        log.info("Preferencia: " + preferencia);
        Cookie cookie = new Cookie("preferencia", preferencia);
        response.addCookie(cookie);
        cookie.setPath(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/fabricantes/ver");
    }
}