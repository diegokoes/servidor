package es.daw.jakarta.cookie;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/cookie-color-fondo")
public class CookieColorServlet extends HttpServlet {
    private String message;

    private static final String COOKIE_NAME = "colorFondo";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Crear una cookie
        Cookie cookie = new Cookie("colorFondo", "#ffffff");
        cookie.setMaxAge(0);
        response.addCookie(cookie);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Crear una cookie
        Cookie cookie = new Cookie("colorFondo", "#ffffff");
        cookie.setMaxAge(0);
        response.addCookie(cookie);


    }
}