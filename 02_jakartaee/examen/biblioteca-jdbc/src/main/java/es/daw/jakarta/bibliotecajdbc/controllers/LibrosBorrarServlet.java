package es.daw.jakarta.bibliotecajdbc.controllers;

import es.daw.jakarta.bibliotecajdbc.model.Libro;
import es.daw.jakarta.bibliotecajdbc.repository.GenericDAO;
import es.daw.jakarta.bibliotecajdbc.repository.LibroDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

@WebServlet("/libros/borrar")
public class LibrosBorrarServlet extends HttpServlet {

    private GenericDAO<Libro, BigInteger> daoLibro;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            daoLibro = new LibroDAO();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isBlank()) {
            try {
                daoLibro.delete(Integer.parseInt(idParam));
            } catch (SQLException e) {
                throw new ServletException("Error borrando libro", e);
        }
        }
        resp.sendRedirect(req.getContextPath() + "/libros/listar");
    }
}
