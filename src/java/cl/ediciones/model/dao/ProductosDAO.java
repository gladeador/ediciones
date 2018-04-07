package cl.ediciones.model.dao;

import cl.ediciones.model.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDAO {

    public ProductosDAO() {
    }

    public Productos retrieve(int id_productos) throws SQLException {
        Productos producto = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from productos where id_productos = ?");
            ps.setInt(1, id_productos);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                producto = new Productos();
                producto.setId_productos(tabla.getInt("id_productos"));
                producto.setDescripcion(tabla.getString("descripcion"));
                producto.setDesc_corta(tabla.getString("desc_corta"));
                producto.setValor_neto(tabla.getInt("valor_neto"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return producto;
    }

    public void create(Productos producto) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into productos (id_productos, descripcion, desc_corta, valor_neto) values (?, ?, ?, ?)");
            ps.setInt(1, producto.getId_productos());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getDesc_corta());
            ps.setInt(4, producto.getValor_neto());
            
            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Productos producto) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update productos set descripcion = ?, desc_corta = ?, valor_neto = ? where id_productos = ?");
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, producto.getDesc_corta());
            ps.setInt(3, producto.getValor_neto());
            ps.setInt(4, producto.getId_productos());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int id_productos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from productos where id_productos = ?");
            ps.setInt(1, id_productos);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Productos() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from productos order by descripcion");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Productos producto = new Productos();
                producto.setId_productos(tabla.getInt("id_productos"));
                producto.setDescripcion(tabla.getString("descripcion"));
                producto.setDesc_corta(tabla.getString("desc_corta"));
                producto.setValor_neto(tabla.getInt("valor_neto"));
                lista.add(producto);
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
