package es.daw.jakarta.controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import es.daw.jakarta.model.Fabricante;
import es.daw.jakarta.repository.FabricanteDAO;
import es.daw.jakarta.repository.GenericDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/index")
public class ListarFabricantesServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ListarFabricantesServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Fabricante> fabricantes = null;
        try {
            GenericDAO<Fabricante, Integer> daoF = new FabricanteDAO();
            fabricantes = daoF.findAll();
            fabricantes.sort((p1, p2) ->  p2.getCodigo().compareTo(p1.getCodigo()));
            fabricantes.forEach(producto -> log.info(producto.toString()));
        } catch (SQLException e) {
            log.severe("Error al obtener los fabricantes" + e);
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
        request.setAttribute("fabricantes", fabricantes);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}