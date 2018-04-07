package cl.ediciones.model.dao;

import cl.ediciones.model.Tipo_Documento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tipo_DocumentoDAO {
 
    public Tipo_DocumentoDAO() {
    }

    public Tipo_Documento retrieve(String id_tipo_documento) throws SQLException {
        Tipo_Documento tipo_documento = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from tipo_documento where id_tipo_documento = ?");
            ps.setString(1, id_tipo_documento);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                tipo_documento = new Tipo_Documento();
                tipo_documento.setId_tipo_documento(tabla.getString("id_tipo_documento"));
                tipo_documento.setDescripcion(tabla.getString("descripcion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return tipo_documento;
    }

    public String create(Tipo_Documento tipo_documento) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert Stringo tipo_documento (id_tipo_documento, descripcion) values (?, ?)");
            ps.setString(1, tipo_documento.getId_tipo_documento());
            ps.setString(2, tipo_documento.getDescripcion());

            ps.executeUpdate();

            ps.close();
            
            ok = "ok";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void update(Tipo_Documento tipo_documento) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update tipo_documento set descripcion = ? where id_tipo_documento = ?");
            ps.setString(1, tipo_documento.getDescripcion());
            ps.setString(2, tipo_documento.getId_tipo_documento());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(String id_tipo_documento) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from  tipo_documento where id_tipo_documento = ?");
            ps.setString(1, id_tipo_documento);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Tipo_Documento() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from tipo_documento");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Tipo_Documento tipo_documento = new Tipo_Documento();
                tipo_documento.setId_tipo_documento(tabla.getString("id_tipo_documento"));
                tipo_documento.setDescripcion(tabla.getString("descripcion"));
                lista.add(tipo_documento);
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
