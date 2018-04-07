package cl.ediciones.model.dao;

import cl.ediciones.model.Fecha_Actual;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Fecha_ActualDAO {
 
    public Fecha_Actual retrieve() throws SQLException {
        Fecha_Actual fecha = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT now() as fecha_actual, DATE_FORMAT( now() , '%m' ) AS mes, DATE_FORMAT( now() , '%Y' ) AS ano");

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                fecha = new Fecha_Actual();
                fecha.setFecha_actual(tabla.getDate("fecha_actual"));
                fecha.setMes(tabla.getString("mes"));
                fecha.setAno(tabla.getString("ano"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return fecha;
    }

}
