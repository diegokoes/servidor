package es.daw.jakarta.controllers;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import es.daw.jakarta.model.Producto;
import es.daw.jakarta.repository.DBConnection;
import es.daw.jakarta.repository.GenericDAO;
import es.daw.jakarta.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/productos/ver")
public class ListarProductosServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ListarProductosServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Producto> productos =null;
        try {
            GenericDAO<Producto,Integer> daoP = new ProductoDAO();
            productos = daoP.findAll();
            productos.sort((p1, p2) -> p2.getNombre().compareTo(p1.getNombre()));
            productos.forEach( producto -> log.info(producto.toString()));
        } catch (SQLException e) {
            log.severe("Error al obtener los productos" +e );
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request,response);
        }
        request.setAttribute("productos", productos);
        getServletContext().getRequestDispatcher("/informe.jsp").forward(request,response);
    }
}