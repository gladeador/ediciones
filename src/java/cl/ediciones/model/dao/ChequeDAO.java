package cl.ediciones.model.dao;

import cl.ediciones.model.Banco;
import cl.ediciones.model.Cheque;
import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Estado_Cheque;
import cl.ediciones.model.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChequeDAO {

    public Cheque retrive_Cheque_por_Numero(int num_cheque) throws SQLException {
        Cheque cheque = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cheque where num_cheque = ?");
            ps.setInt(1, num_cheque);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cheque = new Cheque();
                cheque.setId_cheque(tabla.getInt("id_cheque"));
                cheque.setNum_cheque(tabla.getInt("num_cheque"));
                cheque.setMonto(tabla.getInt("monto"));
                cheque.setRut_cliente(tabla.getInt("rut_cliente"));
                cheque.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque.setFecha_cancela(tabla.getDate("fecha_cancela"));

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque.setBanco(banco);

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                cheque.setFactura(factura);

                Estado_ChequeDAO epDAO = new Estado_ChequeDAO();
                Estado_Cheque estado = epDAO.retrive_Estado_Cheque(tabla.getString("estado"));
                cheque.setEstado_cheque(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque.setCuentas_base(cuenta_base);
                
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cheque;
    }

    public Cheque retrive_Cheque(int id_cheque) throws SQLException {
        Cheque cheque = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cheque where id_cheque = ?");
            ps.setInt(1, id_cheque);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cheque = new Cheque();
                cheque.setId_cheque(tabla.getInt("id_cheque"));
                cheque.setNum_cheque(tabla.getInt("num_cheque"));
                cheque.setMonto(tabla.getInt("monto"));
                cheque.setRut_cliente(tabla.getInt("rut_cliente"));
                cheque.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque.setFecha_cancela(tabla.getDate("fecha_cancela"));

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque.setBanco(banco);

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                cheque.setFactura(factura);

                Estado_ChequeDAO epDAO = new Estado_ChequeDAO();
                Estado_Cheque estado = epDAO.retrive_Estado_Cheque(tabla.getString("estado"));
                cheque.setEstado_cheque(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque.setCuentas_base(cuenta_base);
                
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cheque;
    }

    public String create_Cheque(Cheque cheque) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into cheque (num_cheque, id_banco, monto, rut_cliente, fecha_recepcion_documento, fecha_vencimiento, id_factura) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, cheque.getNum_cheque());
            ps.setInt(2, cheque.getBanco().getId_banco());
            ps.setInt(3, cheque.getMonto());
            ps.setInt(4, cheque.getRut_cliente());

            java.sql.Date fecha_recepcion_documento = new java.sql.Date(cheque.getFecha_recepcion_documento().getTime());
            ps.setDate(5, fecha_recepcion_documento);

            java.sql.Date fecha_vencimiento = new java.sql.Date(cheque.getFecha_vencimiento().getTime());
            ps.setDate(6, fecha_vencimiento);

            ps.setInt(7, cheque.getFactura().getId_factura());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String cancela_Cheque(Cheque cheque) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cheque set monto_cancelado = ?, fecha_cancela = ?, estado = 'PA' where id_cheque = ?");
            ps.setInt(1, cheque.getMonto_cancelado());
            
            java.sql.Date fecha_cancela = new java.sql.Date(cheque.getFecha_cancela().getTime());
            ps.setDate(2, fecha_cancela);
            
            ps.setInt(3, cheque.getId_cheque());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String protesta_Cheque(int id_cheque) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cheque set estado = 'PR' where id_cheque = ?");
            ps.setInt(1, id_cheque);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String revertir_protesta_Cheque(int id_cheque) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cheque set estado = 'N' where id_cheque = ?");
            ps.setInt(1, id_cheque);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String revertir_cancela_Cheque(int id_cheque) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cheque set monto_cancelado = 0, fecha_cancela = null, estado = 'N' where id_cheque = ?");
            ps.setInt(1, id_cheque);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void delete_Cheque(int id_cheque) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from cheque where id_cheque = ?");
            ps.setInt(1, id_cheque);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerCheque() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cheque");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque cheque = new Cheque();
                cheque.setId_cheque(tabla.getInt("id_cheque"));
                cheque.setNum_cheque(tabla.getInt("num_cheque"));
                cheque.setMonto(tabla.getInt("monto"));
                cheque.setRut_cliente(tabla.getInt("rut_cliente"));
                cheque.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque.setFecha_cancela(tabla.getDate("fecha_cancela"));

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque.setBanco(banco);

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                cheque.setFactura(factura);

                Estado_ChequeDAO epDAO = new Estado_ChequeDAO();
                Estado_Cheque estado = epDAO.retrive_Estado_Cheque(tabla.getString("estado"));
                cheque.setEstado_cheque(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque.setCuentas_base(cuenta_base);
                
                lista.add(cheque);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cheque_por_Factura(int id_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_vencimiento , '%m' ) mes_cheque_ven, DATE_FORMAT(fecha_vencimiento , '%Y' ) ano_cheque_ven from cheque where id_factura = ? order by fecha_vencimiento");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque cheque = new Cheque();
                cheque.setId_cheque(tabla.getInt("id_cheque"));
                cheque.setNum_cheque(tabla.getInt("num_cheque"));
                cheque.setMonto(tabla.getInt("monto"));
                cheque.setRut_cliente(tabla.getInt("rut_cliente"));
                cheque.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque.setFecha_cancela(tabla.getDate("fecha_cancela"));
                cheque.setMes_cheque_ven(tabla.getInt("mes_cheque_ven"));
                cheque.setAno_cheque_ven(tabla.getInt("ano_cheque_ven"));

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque.setBanco(banco);

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                cheque.setFactura(factura);

                Estado_ChequeDAO epDAO = new Estado_ChequeDAO();
                Estado_Cheque estado = epDAO.retrive_Estado_Cheque(tabla.getString("estado"));
                cheque.setEstado_cheque(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque.setCuentas_base(cuenta_base);
                
                lista.add(cheque);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Cheque_por_Factura_Pagado(int id_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_cancela , '%m' ) mes_cheque_pag, DATE_FORMAT(fecha_cancela , '%Y' ) ano_cheque_pag, estado "
                    + "from cheque where id_factura = ? and fecha_cancela is not null and estado = 'PA' order by fecha_cancela");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque cheque = new Cheque();
                cheque.setId_cheque(tabla.getInt("id_cheque"));
                cheque.setNum_cheque(tabla.getInt("num_cheque"));
                cheque.setMonto(tabla.getInt("monto"));
                cheque.setRut_cliente(tabla.getInt("rut_cliente"));
                cheque.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque.setFecha_cancela(tabla.getDate("fecha_cancela"));
                cheque.setMes_cheque_pag(tabla.getInt("mes_cheque_pag"));
                cheque.setAno_cheque_pag(tabla.getInt("ano_cheque_pag"));

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque.setBanco(banco);

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                cheque.setFactura(factura);

                Estado_ChequeDAO epDAO = new Estado_ChequeDAO();
                Estado_Cheque estado = epDAO.retrive_Estado_Cheque(tabla.getString("estado"));
                cheque.setEstado_cheque(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque.setCuentas_base(cuenta_base);
                
                lista.add(cheque);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Cheque_para_Comprobante() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM cheque where id_cheque not in (select cp.id_cheque from comprobante cp where cp.id_cheque is not null) order by fecha_recepcion_documento");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque cheque = new Cheque();
                cheque.setId_cheque(tabla.getInt("id_cheque"));
                cheque.setNum_cheque(tabla.getInt("num_cheque"));
                cheque.setMonto(tabla.getInt("monto"));
                cheque.setRut_cliente(tabla.getInt("rut_cliente"));
                cheque.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque.setFecha_cancela(tabla.getDate("fecha_cancela"));

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque.setBanco(banco);

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                cheque.setFactura(factura);

                Estado_ChequeDAO epDAO = new Estado_ChequeDAO();
                Estado_Cheque estado = epDAO.retrive_Estado_Cheque(tabla.getString("estado"));
                cheque.setEstado_cheque(estado);
                
                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque.setCuentas_base(cuenta_base);
                
                lista.add(cheque);
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
