package es.daw.jakarta.bibliotecajdbc.controllers;

import java.io.*;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import es.daw.jakarta.bibliotecajdbc.model.Autor;
import es.daw.jakarta.bibliotecajdbc.model.Libro;
import es.daw.jakarta.bibliotecajdbc.repository.AutorDAO;
import es.daw.jakarta.bibliotecajdbc.repository.GenericDAO;
import es.daw.jakarta.bibliotecajdbc.repository.LibroDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/autores/listar", "/libros/listar"})
public class ListarServlet extends HttpServlet {

    private GenericDAO<Libro, BigInteger> daoLibro;
    private GenericDAO<Autor, BigInteger> daoAutor;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            daoLibro = new LibroDAO();
            daoAutor = new AutorDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        List<Autor> autores = null;
        List<Libro> libros;
        try {
            autores = daoAutor.selectAll();
            libros = daoLibro.selectAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("libros", libros);
        request.setAttribute("autores", autores);

        if (path.equals("/autores/listar")) {
            getServletContext().getRequestDispatcher("/autores/autores.jsp").forward(request, response);
            return;
        } else if (path.equals("/libros/listar")) {
            getServletContext().getRequestDispatcher("/libros/libros.jsp").forward(request, response);
            return;
        }
    }
}