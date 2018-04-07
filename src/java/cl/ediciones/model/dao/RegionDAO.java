package cl.ediciones.model.dao;

import cl.ediciones.model.Region;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegionDAO {

    public Region retrive_Region(int id_region) throws SQLException {
        Region region = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from region where id_region = ?");
            ps.setInt(1, id_region);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                region = new Region();
                region.setId_region(tabla.getInt("id_region"));
                region.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return region;
    }

    public String createRegion(String descripcion) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into region (descripcion) values (?)");
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

    public void deleteRegion(int id_region) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from region where id_region = ?");
            ps.setInt(1, id_region);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerRegion() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from region");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Region region = new Region();
                region.setId_region(tabla.getInt("id_region"));
                region.setN_romano(tabla.getString("n_romano"));
                region.setDescripcion(tabla.getString("descripcion"));
                
                lista.add(region);
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
