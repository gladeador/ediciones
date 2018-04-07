package cl.ediciones.model.dao;

import cl.ediciones.model.Factura;
import cl.ediciones.model.Factura_Productos;
import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.NotaCredito;
import cl.ediciones.model.NotaCredito_Productos;
import cl.ediciones.model.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotaCredito_ProductosDAO {

    public ArrayList retrieve(int id_notacredito) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from notacredito_productos where id_notacredito = ?");
            ps.setInt(1, id_notacredito);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                NotaCredito_Productos notacredito_productos = new NotaCredito_Productos();
                notacredito_productos.setCantidad(tabla.getInt("cantidad"));

                NotaCreditoDAO ncDAO = new NotaCreditoDAO();
                NotaCredito notacredito = ncDAO.retrive(tabla.getInt("id_notacredito"));
                notacredito_productos.setNotacredito(notacredito);

                ProductosDAO prodDAO = new ProductosDAO();
                Productos productos = prodDAO.retrieve(tabla.getInt("id_productos"));
                notacredito_productos.setProductos(productos);

                lista.add(notacredito_productos);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void create(int id_producto,int id_notacredito,int cantidad) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into notacredito_productos (id_productos, id_notacredito, cantidad) values (?, ?, ?)");
            ps.setInt(1, id_producto);
            ps.setInt(2, id_notacredito);
            ps.setInt(3, cantidad);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Factura_Productos notacredito_productos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update notacredito_productos set cantidad = ? where id_productos = ? and id_notacredito_despacho = ?");
            ps.setInt(1, notacredito_productos.getCantidad());
         

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int id_notacredito,int id_productos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from notacredito_productos where id_notacredito = ? and id_productos = ?");
            ps.setInt(1, id_notacredito);
            ps.setInt(2, id_productos);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
}
