package es.daw.jakarta.repository;

import es.daw.jakarta.model.Fabricante;
import es.daw.jakarta.model.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FabricanteDAO implements GenericDAO<Fabricante, Integer> {
    private Connection conn;

    public FabricanteDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    @Override
    public void save(Fabricante entity) throws SQLException {

    }

    @Override
    public Optional<Fabricante> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Fabricante> findAll() throws SQLException {
        List<Fabricante> fabricantes = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM fabricante");
            while (rs.next()) {
                fabricantes.add(new Fabricante(
                        rs.getString("nombre"),
                        rs.getInt("codigo")));
            }
        }

        return fabricantes;
    }

    @Override
    public void update(Fabricante entity) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
