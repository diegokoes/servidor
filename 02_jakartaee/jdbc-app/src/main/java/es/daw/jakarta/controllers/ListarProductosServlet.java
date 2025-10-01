package es.daw.jakarta.controllers;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import es.daw.jakarta.repository.DBConnection;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/productos/ver")
public class ListarProductosServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ListarProductosServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}