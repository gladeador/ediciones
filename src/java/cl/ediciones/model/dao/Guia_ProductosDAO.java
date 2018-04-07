package cl.ediciones.model.dao;

import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.Guia_Productos;
import cl.ediciones.model.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Guia_ProductosDAO {

    public ArrayList retrieve(int id_guia_despacho) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from guia_productos where id_guia_despacho = ?");
            ps.setInt(1, id_guia_despacho);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Guia_Productos guia_productos = new Guia_Productos();
                guia_productos.setCantidad(tabla.getInt("cantidad"));

                Guia_Despacho guia = new Guia_Despacho();
                guia.setId_guia_despacho(tabla.getInt("id_guia_despacho"));
                guia_productos.setGuia_despacho(guia);

                ProductosDAO prodDAO = new ProductosDAO();
                Productos productos = prodDAO.retrieve(tabla.getInt("id_productos"));
                guia_productos.setProductos(productos);

                lista.add(guia_productos);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void create(Guia_Productos guia_productos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into guia_productos (id_productos, id_guia_despacho, cantidad) values (?, ?, ?)");
            ps.setInt(1, guia_productos.getProductos().getId_productos());
            ps.setInt(2, guia_productos.getGuia_despacho().getId_guia_despacho());
            ps.setInt(3, guia_productos.getCantidad());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Guia_Productos guia_productos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update guia_productos set cantidad = ? where id_productos = ? and id_guia_despacho = ?");
            ps.setInt(1, guia_productos.getCantidad());
            ps.setInt(2, guia_productos.getProductos().getId_productos());
            ps.setInt(3, guia_productos.getGuia_despacho().getId_guia_despacho());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int id_productos, int id_guia_despacho) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from guia_productos where id_productos = ? and id_guia_despacho = ?");
            ps.setInt(1, id_productos);
            ps.setInt(2, id_guia_despacho);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
    
}
