package cl.ediciones.model.dao;

import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Cuota;
import cl.ediciones.model.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CuotaDAO {

    public Cuota retrive_Cuota(int id_cuota) throws SQLException {
        Cuota cuota = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuota where id_cuota = ?");
            ps.setInt(1, id_cuota);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cuota = new Cuota();
                cuota.setId_cuota(tabla.getInt("id_cuota"));
                cuota.setMonto(tabla.getInt("monto"));
                cuota.setRut_cliente(tabla.getInt("rut_cliente"));
                cuota.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cuota.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cuota.setFecha_cancela(tabla.getDate("fecha_cancela"));

                FacturaDAO facFAO = new FacturaDAO();
                Factura factura = facFAO.retrive(tabla.getInt("id_factura"));
                cuota.setFactura(factura);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cuota.setCuentas_base(cuenta_base);
                
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cuota;
    }

    public String create_Cuota(Cuota cuota) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into cuota (monto, rut_cliente, fecha_vencimiento, id_factura) values (?, ?, ?, ?)");
            ps.setInt(1, cuota.getMonto());
            ps.setInt(2, cuota.getRut_cliente());

            java.sql.Date fecha_vencimiento = new java.sql.Date(cuota.getFecha_vencimiento().getTime());
            ps.setDate(3, fecha_vencimiento);

            ps.setInt(4, cuota.getFactura().getId_factura());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String cancela_Cuota(Cuota cuota) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cuota set monto_cancelado = ?, fecha_cancela = ? where id_cuota = ?");
            ps.setInt(1, cuota.getMonto_cancelado());
            
            java.sql.Date fecha_cancela = new java.sql.Date(cuota.getFecha_cancela().getTime());
            ps.setDate(2, fecha_cancela);
            
            ps.setInt(3, cuota.getId_cuota());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String revertir_cancela_Cuota(int id_cuota) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cuota set monto_cancelado = 0, fecha_cancela = null where id_cuota = ?");
            ps.setInt(1, id_cuota);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void delete_Cuota(int id_cuota) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from cuota where id_cuota = ?");
            ps.setInt(1, id_cuota);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerCuota() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuota");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuota cuota = new Cuota();
                cuota.setId_cuota(tabla.getInt("id_cuota"));
                cuota.setMonto(tabla.getInt("monto"));
                cuota.setRut_cliente(tabla.getInt("rut_cliente"));
                cuota.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cuota.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cuota.setFecha_cancela(tabla.getDate("fecha_cancela"));

                FacturaDAO facFAO = new FacturaDAO();
                Factura factura = facFAO.retrive(tabla.getInt("id_factura"));
                cuota.setFactura(factura);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cuota.setCuentas_base(cuenta_base);
                
                lista.add(cuota);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Cuota_por_Factura(int id_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_vencimiento , '%m' ) mes_cuota_ven, DATE_FORMAT(fecha_vencimiento , '%Y' ) ano_cuota_ven from cuota where id_factura = ? order by fecha_vencimiento");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuota cuota = new Cuota();
                cuota.setId_cuota(tabla.getInt("id_cuota"));
                cuota.setMonto(tabla.getInt("monto"));
                cuota.setRut_cliente(tabla.getInt("rut_cliente"));
                cuota.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cuota.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cuota.setFecha_cancela(tabla.getDate("fecha_cancela"));
                cuota.setMes_cuota_ven(tabla.getInt("mes_cuota_ven"));
                cuota.setAno_cuota_ven(tabla.getInt("ano_cuota_ven"));

                FacturaDAO facFAO = new FacturaDAO();
                Factura factura = facFAO.retrive(tabla.getInt("id_factura"));
                cuota.setFactura(factura);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cuota.setCuentas_base(cuenta_base);
                
                lista.add(cuota);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Cuota_por_Factura_Pagado(int id_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_cancela , '%m' ) mes_cuota_pag, DATE_FORMAT(fecha_cancela , '%Y' ) ano_cuota_pag "
                    + "from cuota where id_factura = ? and fecha_cancela is not null order by fecha_cancela");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuota cuota = new Cuota();
                cuota.setId_cuota(tabla.getInt("id_cuota"));
                cuota.setMonto(tabla.getInt("monto"));
                cuota.setRut_cliente(tabla.getInt("rut_cliente"));
                cuota.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cuota.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cuota.setFecha_cancela(tabla.getDate("fecha_cancela"));
                cuota.setMes_cuota_pag(tabla.getInt("mes_cuota_pag"));
                cuota.setAno_cuota_pag(tabla.getInt("ano_cuota_pag"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                cuota.setFactura(factura);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cuota.setCuentas_base(cuenta_base);
                
                lista.add(cuota);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Cuota_para_Comprobante() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM cuota where id_cuota not in (select cp.id_cuota from comprobante cp where cp.id_cuota is not null) order by fecha_vencimiento");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuota cuota = new Cuota();
                cuota.setId_cuota(tabla.getInt("id_cuota"));
                cuota.setMonto(tabla.getInt("monto"));
                cuota.setRut_cliente(tabla.getInt("rut_cliente"));
                cuota.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cuota.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cuota.setFecha_cancela(tabla.getDate("fecha_cancela"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                cuota.setFactura(factura);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cuota.setCuentas_base(cuenta_base);
                
                lista.add(cuota);
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
