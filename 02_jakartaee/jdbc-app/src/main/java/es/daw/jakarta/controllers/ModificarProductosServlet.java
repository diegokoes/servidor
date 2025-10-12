package es.daw.jakarta.controllers;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import es.daw.jakarta.repository.DBConnection;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/productos/modificar")
public class ModificarProductosServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ModificarProductosServlet.class.getName());
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String tipoOperacion = request.getAttribute("operacion").toString();

    }
}