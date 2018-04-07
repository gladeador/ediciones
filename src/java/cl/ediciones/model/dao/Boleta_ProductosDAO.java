package cl.ediciones.model.dao;

import cl.ediciones.model.Boleta;
import cl.ediciones.model.Boleta_Productos;
import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Boleta_ProductosDAO {

    public ArrayList retrieve(int id_boleta) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from boleta_productos where id_boleta = ?");
            ps.setInt(1, id_boleta);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Boleta_Productos boleta_productos = new Boleta_Productos();
                boleta_productos.setCantidad(tabla.getInt("cantidad"));

                BoletaDAO facDAO = new BoletaDAO();
                Boleta boleta = facDAO.retrive(tabla.getInt("id_boleta"));
                boleta_productos.setBoleta(boleta);

                ProductosDAO prodDAO = new ProductosDAO();
                Productos productos = prodDAO.retrieve(tabla.getInt("id_productos"));
                boleta_productos.setProductos(productos);

                lista.add(boleta_productos);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void create(int id_producto, int id_boleta, int cantidad) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into boleta_productos (id_productos, id_boleta, cantidad) values (?, ?, ?)");
            ps.setInt(1, id_producto);
            ps.setInt(2, id_boleta);
            ps.setInt(3, cantidad);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Boleta_Productos boleta_productos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update boleta_productos set cantidad = ? where id_productos = ? and id_boleta_despacho = ?");
            ps.setInt(1, boleta_productos.getCantidad());
         

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int id_boleta,int id_productos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from boleta_productos where id_boleta = ? and id_productos = ?");
            ps.setInt(1, id_boleta);
            ps.setInt(2, id_productos);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
}
