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
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/libros/editar")
public class LibrosEditarServlet extends HttpServlet {

    private GenericDAO<Libro, BigInteger> daoLibro;
    private GenericDAO<Autor, BigInteger> daoAutor;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            daoLibro = new LibroDAO();
            daoAutor = new AutorDAO();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String titulo = req.getParameter("titulo");
        String autorIdParam = req.getParameter("autor");
        String fechaParam = req.getParameter("fechaPublicacion"); // expect ISO yyyy-MM-dd

        Date fecha = null;
        if (fechaParam != null && !fechaParam.isBlank()) {
            LocalDate ld = LocalDate.parse(fechaParam);
            fecha = Date.valueOf(ld);
        }
        if (idParam != null && !idParam.isBlank()) {
            Libro libro = new Libro(
                    titulo,
                    (autorIdParam != null && !autorIdParam.isBlank()) ? new BigInteger(autorIdParam) : null,
                    fecha,
                    new BigInteger(idParam)
            );
            req.setAttribute("libroEditar", libro);
        }
        try {
            List<Autor> autores = daoAutor.selectAll();
            req.setAttribute("listaAutores", autores);
        } catch (SQLException e) {
            throw new ServletException("Error cargando autores", e);
        }
        getServletContext().getRequestDispatcher("/libros/formlibro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("libroId");
        String titulo = req.getParameter("titulo");
        String autorId = req.getParameter("autor");
        String fechaParam = req.getParameter("fechaPublicacion"); // ISO yyyy-MM-dd

        try {
            Date fecha = null;
            if (fechaParam != null && !fechaParam.isBlank()) {
                LocalDate ld = LocalDate.parse(fechaParam);
                fecha = Date.valueOf(ld);
            }
            Libro libro = new Libro(
                    titulo,
                    (autorId != null && !autorId.isBlank()) ? new BigInteger(autorId) : null,
                    fecha,
                    (idParam != null && !idParam.isBlank()) ? new BigInteger(idParam) : null
            );
            daoLibro.update(libro);
            resp.sendRedirect(req.getContextPath() + "/libros/listar");
        } catch (SQLException e) {
            throw new ServletException("Error actualizando libro", e);
        }
    }
}
