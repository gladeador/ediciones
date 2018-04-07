package cl.ediciones.model.dao;

import cl.ediciones.model.Banco;
import cl.ediciones.model.Cheque_Pago;
import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cheque_PagoDAO {

    public Cheque_Pago retrive_Cheque_Pago(int id_cheque_pago) throws SQLException {
        Cheque_Pago cheque_pago = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cheque_pago where id_cheque_pago = ?");
            ps.setInt(1, id_cheque_pago);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cheque_pago = new Cheque_Pago();
                cheque_pago.setId_cheque_pago(tabla.getInt("id_cheque_pago"));
                cheque_pago.setNum_cheque_pago(tabla.getInt("num_cheque_pago"));
                cheque_pago.setMonto(tabla.getInt("monto"));
                cheque_pago.setNum_factura_compra(tabla.getInt("num_factura_compra"));
                cheque_pago.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque_pago.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque_pago.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque_pago.setFecha_cancela(tabla.getDate("fecha_cancela"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                cheque_pago.setProveedores(proveedores);

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque_pago.setBanco(banco);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque_pago.setCuentas_base(cuenta_base);
                
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cheque_pago;
    }

    public String create_Cheque_Pago(Cheque_Pago cheque_pago) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into cheque_pago (num_cheque_pago, id_banco, monto, rut_proveedor, fecha_recepcion_documento, fecha_vencimiento, num_factura_compra) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, cheque_pago.getNum_cheque_pago());
            ps.setInt(2, cheque_pago.getBanco().getId_banco());
            ps.setInt(3, cheque_pago.getMonto());
            ps.setInt(4, cheque_pago.getProveedores().getRut());

            java.sql.Date fecha_recepcion_documento = new java.sql.Date(cheque_pago.getFecha_recepcion_documento().getTime());
            ps.setDate(5, fecha_recepcion_documento);

            java.sql.Date fecha_vencimiento = new java.sql.Date(cheque_pago.getFecha_vencimiento().getTime());
            ps.setDate(6, fecha_vencimiento);

            ps.setInt(7, cheque_pago.getNum_factura_compra());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String create_Cheque_Pago_Gastos(Cheque_Pago cheque_pago) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into cheque_pago (num_cheque_pago, id_banco, monto, rut_proveedor, fecha_recepcion_documento, fecha_vencimiento, id_gastos) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, cheque_pago.getNum_cheque_pago());
            ps.setInt(2, cheque_pago.getBanco().getId_banco());
            ps.setInt(3, cheque_pago.getMonto());
            ps.setInt(4, cheque_pago.getProveedores().getRut());

            java.sql.Date fecha_recepcion_documento = new java.sql.Date(cheque_pago.getFecha_recepcion_documento().getTime());
            ps.setDate(5, fecha_recepcion_documento);

            java.sql.Date fecha_vencimiento = new java.sql.Date(cheque_pago.getFecha_vencimiento().getTime());
            ps.setDate(6, fecha_vencimiento);

            ps.setInt(7, cheque_pago.getId_gastos());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String cancela_Cheque_Pago(Cheque_Pago cheque_pago) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cheque_pago set monto_cancelado = ?, fecha_cancela = ? where id_cheque_pago = ?");
            ps.setInt(1, cheque_pago.getMonto_cancelado());

            java.sql.Date fecha_cancela = new java.sql.Date(cheque_pago.getFecha_cancela().getTime());
            ps.setDate(2, fecha_cancela);

            ps.setInt(3, cheque_pago.getId_cheque_pago());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String revertir_cancela_Cheque_Pago(int id_cheque_pago) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cheque_pago set monto_cancelado = 0, fecha_cancela = null where id_cheque_pago = ?");
            ps.setInt(1, id_cheque_pago);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void delete_Cheque_Pago(int id_cheque_pago) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from cheque_pago where id_cheque_pago = ?");
            ps.setInt(1, id_cheque_pago);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerCheque_Pago() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cheque_pago");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque_Pago cheque_pago = new Cheque_Pago();
                cheque_pago.setId_cheque_pago(tabla.getInt("id_cheque_pago"));
                cheque_pago.setNum_cheque_pago(tabla.getInt("num_cheque_pago"));
                cheque_pago.setMonto(tabla.getInt("monto"));
                cheque_pago.setNum_factura_compra(tabla.getInt("num_factura_compra"));
                cheque_pago.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque_pago.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque_pago.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque_pago.setFecha_cancela(tabla.getDate("fecha_cancela"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                cheque_pago.setProveedores(proveedores);

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque_pago.setBanco(banco);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque_pago.setCuentas_base(cuenta_base);
                
                lista.add(cheque_pago);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cheque_Pago_por_Stock_Producto(int num_factura_compra, int rut_proveedor) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_vencimiento , '%m' ) mes_cheque_pago_ven, DATE_FORMAT(fecha_vencimiento , '%Y' ) ano_cheque_pago_ven from cheque_pago where num_factura_compra = ? and rut_proveedor = ? order by fecha_vencimiento");
            ps.setInt(1, num_factura_compra);
            ps.setInt(2, rut_proveedor);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque_Pago cheque_pago = new Cheque_Pago();
                cheque_pago.setId_cheque_pago(tabla.getInt("id_cheque_pago"));
                cheque_pago.setNum_cheque_pago(tabla.getInt("num_cheque_pago"));
                cheque_pago.setMonto(tabla.getInt("monto"));
                cheque_pago.setNum_factura_compra(tabla.getInt("num_factura_compra"));
                cheque_pago.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque_pago.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque_pago.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque_pago.setFecha_cancela(tabla.getDate("fecha_cancela"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                cheque_pago.setProveedores(proveedores);

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque_pago.setBanco(banco);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque_pago.setCuentas_base(cuenta_base);
                
                lista.add(cheque_pago);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cheque_Pago_por_Gastos(int id_gastos) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_vencimiento , '%m' ) mes_cheque_pago_ven, DATE_FORMAT(fecha_vencimiento , '%Y' ) ano_cheque_pago_ven from cheque_pago where id_gastos = ? order by fecha_vencimiento");
            ps.setInt(1, id_gastos);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque_Pago cheque_pago = new Cheque_Pago();
                cheque_pago.setId_cheque_pago(tabla.getInt("id_cheque_pago"));
                cheque_pago.setNum_cheque_pago(tabla.getInt("num_cheque_pago"));
                cheque_pago.setMonto(tabla.getInt("monto"));
                cheque_pago.setNum_factura_compra(tabla.getInt("id_gastos"));
                cheque_pago.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque_pago.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque_pago.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque_pago.setFecha_cancela(tabla.getDate("fecha_cancela"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                cheque_pago.setProveedores(proveedores);

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque_pago.setBanco(banco);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque_pago.setCuentas_base(cuenta_base);
                
                lista.add(cheque_pago);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cheque_Pago_por_Stock_Producto_Pagado(int num_factura_compra) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( fecha_cancela , '%m' ) mes_cheque_pago_pag, DATE_FORMAT(fecha_cancela , '%Y' ) ano_cheque_pago_pag "
                    + "from cheque_pago where num_factura_compra = ? and fecha_cancela is not null order by fecha_cancela");
            ps.setInt(1, num_factura_compra);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque_Pago cheque_pago = new Cheque_Pago();
                cheque_pago.setId_cheque_pago(tabla.getInt("id_cheque_pago"));
                cheque_pago.setNum_cheque_pago(tabla.getInt("num_cheque_pago"));
                cheque_pago.setMonto(tabla.getInt("monto"));
                cheque_pago.setNum_factura_compra(tabla.getInt("num_factura_compra"));
                cheque_pago.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque_pago.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque_pago.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque_pago.setFecha_cancela(tabla.getDate("fecha_cancela"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                cheque_pago.setProveedores(proveedores);

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque_pago.setBanco(banco);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                cheque_pago.setCuentas_base(cuenta_base);
                
                lista.add(cheque_pago);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cheque_Pago_para_Comprobante() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM cheque_pago where id_cheque_pago not in (select cp.id_cheque_pago from comprobante cp where cp.id_cheque_pago is not null) order by fecha_recepcion_documento");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cheque_Pago cheque = new Cheque_Pago();
                cheque.setId_cheque_pago(tabla.getInt("id_cheque_pago"));
                cheque.setNum_cheque_pago(tabla.getInt("num_cheque_pago"));
                cheque.setMonto(tabla.getInt("monto"));
                cheque.setFecha_recepcion_documento(tabla.getDate("fecha_recepcion_documento"));
                cheque.setFecha_vencimiento(tabla.getDate("fecha_vencimiento"));
                cheque.setMonto_cancelado(tabla.getInt("monto_cancelado"));
                cheque.setFecha_cancela(tabla.getDate("fecha_cancela"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                cheque.setProveedores(proveedores);

                BancoDAO banDAO = new BancoDAO();
                Banco banco = banDAO.retrive_Banco(tabla.getInt("id_banco"));
                cheque.setBanco(banco);

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
