package es.daw.jakarta.jdbcapp.repository;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO implements GenericDAO<Usuario,String>{
    private Connection conn;

    public UsuarioDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    @Override
    public void save(Usuario entity) throws SQLException {

    }

    @Override
    public Optional<Usuario> findById(String username) throws SQLException {

        String sql = "SELECT * FROM usuario WHERE username = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Usuario usuario = new Usuario(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("admin")
                );
                return Optional.of(usuario);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void update(Usuario entity) throws SQLException {

    }

    @Override
    public void delete(String s) throws SQLException {

    }


}
