package cl.ediciones.model.dao;

import cl.ediciones.model.Estado_Protesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Estado_ProtestoDAO {

    public Estado_Protesto retrive_Estado_Protesto(String estado) throws SQLException {
        Estado_Protesto estado_protesto = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from estado_protesto where estado = ?");
            ps.setString(1, estado);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                estado_protesto = new Estado_Protesto();
                estado_protesto.setEstado(tabla.getString("estado"));
                estado_protesto.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return estado_protesto;
    }

    public ArrayList traerEstado_Protesto() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from estado_protesto");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Estado_Protesto estado_protesto = new Estado_Protesto();
                estado_protesto.setEstado(tabla.getString("estado"));
                estado_protesto.setDescripcion(tabla.getString("descripcion"));
                lista.add(estado_protesto);
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
