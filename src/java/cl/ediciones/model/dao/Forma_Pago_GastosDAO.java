package cl.ediciones.model.dao;

import cl.ediciones.model.Forma_Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Forma_Pago_GastosDAO {

    public Forma_Pago retrive(int id_forma_pago) throws SQLException {
        Forma_Pago forma_pago = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from forma_pago_gastos where id_forma_pago = ?");
            ps.setInt(1, id_forma_pago);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                forma_pago = new Forma_Pago();
                forma_pago.setId_forma_pago(tabla.getInt("id_forma_pago"));
                forma_pago.setDescripcion(tabla.getString("descripcion"));

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return forma_pago;
    }

    public ArrayList traerTodos_Forma_Pago() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from forma_pago_gastos order by id_forma_pago");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Forma_Pago forma_pago = new Forma_Pago();
                forma_pago.setId_forma_pago(tabla.getInt("id_forma_pago"));
                forma_pago.setDescripcion(tabla.getString("descripcion"));

                lista.add(forma_pago);
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
