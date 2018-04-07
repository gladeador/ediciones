package cl.ediciones.model.dao;

import cl.ediciones.model.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BancoDAO {

    public Banco retrive_Banco(int id_banco) throws SQLException {
        Banco banco = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from banco where id_banco = ?");
            ps.setInt(1, id_banco);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                banco = new Banco();
                banco.setId_banco(tabla.getInt("id_banco"));
                banco.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return banco;
    }

    public String createBanco(String descripcion) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into banco (descripcion) values (?)");
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

    public void deleteBanco(int id_banco) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from banco where id_banco = ?");
            ps.setInt(1, id_banco);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerBanco() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from banco");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Banco banco = new Banco();
                banco.setId_banco(tabla.getInt("id_banco"));
                banco.setDescripcion(tabla.getString("descripcion"));
                lista.add(banco);
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
