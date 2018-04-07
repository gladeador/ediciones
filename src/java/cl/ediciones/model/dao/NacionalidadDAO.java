package cl.ediciones.model.dao;

import cl.ediciones.model.Clientes;
import cl.ediciones.model.Nacionalidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NacionalidadDAO {
   
    public ArrayList traerNacionalidad() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from nacionalidad");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Nacionalidad nacionalidad = new Nacionalidad();
                nacionalidad.setId_nacionalidad(tabla.getInt("id_nacionalidad"));
                nacionalidad.setDescripcion(tabla.getString("descripcion"));
                lista.add(nacionalidad);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

}
