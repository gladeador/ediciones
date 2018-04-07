package cl.ediciones.model.dao;

import cl.ediciones.model.Clientes;
import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Factura;
import cl.ediciones.model.Forma_Pago;
import cl.ediciones.util.FechaStr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacturaDAO {

    public int create_Factura(int num_factura, String fecha_factura) throws SQLException {
        int id_factura = 0;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = con.prepareStatement("insert into factura (num_factura, fecha_factura, estado, id_forma_pago) values (?, ?, 'N', 1)");
            ps.setInt(1, num_factura);
            ps.setDate(2, FechaStr.stringToDate(fecha_factura));

            ps.executeUpdate();

            ps2 = con.prepareStatement("select id_factura from factura where num_factura= ?");
            ps2.setInt(1, num_factura);

            ResultSet tabla = ps2.executeQuery();
            if (tabla.next()) {
                id_factura = tabla.getInt("id_factura");
            }
            ps.close();
            ps2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return id_factura;
    }

    public Factura total_ventas_ano(String ano) throws SQLException {
        Factura factura = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT sum(f.neto) as neto, sum(f.total) as total FROM factura f where f.estado in ('P', 'E') and DATE_FORMAT( f.fecha_factura , '%Y' ) = ?");
            ps.setString(1, ano);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                factura = new Factura();
                factura.setNeto(tabla.getInt("neto"));
                factura.setTotal(tabla.getInt("total"));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return factura;
    }

    public String valida_num_factura(int num_factura) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select num_factura from factura where num_factura = ?");
            ps.setInt(1, num_factura);

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

    public Factura retrive(int id_factura) throws SQLException {
        Factura factura = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura where id_factura = ?");
            ps.setInt(1, id_factura);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes cliente = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(cliente);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base_neto = cb.retrieve(tabla.getString("num_cuenta_neto"));
                factura.setCuenta_base_neto(cuenta_base_neto);
                Cuentas_Base cuenta_base_iva = cb.retrieve(tabla.getString("num_cuenta_iva"));
                factura.setCuenta_base_iva(cuenta_base_iva);
                Cuentas_Base cuenta_base_total = cb.retrieve(tabla.getString("num_cuenta_total"));
                factura.setCuenta_base_total(cuenta_base_total);


            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return factura;
    }

    public void agrega_cliente_a_Factura(int rut, int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura set rut_cliente = ? where id_factura = ?");
            ps.setInt(1, rut);
            ps.setInt(2, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void agrega_valores_a_Factura(int sub_total, int desc, int neto, int iva, int total, int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura set sub_total = ?, descuento = ?, neto = ?, iva = ?, total = ?, saldo_pago = ? where id_factura = ?");
            ps.setInt(1, sub_total);
            ps.setInt(2, desc);
            ps.setInt(3, neto);
            ps.setInt(4, iva);
            ps.setInt(5, total);
            ps.setInt(6, total);
            ps.setInt(7, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void emitir_Factura(int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura set estado = 'E' where id_factura = ?");
            ps.setInt(1, id_factura);

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void modifica_fecha_Factura(String fecha_factura, int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura set fecha_factura = ? where id_factura = ?");
//            java.sql.Date fecha = new java.sql.Date(FechaStr.stringToDate(fecha_factura));

//            ps.setDate(3, fecha);
            ps.setDate(1, FechaStr.stringToDate(fecha_factura));
            ps.setInt(2, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update_forma_pago(int id_forma_pago, int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura set id_forma_pago = ? where id_factura = ?");
            ps.setInt(1, id_forma_pago);
            ps.setInt(2, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void graba_Envio(int id_tipo_envio, int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura set id_tipo_envio = ? where id_factura = ?");
            ps.setInt(1, id_tipo_envio);
            ps.setInt(2, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Factura_Nuevas() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura where estado = 'N' order by num_factura");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Nuevas_Criterios(String rut_cliente, String rut, String num_factura, String mes, String ano, String estado) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            String sql = "select * from factura f "
                    + "where f.estado = 'N' "
                    + "and f.num_factura like '%" + num_factura + "' "
                    + "and DATE_FORMAT( f.fecha_factura , '%m' ) like '%" + mes + "' "
                    + "and DATE_FORMAT( f.fecha_factura , '%Y' ) like '%" + ano + "' ";
            if (rut_cliente.equals("") && rut.equals("")) {
                sql = sql + "order by num_factura";
            } else if (rut.equals(rut_cliente)) {
                sql = sql + "and f.rut_cliente like '%" + rut + "' "
                        + "order by num_factura";
            } else if (!rut_cliente.equals("") && !rut.equals("")) {
                sql = sql + "and f.rut_cliente in (" + rut + "," + rut_cliente + ") "
                        + "order by num_factura";
            } else if (rut.equals("")) {
                sql = sql + "and f.rut_cliente like '%" + rut_cliente + "' "
                        + "order by num_factura";
            } else {
                sql = sql + "and f.rut_cliente like '%" + rut + "' "
                        + "order by num_factura";
            }

            ps = con.prepareStatement(sql);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Emitidas_Criterios(String rut_cliente, String rut, String num_factura, String mes, String ano, String estado) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            String sql = "select * from factura f "
                    + "where f.estado = 'E' "
                    + "and f.num_factura like '%" + num_factura + "' "
                    + "and DATE_FORMAT( f.fecha_factura , '%m' ) like '%" + mes + "' "
                    + "and DATE_FORMAT( f.fecha_factura , '%Y' ) like '%" + ano + "' ";
            if (rut_cliente.equals("") && rut.equals("")) {
                sql = sql + "order by num_factura";
            } else if (rut.equals(rut_cliente)) {
                sql = sql + "and f.rut_cliente like '%" + rut + "' "
                        + "order by num_factura";
            } else if (!rut_cliente.equals("") && !rut.equals("")) {
                sql = sql + "and f.rut_cliente in (" + rut + "," + rut_cliente + ") "
                        + "order by num_factura";
            } else if (rut.equals("")) {
                sql = sql + "and f.rut_cliente like '%" + rut_cliente + "' "
                        + "order by num_factura";
            } else {
                sql = sql + "and f.rut_cliente like '%" + rut + "' "
                        + "order by num_factura";
            }

            ps = con.prepareStatement(sql);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Nuevas_por_Cliente(int rut_cliente) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura where estado = 'N' and rut_cliente = ? order by num_factura");
            ps.setInt(1, rut_cliente);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Emitidas() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura where estado = 'E' order by num_factura");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Emitidas_por_Mes_Ano(String mes, String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select *, DATE_FORMAT( f.fecha_factura , '%m' ) mes_factura, DATE_FORMAT( f.fecha_factura , '%Y' ) ano_factura from factura f where f.estado = 'E' and DATE_FORMAT( f.fecha_factura , '%m' ) = ? and  DATE_FORMAT( f.fecha_factura , '%Y' ) = ? order by fecha_factura");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));
                factura.setMes_factura(tabla.getInt("mes_factura"));
                factura.setAno_factura(tabla.getInt("ano_factura"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Emitidas_para_Anular() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura f "
                    + "where f.estado = 'E' and f.id_factura not in (select c.id_factura from cheque c) "
                    + "and f.id_factura not in (select cu.id_factura from cuota cu) "
                    + "and f.id_factura not in (select l.id_factura from letra l) order by f.num_factura");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Emitidas_para_Anular_por_Cliente(int rut_cliente) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura f "
                    + "where f.estado = 'E' and f.id_factura not in (select c.id_factura from cheque c) "
                    + "and f.id_factura not in (select cu.id_factura from cuota cu) "
                    + "and f.id_factura not in (select l.id_factura from letra l) and f.rut_cliente = ? order by f.num_factura");
            ps.setInt(1, rut_cliente);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_para_ControlPago() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura where estado = 'E' and id_forma_pago != 1 order by num_factura");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_para_ControlPago_por_Cliente(int rut_cliente) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura where estado = 'E' and id_forma_pago != 1 and rut_cliente = ? order by num_factura");
            ps.setInt(1, rut_cliente);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void update(Factura factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura set sub_total = ?, descuento = ?, neto = ?, iva = ?, total= ?, fecha_factura = ? where id_factura = ?");
            ps.setInt(1, factura.getSubtotal());
            ps.setInt(2, factura.getDescuento());
            ps.setInt(3, factura.getIva());
            ps.setInt(4, factura.getNeto());
            ps.setInt(5, factura.getTotal());

            java.sql.Date fecha = new java.sql.Date(factura.getFecha_factura().getTime());
            ps.setDate(6, fecha);

            ps.setInt(7, factura.getId_factura());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void eliminar_Factura(int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from factura where id_factura = ?");
            ps.setInt(1, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void anular_Factura(int id_factura) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update factura set estado = 'A', sub_total = 0, descuento = 0, neto = 0, iva = 0, total = 0 where id_factura = ?");
            ps.setInt(1, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void actualiza_Saldo_Pago(int id_factura, int saldo_pago) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            // ps = con.prepareStatement("delete proveedores where rut = ?");
            ps = con.prepareStatement("update factura set saldo_pago = ? where id_factura = ?");
            ps.setInt(1, saldo_pago);
            ps.setInt(2, id_factura);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Factura_para_Comprobante() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura where estado = 'E' and id_factura not in (select c.id_factura from comprobante c where c.id_factura is not null) order by fecha_factura");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
                factura.setId_factura(tabla.getInt("id_factura"));
                factura.setDescuento(tabla.getInt("descuento"));
                factura.setEstado(tabla.getString("estado"));
                factura.setFecha_factura(tabla.getDate("fecha_factura"));
                factura.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                factura.setIva(tabla.getInt("iva"));
                factura.setNeto(tabla.getInt("neto"));
                factura.setNum_factura(tabla.getInt("num_factura"));
                factura.setRut_cliente(tabla.getInt("rut_cliente"));
                factura.setSubtotal(tabla.getInt("sub_total"));
                factura.setTotal(tabla.getInt("total"));
                factura.setSaldo_pago(tabla.getInt("saldo_pago"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(clientes);

                Forma_PagoDAO forDAO = new Forma_PagoDAO();
                Forma_Pago forma_pago = forDAO.retrive(tabla.getInt("id_forma_pago"));
                factura.setForma_pago(forma_pago);

                lista.add(factura);
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
