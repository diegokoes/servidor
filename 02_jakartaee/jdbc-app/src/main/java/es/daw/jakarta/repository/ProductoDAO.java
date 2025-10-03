package es.daw.jakarta.repository;

import es.daw.jakarta.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAO implements GenericDAO<Producto, Integer>{
    private Connection conn;

    public ProductoDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    @Override
    public void save(Producto entity) throws SQLException {

    }

    @Override
    public Optional<Producto> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM producto");
            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getBigDecimal("precio"),
                        rs.getInt("codigo_fabricante")));
            }
        }

        return productos;
    }

    @Override
    public void update(Producto entity) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
