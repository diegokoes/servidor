package es.daw.jakarta.bibliotecajdbc.repository;

import es.daw.jakarta.bibliotecajdbc.model.Libro;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAO implements GenericDAO<Libro, BigInteger>{
    private Connection conn;

    public LibroDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }
    @Override
    public Optional findByID(BigInteger id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Libro> selectAll() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM " +
                "BOOK")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                libros.add(new Libro(rs.getString(
                        "TITLE"),
                        BigInteger.valueOf(rs.getInt("author_id")),
                        rs.getDate("publication_date"),
                        BigInteger.valueOf(rs.getInt("id"))));
            }
            return libros;
        }
    }

    @Override
    public void insert(Libro libro) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO BOOK (TITLE, author_id, publication_date) VALUES (?, ?, ?)") ) {
            ps.setString(1, libro.getTitulo());
            if (libro.getId_autor() != null) {
                ps.setInt(2, libro.getId_autor().intValue());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            if (libro.getFechaPublicacion() != null) {
                ps.setDate(3, new java.sql.Date(libro.getFechaPublicacion().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Libro libro) throws SQLException {
        if(libro.getId_libro() == null) {
            throw new SQLException("ID de libro nulo, no se puede actualizar");
        }
        try(PreparedStatement ps = conn.prepareStatement("UPDATE BOOK SET TITLE = ?, author_id = ?, publication_date = ? WHERE ID = ?")) {
            ps.setString(1, libro.getTitulo());
            if (libro.getId_autor() != null) {
                ps.setInt(2, libro.getId_autor().intValue());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            if (libro.getFechaPublicacion() != null) {
                ps.setDate(3, new java.sql.Date(libro.getFechaPublicacion().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }
            ps.setInt(4, libro.getId_libro().intValue());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM BOOK WHERE ID = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
