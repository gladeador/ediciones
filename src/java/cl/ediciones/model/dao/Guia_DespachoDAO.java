package cl.ediciones.model.dao;

import cl.ediciones.model.Clientes;
import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.util.FechaStr;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Guia_DespachoDAO {

    public Guia_DespachoDAO() {
    }

    public Guia_Despacho retrive_Guia_Despacho(int id_guia_despacho) throws SQLException {
        Guia_Despacho guia = new Guia_Despacho();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from guia_despacho where id_guia_despacho = ?");
            ps.setInt(1, id_guia_despacho);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                guia = new Guia_Despacho();
                guia.setId_guia_despacho(tabla.getInt("id_guia_despacho"));
                guia.setNum_guia(tabla.getInt("num_guia"));
                guia.setFecha_guia(tabla.getDate("fecha_guia"));
                guia.setSub_total(tabla.getInt("sub_total"));
                guia.setDescuento(tabla.getInt("descuento"));
                guia.setNeto(tabla.getInt("neto"));
                guia.setIva(tabla.getInt("iva"));
                guia.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                guia.setClientes(clientes);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return guia;
    }

    public int create_Guia_Despacho(int num_guia, String fecha_guia) throws SQLException {
        int id_guia_despacho = 0;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = con.prepareStatement("insert into guia_despacho (num_guia, fecha_guia, estado) values (?, ?, 'N')");
            ps.setInt(1, num_guia);
            ps.setDate(2, FechaStr.stringToDate(fecha_guia));

            ps.executeUpdate();

            ps2 = con.prepareStatement("select id_guia_despacho from guia_despacho where num_guia = ?");
            ps2.setInt(1, num_guia);

            ResultSet tabla = ps2.executeQuery();
            if (tabla.next()) {
                id_guia_despacho = tabla.getInt("id_guia_despacho");
            }
            ps.close();
            ps2.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return id_guia_despacho;
    }

    public String valida_num_guia(int num_guia) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select num_guia id from guia_despacho where num_guia = ? and estado in ('N', 'E')");
            ps.setInt(1, num_guia);

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

    public void agrega_cliente_a_Guia(int rut, int id_guia_despacho) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update guia_despacho set rut_cliente = ? where id_guia_despacho = ?");
            ps.setInt(1, rut);
            ps.setInt(2, id_guia_despacho);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void emitir_Guia_Despacho(int id_guia_despacho) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update guia_despacho set estado = 'E' where id_guia_despacho = ?");
            ps.setInt(1, id_guia_despacho);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void modifica_fecha_Guia(String fecha_guia, int id_guia_despacho) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update guia_despacho set fecha_guia = ? where id_guia_despacho = ?");
            ps.setString(1, fecha_guia);
            ps.setInt(2, id_guia_despacho);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update_valores(int sub_total, int desc, int neto, int iva, int total, int id_guia_despacho) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update guia_despacho set sub_total = ?, descuento = ?, neto = ?, iva = ?, total = ? where id_guia_despacho = ?");
            ps.setInt(1, sub_total);
            ps.setInt(2, desc);
            ps.setInt(3, neto);
            ps.setInt(4, iva);
            ps.setInt(5, total);
            ps.setInt(6, id_guia_despacho);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void agrega_Factura(int id_guia_despacho, int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update guia_despacho set id_factura = ? where id_guia_despacho = ?");
            ps.setInt(1, id_factura);
            ps.setInt(2, id_guia_despacho);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete_guia(int id_guia_despacho) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update guia_despacho set estado = 'X' where id_guia_despacho = ?");
            ps.setInt(1, id_guia_despacho);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Guia_Despacho() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from guia_despacho where estado = 'N' order by num_guia");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Guia_Despacho guia = new Guia_Despacho();
                guia.setId_guia_despacho(tabla.getInt("id_guia_despacho"));
                guia.setNum_guia(tabla.getInt("num_guia"));
                guia.setFecha_guia(tabla.getDate("fecha_guia"));
                guia.setSub_total(tabla.getInt("sub_total"));
                guia.setDescuento(tabla.getInt("descuento"));
                guia.setNeto(tabla.getInt("neto"));
                guia.setIva(tabla.getInt("iva"));
                guia.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                guia.setClientes(clientes);

                lista.add(guia);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Guia_Despacho_para_Facturacion() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from guia_despacho where estado = 'E' and id_factura is null order by num_guia");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Guia_Despacho guia = new Guia_Despacho();
                guia.setId_guia_despacho(tabla.getInt("id_guia_despacho"));
                guia.setNum_guia(tabla.getInt("num_guia"));
                guia.setFecha_guia(tabla.getDate("fecha_guia"));
                guia.setSub_total(tabla.getInt("sub_total"));
                guia.setDescuento(tabla.getInt("descuento"));
                guia.setNeto(tabla.getInt("neto"));
                guia.setIva(tabla.getInt("iva"));
                guia.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                guia.setClientes(clientes);

                lista.add(guia);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Guia_Despacho_por_Factura(int id_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from guia_despacho where id_factura = ? order by num_guia");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Guia_Despacho guia = new Guia_Despacho();
                guia.setId_guia_despacho(tabla.getInt("id_guia_despacho"));
                guia.setNum_guia(tabla.getInt("num_guia"));
                guia.setFecha_guia(tabla.getDate("fecha_guia"));
                guia.setSub_total(tabla.getInt("sub_total"));
                guia.setDescuento(tabla.getInt("descuento"));
                guia.setNeto(tabla.getInt("neto"));
                guia.setIva(tabla.getInt("iva"));
                guia.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                guia.setClientes(clientes);

                lista.add(guia);
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
