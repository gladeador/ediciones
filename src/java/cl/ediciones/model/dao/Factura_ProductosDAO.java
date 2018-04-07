package cl.ediciones.model.dao;

import cl.ediciones.model.Factura;
import cl.ediciones.model.Factura_Productos;
import cl.ediciones.model.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Factura_ProductosDAO {

    public ArrayList retrieve(int id_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura_productos fp, productos p where id_factura = ? and fp.id_productos = p.id_productos order by p.descripcion");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura_Productos factura_productos = new Factura_Productos();
                factura_productos.setCantidad(tabla.getInt("cantidad"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                factura_productos.setFactura(factura);

                Productos producto = new Productos();
                producto.setId_productos(tabla.getInt("id_productos"));
                producto.setDescripcion(tabla.getString("descripcion"));
                producto.setDesc_corta(tabla.getString("desc_corta"));
                producto.setValor_neto(tabla.getInt("valor_neto"));
                factura_productos.setProductos(producto);

                lista.add(factura_productos);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void create(int id_producto, int id_factura, int cantidad) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into factura_productos (id_productos, id_factura, cantidad) values (?, ?, ?)");
            ps.setInt(1, id_producto);
            ps.setInt(2, id_factura);
            ps.setInt(3, cantidad);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void updateCantidad(int id_producto, int id_factura, int cantidad) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura_productos set cantidad = ? where id_productos = ? and id_factura = ?");
            ps.setInt(1, cantidad);
            ps.setInt(2, id_producto);
            ps.setInt(3, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int id_factura, int id_productos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from factura_productos where id_factura = ? and id_productos = ?");
            ps.setInt(1, id_factura);
            ps.setInt(2, id_productos);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete_por_Factura(int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from factura_productos where id_factura = ?");
            ps.setInt(1, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public int suma_productos_por_Factura(int id_factura, int id_productos) throws SQLException {
        int cantidad = 0;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT sum( cantidad ) cantidad FROM factura_productos WHERE id_factura = ? AND id_productos = ?");
            ps.setInt(1, id_factura);
            ps.setInt(2, id_productos);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cantidad = tabla.getInt("cantidad");
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cantidad;
    }

    public int suma_productos_por_Producto(int id_productos) throws SQLException {
        int cantidad = 0;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select\n" +
"(if(exists(SELECT ifnull(sum(cantidad),0) as cantidad FROM factura_productos T0 WHERE id_productos = ?),(SELECT ifnull(sum(cantidad),0) as cantidad FROM factura_productos T0 WHERE id_productos = ?),0)\n" +
"+\n" +
"if(exists(SELECT ifnull(sum(cantidad),0) as cantidad FROM boleta_productos WHERE id_productos = ?),(SELECT ifnull(sum(cantidad),0) as cantidad FROM boleta_productos WHERE id_productos = ?),0)) as cantidad");
            ps.setInt(1, id_productos);
            ps.setInt(2, id_productos);
            ps.setInt(3, id_productos);
            ps.setInt(4, id_productos);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cantidad = tabla.getInt("cantidad");
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cantidad;
    }
}
