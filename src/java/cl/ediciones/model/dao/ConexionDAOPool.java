package cl.ediciones.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.InitialContext;

public class ConexionDAOPool {

    public ConexionDAOPool() {
    }

    public Connection obtenerConexion() {
        Connection con = null;
        try {
            InitialContext ic = new InitialContext();
            javax.sql.DataSource pool = (javax.sql.DataSource) ic.lookup("jdbc/ediciones");

            con = pool.getConnection();
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception ex) {
            System.out.println("IMPORTANTE: Falló POOL...");

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ediciones", "root", "018350"); //LOCAL
//                con = DriverManager.getConnection("jdbc:mysql://192.168.10.5:3306/ediciones", "root", "edicionesRYV");
            } catch (Exception ex2) {
                System.out.println("IMPORTANTE: Fallo conexion directa...");
            }

        }
        return con;
    }
}
