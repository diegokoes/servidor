package es.daw.jakarta.repository;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductoDAO {
    // constructor privado, no deja
    // DBConnection db = new DBConnection();
    //
    private Connection conn;

    public ProductoDAO(Connection con) throws SQLException{
            conn = DBConnection.getConnection();
    }

}
