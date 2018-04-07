package cl.ediciones.model.dao;

import cl.ediciones.model.Tipo_Envio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tipo_EnvioDAO {

    public String createTipo_Envio(String descripcion) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into tipo_envio (descripcion) values (?)");
            ps.setString(1, descripcion);

            ps.executeUpdate();

            ps.close();
            ok = "ok";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void deleteTipo_Envio(int id_tipo_envio) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from tipo_envio where id_tipo_envio = ?");
            ps.setInt(1, id_tipo_envio);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTipo_Envio() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from tipo_envio");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Tipo_Envio tipoEnvio = new Tipo_Envio();
                tipoEnvio.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                tipoEnvio.setDescripcion(tabla.getString("descripcion"));
                lista.add(tipoEnvio);
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
