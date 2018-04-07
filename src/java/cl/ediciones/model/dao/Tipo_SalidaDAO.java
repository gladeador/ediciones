package cl.ediciones.model.dao;

import cl.ediciones.model.Tipo_Salida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tipo_SalidaDAO {

    public Tipo_Salida retrieve(int id_tipo_salida) throws SQLException {
        Tipo_Salida tipo_salida = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from tipo_salida where id_tipo_salida = ?");
            ps.setInt(1, id_tipo_salida);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                tipo_salida = new Tipo_Salida();
                tipo_salida.setId_tipo_salida(tabla.getInt("id_tipo_salida"));
                tipo_salida.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return tipo_salida;
    }

    public ArrayList traerTodos_Tipo_Salida() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from tipo_salida order by id_tipo_salida");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Tipo_Salida tipo_salida = new Tipo_Salida();
                tipo_salida.setId_tipo_salida(tabla.getInt("id_tipo_salida"));
                tipo_salida.setDescripcion(tabla.getString("descripcion"));
                lista.add(tipo_salida);
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
