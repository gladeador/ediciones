package cl.ediciones.model.dao;

import cl.ediciones.model.Clientes;
import cl.ediciones.model.NotaCredito;
import cl.ediciones.util.FechaStr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotaCreditoDAO {

    public int create_NotaCredito(int num_notacredito) throws SQLException {
        int id_notacredito = 0;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = con.prepareStatement("insert into notacredito (num_notacredito, estado) values (?, 'N')");
            ps.setInt(1, num_notacredito);

            ps.executeUpdate();

            ps2 = con.prepareStatement("select id_notacredito from notacredito where num_notacredito = ?");
            ps2.setInt(1, num_notacredito);

            ResultSet tabla = ps2.executeQuery();
            if (tabla.next()) {
                id_notacredito = tabla.getInt("id_notacredito");
            }
            ps.close();
            ps2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return id_notacredito;
    }

    public String valida_num_notacredito(int num_notacredito) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select num_notacredito from notacredito where num_notacredito = ?");
            ps.setInt(1, num_notacredito);

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

    public NotaCredito retrive(int id_notacredito) throws SQLException {
        NotaCredito notacredito = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from notacredito where id_notacredito = ?");
            ps.setInt(1, id_notacredito);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                notacredito = new NotaCredito();
                notacredito.setId_notacredito(tabla.getInt("id_notacredito"));
                notacredito.setDescuento(tabla.getInt("descuento"));
                notacredito.setEstado(tabla.getString("estado"));
                notacredito.setFecha_notacredito(tabla.getDate("fecha_notacredito"));
                notacredito.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                notacredito.setIva(tabla.getInt("iva"));
                notacredito.setNeto(tabla.getInt("neto"));
                notacredito.setNum_notacredito(tabla.getInt("num_notacredito"));
                notacredito.setRut_cliente(tabla.getInt("rut_cliente"));
                notacredito.setSubtotal(tabla.getInt("sub_total"));
                notacredito.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes cliente = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                notacredito.setClientes(cliente);


            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return notacredito;
    }

    public void agrega_cliente_a_NotaCredito(int rut, int id_notacredito) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update notacredito set rut_cliente = ? where id_notacredito = ?");
            ps.setInt(1, rut);
            ps.setInt(2, id_notacredito);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void agrega_valores_a_NotaCredito(int sub_total, int desc, int neto, int iva, int total, int id_notacredito) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update notacredito set sub_total = ?, descuento = ?, neto = ?, iva = ?, total = ? where id_notacredito = ?");
            ps.setInt(1, sub_total);
            ps.setInt(2, desc);
            ps.setInt(3, neto);
            ps.setInt(4, iva);
            ps.setInt(5, total);
            ps.setInt(6, id_notacredito);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void modifica_fecha_NotaCredito(String fecha_notacredito, int id_notacredito) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update notacredito set fecha_notacredito = ? where id_notacredito = ?");
//            java.sql.Date fecha = new java.sql.Date(FechaStr.stringToDate(fecha_notacredito));

//            ps.setDate(3, fecha);
            ps.setDate(1, FechaStr.stringToDate(fecha_notacredito));
            ps.setInt(2, id_notacredito);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void graba_Envio(int id_tipo_envio, int id_notacredito) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update notacredito set id_tipo_envio = ? where id_notacredito = ?");
            ps.setInt(1, id_tipo_envio);
            ps.setInt(2, id_notacredito);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_NotaCredito() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from notacredito where estado = 'N' order by num_notacredito");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                NotaCredito notacredito = new NotaCredito();
                notacredito.setId_notacredito(tabla.getInt("id_notacredito"));
                notacredito.setDescuento(tabla.getInt("descuento"));
                notacredito.setEstado(tabla.getString("estado"));
                notacredito.setFecha_notacredito(tabla.getDate("fecha_notacredito"));
                notacredito.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                notacredito.setIva(tabla.getInt("iva"));
                notacredito.setNeto(tabla.getInt("neto"));
                notacredito.setNum_notacredito(tabla.getInt("num_notacredito"));
                notacredito.setRut_cliente(tabla.getInt("rut_cliente"));
                notacredito.setSubtotal(tabla.getInt("sub_total"));
                notacredito.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                notacredito.setClientes(clientes);

                lista.add(notacredito);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void update(NotaCredito notacredito) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            
           
            ps = con.prepareStatement("update notacredito set sub_total = ?, descuento = ?, neto = ?, iva = ?, total= ?, fecha_notacredito = ?, id_factura = ? where id_notacredito = ? ");
            ps.setInt(1, notacredito.getSubtotal());
            ps.setInt(2, notacredito.getDescuento());
            ps.setInt(3, notacredito.getIva());
            ps.setInt(4, notacredito.getNeto());
            ps.setInt(5, notacredito.getTotal());
            //  java.sql.Date(5, notacredito.getNum_notacredito());
            java.sql.Date fecha = new java.sql.Date(notacredito.getFecha_notacredito().getTime());
            ps.setDate(6, fecha);
            ps.setInt(7, notacredito.getId_factura());
            ps.setInt(8, notacredito.getId_notacredito());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

        public void delete(String id_notacredito) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            // ps = con.prepareStatement("delete proveedores where rut = ?");
            ps = con.prepareStatement("update notacredito set estado ='A' where id_notacredito = ?");
            ps.setInt(1, Integer.parseInt(id_notacredito));

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
}
