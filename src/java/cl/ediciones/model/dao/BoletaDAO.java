package cl.ediciones.model.dao;

import cl.ediciones.model.Clientes;
import cl.ediciones.model.Boleta;
import cl.ediciones.util.FechaStr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoletaDAO {

    public int create_Boleta(int num_boleta) throws SQLException {
        int id_boleta = 0;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = con.prepareStatement("insert into boleta (num_boleta, fecha_boleta, estado) values (?, now(), 'N')");
            ps.setInt(1, num_boleta);

            ps.executeUpdate();

            ps2 = con.prepareStatement("select id_boleta from boleta where num_boleta = ?");
            ps2.setInt(1, num_boleta);

            ResultSet tabla = ps2.executeQuery();
            if (tabla.next()) {
                id_boleta = tabla.getInt("id_boleta");
            }
            ps.close();
            ps2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return id_boleta;
    }

    public String valida_num_boleta(int num_boleta) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select num_boleta from boleta where num_boleta = ?");
            ps.setInt(1, num_boleta);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                ok = "ok";
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public Boleta retrive(int id_boleta) throws SQLException {
        Boleta boleta = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from boleta where id_boleta = ?");
            ps.setInt(1, id_boleta);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                boleta = new Boleta();
                boleta.setId_boleta(tabla.getInt("id_boleta"));
                boleta.setDescuento(tabla.getInt("descuento"));
                boleta.setEstado(tabla.getString("estado"));
                boleta.setFecha_boleta(tabla.getDate("fecha_boleta"));
                boleta.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                boleta.setIva(tabla.getInt("iva"));
                boleta.setNeto(tabla.getInt("neto"));
                boleta.setNum_boleta(tabla.getInt("num_boleta"));
                boleta.setRut_cliente(tabla.getInt("rut_cliente"));
                boleta.setSubtotal(tabla.getInt("sub_total"));
                boleta.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes cliente = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                boleta.setClientes(cliente);

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return boleta;
    }

    public void agrega_cliente_a_Boleta(int rut, int id_boleta) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update boleta set rut_cliente = ? where id_boleta = ?");
            ps.setInt(1, rut);
            ps.setInt(2, id_boleta);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void agrega_valores_a_Boleta(int sub_total, int desc, int neto, int iva, int total, int id_boleta) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update boleta set sub_total = ?, descuento = ?, neto = ?, iva = ?, total = ? where id_boleta = ?");
            ps.setInt(1, sub_total);
            ps.setInt(2, desc);
            ps.setInt(3, neto);
            ps.setInt(4, iva);
            ps.setInt(5, total);
            ps.setInt(6, id_boleta);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void modifica_fecha_Boleta(String fecha_boleta, int id_boleta) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update boleta set fecha_boleta = ? where id_boleta = ?");
//            java.sql.Date fecha = new java.sql.Date(FechaStr.stringToDate(fecha_boleta));

//            ps.setDate(3, fecha);
            ps.setDate(1, FechaStr.stringToDate(fecha_boleta));
            ps.setInt(2, id_boleta);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void graba_Envio(int id_tipo_envio, int id_boleta) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update boleta set id_tipo_envio = ? where id_boleta = ?");
            ps.setInt(1, id_tipo_envio);
            ps.setInt(2, id_boleta);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Boleta() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from boleta where estado = 'N' order by num_boleta");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Boleta boleta = new Boleta();
                boleta.setId_boleta(tabla.getInt("id_boleta"));
                boleta.setDescuento(tabla.getInt("descuento"));
                boleta.setEstado(tabla.getString("estado"));
                boleta.setFecha_boleta(tabla.getDate("fecha_boleta"));
                boleta.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                boleta.setIva(tabla.getInt("iva"));
                boleta.setNeto(tabla.getInt("neto"));
                boleta.setNum_boleta(tabla.getInt("num_boleta"));
                boleta.setRut_cliente(tabla.getInt("rut_cliente"));
                boleta.setSubtotal(tabla.getInt("sub_total"));
                boleta.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                boleta.setClientes(clientes);

                lista.add(boleta);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void update(Boleta boleta) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update boleta set subtotal = ?, descuento = ?, neto = ?, iva = ?, total= ?, fecha_boleta = ? where id_boleta = ? = ?");
            ps.setInt(1, boleta.getSubtotal());
            ps.setInt(2, boleta.getDescuento());
            ps.setInt(3, boleta.getIva());
            ps.setInt(4, boleta.getNeto());
            ps.setInt(5, boleta.getTotal());
            //  java.sql.Date(5, boleta.getNum_boleta());
            ps.setDate(6, null);
            ps.setInt(7, boleta.getId_boleta());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(String id_boleta) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            // ps = con.prepareStatement("delete proveedores where rut = ?");
            ps = con.prepareStatement("update boleta set estado ='A' where id_boleta = ?");
            ps.setInt(1, Integer.parseInt(id_boleta));

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
    
    public void emitir_Boleta(int id_boleta) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update boleta set estado = 'E' where id_boleta = ?");
            ps.setInt(1, id_boleta);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

}
