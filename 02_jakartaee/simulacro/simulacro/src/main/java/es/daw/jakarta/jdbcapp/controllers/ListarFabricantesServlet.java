package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;
import es.daw.jakarta.jdbcapp.repository.DBConnection;
import es.daw.jakarta.jdbcapp.repository.FabricanteDAO;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@WebServlet(name = "ListarFabricantesServlet", value = "/fabricantes/ver")
public class ListarFabricantesServlet extends HttpServlet {

    private static final Logger log =  Logger.getLogger(ListarFabricantesServlet.class.getName());

    // listar fabricantes
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Fabricante> fabricantes = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        try {
            GenericDAO<Fabricante,Integer> daoF = new FabricanteDAO();
            GenericDAO<Producto,Integer> daoP = new ProductoDAO();

            productos = daoP.findAll();


            fabricantes = daoF.findAll();
            fabricantes.forEach(f -> log.info(f.toString()));
            fabricantes.sort((f1, f2) -> f2.getNombre().compareToIgnoreCase(f1.getNombre()));

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
        String preferencia = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie c : cookies) {
                if (c.getName().equals("preferencia")) {
                     preferencia = c.getValue();
                }
            }
        }

        request.setAttribute("preferencia", preferencia);
        request.setAttribute("fabricantes", fabricantes);
        request.setAttribute("productos", productos);
        getServletContext().getRequestDispatcher("/fabricantes.jsp").forward(request, response);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }

    @Override
    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); // el server devolvería 500
        }
    }
}