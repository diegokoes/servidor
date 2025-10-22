package es.daw.jakarta.bibliotecajdbc.controllers;

import es.daw.jakarta.bibliotecajdbc.model.Autor;
import es.daw.jakarta.bibliotecajdbc.repository.AutorDAO;
import es.daw.jakarta.bibliotecajdbc.repository.GenericDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

@WebServlet("/autores/crear")
public class AutoresCrearServlet extends HttpServlet {

    private GenericDAO<Autor, BigInteger> daoAutor;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            daoAutor = new AutorDAO();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        try {
            Autor autor = new Autor(nombre, null);
            daoAutor.insert(autor);
            resp.sendRedirect(req.getContextPath() + "/autores/listar");
        } catch (SQLException e) {
            throw new ServletException("Error creando autor", e);
        }
    }
}
