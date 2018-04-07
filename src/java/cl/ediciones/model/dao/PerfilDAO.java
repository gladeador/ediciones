package cl.ediciones.model.dao;

import cl.ediciones.model.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerfilDAO {

    public Perfil retrive_Perfil(int id_perfil) throws SQLException {
        Perfil perfil = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from perfil where id_perfil = ?");
            ps.setInt(1, id_perfil);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                perfil = new Perfil();
                perfil.setId_perfil(tabla.getInt("id_perfil"));
                perfil.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return perfil;
    }

    public String createPerfil(String descripcion) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into perfil (descripcion) values (?)");
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

    public void deletePerfil(int id_perfil) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from perfil where id_perfil = ?");
            ps.setInt(1, id_perfil);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerPerfil() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from perfil");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Perfil perfil = new Perfil();
                perfil.setId_perfil(tabla.getInt("id_perfil"));
                perfil.setDescripcion(tabla.getString("descripcion"));
                lista.add(perfil);
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
