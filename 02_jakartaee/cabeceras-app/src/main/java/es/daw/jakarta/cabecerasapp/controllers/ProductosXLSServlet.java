package es.daw.jakarta.cabecerasapp.controllers;

import es.daw.jakarta.cabecerasapp.model.Producto;
import es.daw.jakarta.cabecerasapp.service.ProductoService;
import es.daw.jakarta.cabecerasapp.service.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductosXLSServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productoService = new ProductoServiceImpl();

        List<Producto> productos = productoService.findAll();
        // pendiente controlar si exporta a XLS

        req.setAttribute("productos", productos);
        getServletContext().getRequestDispatcher("/productos.jsp").forward(req, resp);

    }

}