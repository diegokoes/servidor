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

@WebServlet("/autores/editar")
public class AutoresEditarServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String nombre = req.getParameter("nombre");
        if (idParam != null && !idParam.isBlank()) {
            Autor autor = new Autor(nombre, new BigInteger(idParam));
            req.setAttribute("autorEditar", autor);
        }
        getServletContext().getRequestDispatcher("/autores/formautor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("autorId");
        String nombre = req.getParameter("nombre");
        try {
            Autor autor = new Autor(nombre, idParam != null && !idParam.isBlank() ? new BigInteger(idParam) : null);
            daoAutor.update(autor);
            resp.sendRedirect(req.getContextPath() + "/autores/listar");
        } catch (SQLException e) {
            throw new ServletException("Error actualizando autor", e);
        }
    }
}
