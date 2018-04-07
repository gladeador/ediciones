package cl.ediciones.model.dao;

import cl.ediciones.model.Tipo_Moneda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tipo_MonedaDAO {

    public Tipo_MonedaDAO() {
    }

    public Tipo_Moneda retrieve(int id_tipo_moneda) throws SQLException {
        Tipo_Moneda tipo_moneda = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from tipo_moneda where id_tipo_moneda = ?");
            ps.setInt(1, id_tipo_moneda);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                tipo_moneda = new Tipo_Moneda();
                tipo_moneda.setId_tipo_moneda(tabla.getInt("id_tipo_moneda"));
                tipo_moneda.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return tipo_moneda;
    }

    public String create(Tipo_Moneda tipo_moneda) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into tipo_moneda (id_tipo_moneda, descripcion) values (?, ?)");
            ps.setInt(1, tipo_moneda.getId_tipo_moneda());
            ps.setString(2, tipo_moneda.getDescripcion());

            ps.executeUpdate();

            ps.close();
            
            ok = "ok";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void update(Tipo_Moneda tipo_moneda) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update tipo_moneda set descripcion = ? where id_tipo_moneda = ?");
            ps.setString(1, tipo_moneda.getDescripcion());
            ps.setInt(2, tipo_moneda.getId_tipo_moneda());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int id_tipo_moneda) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from tipo_moneda where id_tipo_moneda = ?");
            ps.setInt(1, id_tipo_moneda);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Tipo_Moneda() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from tipo_moneda");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Tipo_Moneda tipo_moneda = new Tipo_Moneda();
                tipo_moneda.setId_tipo_moneda(tabla.getInt("id_tipo_moneda"));
                tipo_moneda.setDescripcion(tabla.getString("descripcion"));
                lista.add(tipo_moneda);
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
