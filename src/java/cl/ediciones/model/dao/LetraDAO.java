package cl.ediciones.model.dao;

import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Estado_Letra;
import cl.ediciones.model.Letra;
import cl.ediciones.model.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LetraDAO {

    public Letra retrive_Letra(int num_letra) throws SQLException {
        Letra letra = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from letra where num_letra = ?");
            ps.setInt(1, num_letra);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                letra = new Letra();
                letra.setId_letra(tabla.getInt("id_letra"));
                letra.setNum_letra(tabla.getInt("num_letra"));
                letra.setMonto(tabla.getInt("monto"));
                letra.setRut_cliente(tabla.getInt("rut_cliente"));
                letra.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                letra.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                letra.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                letra.setFecha_cancela(tabla.getDate("fecha_cancela"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                letra.setFactura(factura);

                Estado_LetraDAO epDAO = new Estado_LetraDAO();
                Estado_Letra estado = epDAO.retrive_Estado_Letra(tabla.getString("estado"));
                letra.setEstado_letra(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                letra.setCuentas_base(cuenta_base);
                
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return letra;
    }

    public String create_Letra(Letra letra) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into letra (num_letra, monto, rut_cliente, fecha_recepcion_documento, fecha_vencimiento, id_factura) values (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, letra.getNum_letra());
            ps.setInt(2, letra.getMonto());
            ps.setInt(3, letra.getRut_cliente());

            java.sql.Date fecha_recepcion_documento = new java.sql.Date(letra.getFecha_recepcion_documento().getTime());
            ps.setDate(4, fecha_recepcion_documento);

            java.sql.Date fecha_vencimiento = new java.sql.Date(letra.getFecha_vencimiento().getTime());
            ps.setDate(5, fecha_vencimiento);

            ps.setInt(6, letra.getFactura().getId_factura());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String cancela_Letra(Letra letra) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update letra set monto_cancelado = ?, fecha_cancela = ?, estado = 'PA' where id_letra = ?");
            ps.setInt(1, letra.getMonto_cancelado());
            
            java.sql.Date fecha_cancela = new java.sql.Date(letra.getFecha_cancela().getTime());
            ps.setDate(2, fecha_cancela);
            
            ps.setInt(3, letra.getId_letra());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String protesta_Letra(int id_letra) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update letra set estado = 'PR' where id_letra = ?");
            ps.setInt(1, id_letra);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String revertir_protesta_Letra(int id_letra) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update letra set estado = 'N' where id_letra = ?");
            ps.setInt(1, id_letra);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String revertir_cancela_Letra(int id_letra) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update letra set monto_cancelado = 0, fecha_cancela = null, estado = 'N' where id_letra = ?");
            ps.setInt(1, id_letra);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void delete_Letra(int id_letra) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from letra where id_letra = ?");
            ps.setInt(1, id_letra);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerLetra() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from letra");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Letra letra = new Letra();
                letra.setId_letra(tabla.getInt("id_letra"));
                letra.setNum_letra(tabla.getInt("num_letra"));
                letra.setMonto(tabla.getInt("monto"));
                letra.setRut_cliente(tabla.getInt("rut_cliente"));
                letra.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                letra.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                letra.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                letra.setFecha_cancela(tabla.getDate("fecha_cancela"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                letra.setFactura(factura);

                Estado_LetraDAO epDAO = new Estado_LetraDAO();
                Estado_Letra estado = epDAO.retrive_Estado_Letra(tabla.getString("estado"));
                letra.setEstado_letra(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                letra.setCuentas_base(cuenta_base);
                
                lista.add(letra);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Letra_por_Factura(int id_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_vencimiento , '%m' ) mes_letra_ven, DATE_FORMAT(fecha_vencimiento , '%Y' ) ano_letra_ven from letra where id_factura = ? order by fecha_vencimiento");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Letra letra = new Letra();
                letra.setId_letra(tabla.getInt("id_letra"));
                letra.setNum_letra(tabla.getInt("num_letra"));
                letra.setMonto(tabla.getInt("monto"));
                letra.setRut_cliente(tabla.getInt("rut_cliente"));
                letra.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                letra.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                letra.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                letra.setFecha_cancela(tabla.getDate("fecha_cancela"));
                letra.setMes_letra_ven(tabla.getInt("mes_letra_ven"));
                letra.setAno_letra_ven(tabla.getInt("ano_letra_ven"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                letra.setFactura(factura);

                Estado_LetraDAO epDAO = new Estado_LetraDAO();
                Estado_Letra estado = epDAO.retrive_Estado_Letra(tabla.getString("estado"));
                letra.setEstado_letra(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                letra.setCuentas_base(cuenta_base);
                
                lista.add(letra);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Letra_por_Factura_Pagado(int id_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_cancela , '%m' ) mes_letra_pag, DATE_FORMAT(fecha_cancela , '%Y' ) ano_letra_pag "
                    + "from letra where id_factura = ? and fecha_cancela is not null order by fecha_cancela");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Letra letra = new Letra();
                letra.setId_letra(tabla.getInt("id_letra"));
                letra.setNum_letra(tabla.getInt("num_letra"));
                letra.setMonto(tabla.getInt("monto"));
                letra.setRut_cliente(tabla.getInt("rut_cliente"));
                letra.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                letra.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                letra.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                letra.setFecha_cancela(tabla.getDate("fecha_cancela"));
                letra.setMes_letra_pag(tabla.getInt("mes_letra_pag"));
                letra.setAno_letra_pag(tabla.getInt("ano_letra_pag"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                letra.setFactura(factura);

                Estado_LetraDAO epDAO = new Estado_LetraDAO();
                Estado_Letra estado = epDAO.retrive_Estado_Letra(tabla.getString("estado"));
                letra.setEstado_letra(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                letra.setCuentas_base(cuenta_base);
                
                lista.add(letra);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Letra_para_Comprobante() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM letra where id_letra not in (select cp.id_letra from comprobante cp where cp.id_letra is not null) order by fecha_recepcion_documento");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Letra letra = new Letra();
                letra.setId_letra(tabla.getInt("id_letra"));
                letra.setNum_letra(tabla.getInt("num_letra"));
                letra.setMonto(tabla.getInt("monto"));
                letra.setRut_cliente(tabla.getInt("rut_cliente"));
                letra.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                letra.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                letra.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                letra.setFecha_cancela(tabla.getDate("fecha_cancela"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                letra.setFactura(factura);

                Estado_LetraDAO epDAO = new Estado_LetraDAO();
                Estado_Letra estado = epDAO.retrive_Estado_Letra(tabla.getString("estado"));
                letra.setEstado_letra(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                letra.setCuentas_base(cuenta_base);
                
                lista.add(letra);
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
