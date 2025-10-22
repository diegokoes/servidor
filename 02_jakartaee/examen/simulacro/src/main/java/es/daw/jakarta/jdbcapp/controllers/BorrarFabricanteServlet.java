package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Producto;
import es.daw.jakarta.jdbcapp.repository.FabricanteDAO;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/fabricantes/borrar")
public class BorrarFabricanteServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(BorrarFabricanteServlet.class.getName());

    private GenericDAO daoF;
    private GenericDAO daoP;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        Integer codigoFabricante = Integer.parseInt(request.getParameter("codigo"));
        if (codigoFabricante == null) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        try {
            daoF = new FabricanteDAO();
            daoP = new ProductoDAO();
            List<Producto> productos = daoP.findAll();

            boolean tieneProductosAsociados = false;
            for(Producto p : productos){
                if(p.getCodigo_fabricante().equals(codigoFabricante)){
                    tieneProductosAsociados = true;
                    break;
                }
            }

            if(tieneProductosAsociados) {
                log.info("Fabricante " + codigoFabricante + " tiene productos asociados, no se puede borrar");
                request.setAttribute("error", "No se puede borrar el fabricante porque tiene productos asociados");
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                daoF.delete(codigoFabricante);
                log.info("Fabricante borrado: " + codigoFabricante);
                response.sendRedirect(request.getContextPath() +
                        "/fabricantes/ver");
            }

        } catch (SQLException e) {
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
