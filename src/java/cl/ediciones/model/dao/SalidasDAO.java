package cl.ediciones.model.dao;

import cl.ediciones.model.Productos;
import cl.ediciones.model.Salidas;
import cl.ediciones.model.Tipo_Salida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalidasDAO {

    public Salidas retrive(int id_salida) throws SQLException {
        Salidas salidas = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from salidas where id_salida = ?");
            ps.setInt(1, id_salida);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                salidas = new Salidas();
                salidas.setId_salida(tabla.getInt("id_salida"));
                salidas.setFecha_salida(tabla.getDate("fecha_salida"));
                salidas.setCantidad(tabla.getInt("cantidad"));
                salidas.setObservaciones(tabla.getString("observaciones"));

                Tipo_SalidaDAO t_sDAO = new Tipo_SalidaDAO();
                Tipo_Salida tipo = t_sDAO.retrieve(tabla.getInt("id_tipo_salida"));
                salidas.setTipo_salida(tipo);

                ProductosDAO proDAO = new ProductosDAO();
                Productos productos = proDAO.retrieve(tabla.getInt("id_producto"));
                salidas.setProductos(productos);

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return salidas;
    }

    public int suma_Salidas(int id_producto, int id_tipo_salida) throws SQLException {
        int cantidad = 0;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select sum(cantidad) as cantidad from salidas where id_producto = ? and id_tipo_salida = ?");
            ps.setInt(1, id_producto);
            ps.setInt(2, id_tipo_salida);

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

    public void create(Salidas salidas) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into salidas (id_tipo_salida, id_producto, cantidad, fecha_salida, observaciones) values (?, ?, ?, ?, ?)");
            ps.setInt(1, salidas.getTipo_salida().getId_tipo_salida());
            ps.setInt(2, salidas.getProductos().getId_productos());
            ps.setInt(3, salidas.getCantidad());

            java.sql.Date fecha1 = new java.sql.Date(salidas.getFecha_salida().getTime());
            ps.setDate(4, fecha1);

            ps.setString(5, salidas.getObservaciones());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Salidas salidas) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update salidas set id_tipo_salida = ?, id_producto = ?, cantidad = ?, fecha_salida = ?, observaciones = ? where id_salida = ?");
            ps.setInt(1, salidas.getTipo_salida().getId_tipo_salida());
            ps.setInt(2, salidas.getProductos().getId_productos());
            ps.setInt(3, salidas.getCantidad());

            java.sql.Date fecha1 = new java.sql.Date(salidas.getFecha_salida().getTime());
            ps.setDate(4, fecha1);

            ps.setString(5, salidas.getObservaciones());
            ps.setInt(6, salidas.getId_salida());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int id_salida) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from salidas where id_salida = ?");
            ps.setInt(1, id_salida);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList Detalle_Salidas(String id_tipo_salida, String id_producto) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            String sql = "select * from salidas where ";
            if (id_producto == null || id_producto.equals("")) {
                sql = sql + "id_producto like '%" + id_producto + "' ";
            } else {
                sql = sql + "id_producto = " + id_producto + " ";
            }
            if (id_tipo_salida == null || id_tipo_salida.equals("")) {
                sql = sql + "and id_tipo_salida like '%" + id_tipo_salida + "' ";
            } else {
                sql = sql + "and id_tipo_salida = " + id_tipo_salida + " ";
            }
            sql = sql + " order by fecha_salida, id_tipo_salida";
            ps = con.prepareStatement(sql);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Salidas salidas = new Salidas();
                salidas.setId_salida(tabla.getInt("id_salida"));
                salidas.setFecha_salida(tabla.getDate("fecha_salida"));
                salidas.setCantidad(tabla.getInt("cantidad"));
                salidas.setObservaciones(tabla.getString("observaciones"));

                Tipo_SalidaDAO t_sDAO = new Tipo_SalidaDAO();
                Tipo_Salida tipo = t_sDAO.retrieve(tabla.getInt("id_tipo_salida"));
                salidas.setTipo_salida(tipo);

                ProductosDAO proDAO = new ProductosDAO();
                Productos productos = proDAO.retrieve(tabla.getInt("id_producto"));
                salidas.setProductos(productos);

                lista.add(salidas);
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
