package es.daw.jakarta.bibliotecajdbc.controllers;

import es.daw.jakarta.bibliotecajdbc.model.Autor;
import es.daw.jakarta.bibliotecajdbc.model.Libro;
import es.daw.jakarta.bibliotecajdbc.repository.AutorDAO;
import es.daw.jakarta.bibliotecajdbc.repository.GenericDAO;
import es.daw.jakarta.bibliotecajdbc.repository.LibroDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/libros/buscar")
public class filtrarLibrosServlet extends HttpServlet {
    private LibroDAO daoL;
    private GenericDAO daoA;
    private List<Libro> librosList;
    private List<Autor> autorList;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            daoL = new LibroDAO();
            daoA = new AutorDAO();
            librosList = daoL.selectAll();
            autorList = daoA.selectAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipoFiltro = req.getParameter("busqueda");
        String textoFiltro = req.getParameter("textoFiltro");
        List<Libro> librosFiltrados = null;
        if (tipoFiltro.equals("titulo")) {
            librosFiltrados = daoL.filtrarLibroByTitulo(librosList
                    , textoFiltro);
        } else if (tipoFiltro.equals("autor")) {
            librosFiltrados = daoL.filtrarLibroByAutor(librosList, textoFiltro, autorList);

        } else {
            resp.sendRedirect(req.getContextPath() + "/libros/listar");
        }
        req.setAttribute("libros", librosFiltrados);
        req.setAttribute("autores", autorList);
        req.getRequestDispatcher("/libros/libros.jsp").forward(req, resp);

    }
}
