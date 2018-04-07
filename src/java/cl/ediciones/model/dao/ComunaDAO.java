package cl.ediciones.model.dao;

import cl.ediciones.model.Comuna;
import cl.ediciones.model.Region;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComunaDAO {

    public Comuna retrive_Comuna(int id_comuna) throws SQLException {
        Comuna comuna = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from comuna where id_comuna = ?");
            ps.setInt(1, id_comuna);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                comuna = new Comuna();
                comuna.setId_comuna(tabla.getInt("id_comuna"));
                comuna.setDescripcion(tabla.getString("descripcion"));
                
                RegionDAO regDAO = new RegionDAO();
                Region region = regDAO.retrive_Region(tabla.getInt("id_region"));
                comuna.setRegion(region);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return comuna;
    }

    public String createComuna(String descripcion, int id_region) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comuna (descripcion, id_region) values (?, ?)");
            ps.setString(1, descripcion);
            ps.setInt(2, id_region);

            ps.executeUpdate();

            ps.close();
            ok = "ok";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void deleteComuna(int id_comuna) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from comuna where id_comuna = ?");
            ps.setInt(1, id_comuna);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerComuna() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from comuna order by id_region, descripcion");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Comuna comuna = new Comuna();
                comuna.setId_comuna(tabla.getInt("id_comuna"));
                comuna.setDescripcion(tabla.getString("descripcion"));
                
                RegionDAO regDAO = new RegionDAO();
                Region region = regDAO.retrive_Region(tabla.getInt("id_region"));
                comuna.setRegion(region);
                
                lista.add(comuna);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodo_Comuna_por_Region(int id_region) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from comuna where id_region = ? order by descripcion");
            ps.setInt(1, id_region);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Comuna comuna = new Comuna();
                comuna.setId_comuna(tabla.getInt("id_comuna"));
                comuna.setDescripcion(tabla.getString("descripcion"));
                
                RegionDAO regDAO = new RegionDAO();
                Region region = regDAO.retrive_Region(tabla.getInt("id_region"));
                comuna.setRegion(region);
                
                lista.add(comuna);
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
