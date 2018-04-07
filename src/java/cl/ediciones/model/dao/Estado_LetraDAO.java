package cl.ediciones.model.dao;

import cl.ediciones.model.Estado_Letra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Estado_LetraDAO {

    public Estado_Letra retrive_Estado_Letra(String estado) throws SQLException {
        Estado_Letra estado_letra = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from estado_letra where estado = ?");
            ps.setString(1, estado);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                estado_letra = new Estado_Letra();
                estado_letra.setEstado(tabla.getString("estado"));
                estado_letra.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return estado_letra;
    }

    public ArrayList traerEstado_Letra() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from estado_letra");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Estado_Letra estado_letra = new Estado_Letra();
                estado_letra.setEstado(tabla.getString("estado"));
                estado_letra.setDescripcion(tabla.getString("descripcion"));
                lista.add(estado_letra);
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
