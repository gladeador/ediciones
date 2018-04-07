package cl.ediciones.model.dao;

import cl.ediciones.model.Estado_Cheque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Estado_ChequeDAO {

    public Estado_Cheque retrive_Estado_Cheque(String estado) throws SQLException {
        Estado_Cheque estado_cheque = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from estado_cheque where estado = ?");
            ps.setString(1, estado);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                estado_cheque = new Estado_Cheque();
                estado_cheque.setEstado(tabla.getString("estado"));
                estado_cheque.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return estado_cheque;
    }

    public ArrayList traerEstado_Cheque() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from estado_cheque");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Estado_Cheque estado_cheque = new Estado_Cheque();
                estado_cheque.setEstado(tabla.getString("estado"));
                estado_cheque.setDescripcion(tabla.getString("descripcion"));
                lista.add(estado_cheque);
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
