package cl.ediciones.model.dao;

import cl.ediciones.model.Perfil_Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Perfil_MenuDAO {

    public Perfil_Menu retrive_Perfil_Menu(int id_perfil, String id_menu) throws SQLException {
        Perfil_Menu perfil_menu = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from perfil_menu where id_perfil = ? and id_menu = ?");
            ps.setInt(1, id_perfil);
            ps.setString(2, id_menu);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                perfil_menu = new Perfil_Menu();
                perfil_menu.setId_perfil(tabla.getInt("id_perfil"));
                perfil_menu.setId_menu(tabla.getString("id_menu"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return perfil_menu;
    }

    public void createPerfil_Menu(int id_perfil, String id_menu) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into perfil_menu (id_perfil, id_menu) values (?, ?)");
            ps.setInt(1, id_perfil);
            ps.setString(2, id_menu);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void deletePerfil_Menu(int id_perfil, String id_menu) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from perfil_menu where id_perfil = ? and id_menu = ?");
            ps.setInt(1, id_perfil);
            ps.setString(2, id_menu);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerPerfil_Menu_por_Perfil(int id_perfil) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from perfil_menu where id_perfil = ?");
            ps.setInt(1, id_perfil);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Perfil_Menu perfil_menu = new Perfil_Menu();
                perfil_menu.setId_perfil(tabla.getInt("id_perfil"));
                perfil_menu.setId_menu(tabla.getString("id_menu"));
                lista.add(perfil_menu);
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
