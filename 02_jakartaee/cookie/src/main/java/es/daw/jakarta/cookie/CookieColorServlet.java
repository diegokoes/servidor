package es.daw.jakarta.cookie;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/cookie-color-fondo")
public class CookieColorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cookie color = new Cookie("color", request.getParameter("color"));
        response.addCookie(color);
        response.sendRedirect("index.jsp");
        return;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie color = new Cookie("color", "");
        color.setMaxAge(0);
        resp.addCookie(color);
        resp.sendRedirect("index.jsp");
        return;
    }
}