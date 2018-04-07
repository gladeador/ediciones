package cl.ediciones.model.dao;

import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Forma_Pago;
import cl.ediciones.model.Gastos;
import cl.ediciones.model.Proveedores;
import cl.ediciones.model.Tipo_Documento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GastosDAO {

    public GastosDAO() {
    }

    public Gastos retrieve(int id_gastos) throws SQLException {
        Gastos gasto = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from gastos where id_gastos = ?");
            ps.setInt(1, id_gastos);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                gasto = new Gastos();
                gasto.setId_gastos(tabla.getInt("id_gastos"));
                gasto.setNum_boleta(tabla.getString("num_boleta"));
                gasto.setFecha_gasto(tabla.getDate("fecha_gasto"));
                gasto.setNeto_exento(tabla.getInt("neto_exento"));
                gasto.setNeto_afecto(tabla.getInt("neto_afecto"));
                gasto.setIva_afecto(tabla.getInt("iva_afecto"));
                gasto.setTotal_afecto(tabla.getInt("total_afecto"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                gasto.setProveedores(proveedores);

                Tipo_DocumentoDAO td = new Tipo_DocumentoDAO();
                Tipo_Documento tipo_documento = td.retrieve(tabla.getString("tipo_documento"));
                gasto.setTipo_documento(tipo_documento);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base_neto = cb.retrieve(tabla.getString("num_cuenta_neto"));
                gasto.setCuenta_base_neto(cuenta_base_neto);
                Cuentas_Base cuenta_base_iva = cb.retrieve(tabla.getString("num_cuenta_iva"));
                gasto.setCuenta_base_iva(cuenta_base_iva);
                Cuentas_Base cuenta_base_total = cb.retrieve(tabla.getString("num_cuenta_total"));
                gasto.setCuenta_base_total(cuenta_base_total);

                Forma_Pago_GastosDAO fpgDAO = new Forma_Pago_GastosDAO();
                Forma_Pago forma_pago = fpgDAO.retrive(tabla.getInt("id_forma_pago"));
                gasto.setForma_pago(forma_pago);

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return gasto;
    }

    public void create(Gastos gasto) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into gastos (num_boleta, fecha_gasto, neto_exento, neto_afecto, iva_afecto, total_afecto, rut_proveedor, tipo_documento, num_cuenta_neto, id_forma_pago) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, gasto.getNum_boleta());

            java.sql.Date fecha1 = new java.sql.Date(gasto.getFecha_gasto().getTime());
            ps.setDate(2, fecha1);

            ps.setInt(3, gasto.getNeto_exento());
            ps.setInt(4, gasto.getNeto_afecto());
            ps.setInt(5, gasto.getIva_afecto());
            ps.setInt(6, gasto.getTotal_afecto());
            ps.setInt(7, gasto.getProveedores().getRut());
            ps.setString(8, gasto.getTipo_documento().getId_tipo_documento());
            ps.setString(9, gasto.getCuenta_base_neto().getNum_cuenta());
            ps.setInt(10, gasto.getForma_pago().getId_forma_pago());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Gastos gasto) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update gastos set num_boleta = ?, fecha_gasto = ?, neto_exento = ?, neto_afecto = ?, iva_afecto = ?, total_afecto = ?, rut_proveedor = ?, tipo_documento = ?, num_cuenta_neto = ?, id_forma_pago = ? where id_gastos = ?");
            ps.setString(1, gasto.getNum_boleta());

            java.sql.Date fecha1 = new java.sql.Date(gasto.getFecha_gasto().getTime());
            ps.setDate(2, fecha1);

            ps.setInt(3, gasto.getNeto_exento());
            ps.setInt(4, gasto.getNeto_afecto());
            ps.setInt(5, gasto.getIva_afecto());
            ps.setInt(6, gasto.getTotal_afecto());
            ps.setInt(7, gasto.getProveedores().getRut());
            ps.setString(8, gasto.getTipo_documento().getId_tipo_documento());
            ps.setString(9, gasto.getCuenta_base_neto().getNum_cuenta());
            ps.setInt(10, gasto.getForma_pago().getId_forma_pago());
            ps.setInt(11, gasto.getId_gastos());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int id_gastos) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from gastos where id_gastos = ?");
            ps.setInt(1, id_gastos);

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Gastos() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from gastos order by fecha_gasto");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Gastos gasto = new Gastos();
                gasto = new Gastos();
                gasto.setId_gastos(tabla.getInt("id_gastos"));
                gasto.setNum_boleta(tabla.getString("num_boleta"));
                gasto.setFecha_gasto(tabla.getDate("fecha_gasto"));
                gasto.setNeto_exento(tabla.getInt("neto_exento"));
                gasto.setNeto_afecto(tabla.getInt("neto_afecto"));
                gasto.setIva_afecto(tabla.getInt("iva_afecto"));
                gasto.setTotal_afecto(tabla.getInt("total_afecto"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                gasto.setProveedores(proveedores);

                Tipo_DocumentoDAO td = new Tipo_DocumentoDAO();
                Tipo_Documento tipo_documento = td.retrieve(tabla.getString("tipo_documento"));
                gasto.setTipo_documento(tipo_documento);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base_neto = cb.retrieve(tabla.getString("num_cuenta_neto"));
                gasto.setCuenta_base_neto(cuenta_base_neto);
                Cuentas_Base cuenta_base_iva = cb.retrieve(tabla.getString("num_cuenta_iva"));
                gasto.setCuenta_base_iva(cuenta_base_iva);
                Cuentas_Base cuenta_base_total = cb.retrieve(tabla.getString("num_cuenta_total"));
                gasto.setCuenta_base_total(cuenta_base_total);

                Forma_Pago_GastosDAO fpgDAO = new Forma_Pago_GastosDAO();
                Forma_Pago forma_pago = fpgDAO.retrive(tabla.getInt("id_forma_pago"));
                gasto.setForma_pago(forma_pago);

                lista.add(gasto);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Gastos_por_Mes_Ano(String mes, String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from gastos where DATE_FORMAT( fecha_gasto, '%m' ) = ? and DATE_FORMAT( fecha_gasto, '%Y' ) = ?");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Gastos gasto = new Gastos();
                gasto = new Gastos();
                gasto.setId_gastos(tabla.getInt("id_gastos"));
                gasto.setNum_boleta(tabla.getString("num_boleta"));
                gasto.setFecha_gasto(tabla.getDate("fecha_gasto"));
                gasto.setNeto_exento(tabla.getInt("neto_exento"));
                gasto.setNeto_afecto(tabla.getInt("neto_afecto"));
                gasto.setIva_afecto(tabla.getInt("iva_afecto"));
                gasto.setTotal_afecto(tabla.getInt("total_afecto"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                gasto.setProveedores(proveedores);

                Tipo_DocumentoDAO td = new Tipo_DocumentoDAO();
                Tipo_Documento tipo_documento = td.retrieve(tabla.getString("tipo_documento"));
                gasto.setTipo_documento(tipo_documento);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base_neto = cb.retrieve(tabla.getString("num_cuenta_neto"));
                gasto.setCuenta_base_neto(cuenta_base_neto);
                Cuentas_Base cuenta_base_iva = cb.retrieve(tabla.getString("num_cuenta_iva"));
                gasto.setCuenta_base_iva(cuenta_base_iva);
                Cuentas_Base cuenta_base_total = cb.retrieve(tabla.getString("num_cuenta_total"));
                gasto.setCuenta_base_total(cuenta_base_total);

                Forma_Pago_GastosDAO fpgDAO = new Forma_Pago_GastosDAO();
                Forma_Pago forma_pago = fpgDAO.retrive(tabla.getInt("id_forma_pago"));
                gasto.setForma_pago(forma_pago);

                lista.add(gasto);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Gastos_para_Comprobante() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from gastos where id_gastos not in (select c.id_gastos from comprobante c where c.id_gastos is not null) order by fecha_gasto");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Gastos gasto = new Gastos();
                gasto.setId_gastos(tabla.getInt("id_gastos"));
                gasto.setNum_boleta(tabla.getString("num_boleta"));
                gasto.setFecha_gasto(tabla.getDate("fecha_gasto"));
                gasto.setNeto_exento(tabla.getInt("neto_exento"));
                gasto.setNeto_afecto(tabla.getInt("neto_afecto"));
                gasto.setIva_afecto(tabla.getInt("iva_afecto"));
                gasto.setTotal_afecto(tabla.getInt("total_afecto"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                gasto.setProveedores(proveedores);

                Tipo_DocumentoDAO td = new Tipo_DocumentoDAO();
                Tipo_Documento tipo_documento = td.retrieve(tabla.getString("tipo_documento"));
                gasto.setTipo_documento(tipo_documento);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base_neto = cb.retrieve(tabla.getString("num_cuenta_neto"));
                gasto.setCuenta_base_neto(cuenta_base_neto);
                Cuentas_Base cuenta_base_iva = cb.retrieve(tabla.getString("num_cuenta_iva"));
                gasto.setCuenta_base_iva(cuenta_base_iva);
                Cuentas_Base cuenta_base_total = cb.retrieve(tabla.getString("num_cuenta_total"));
                gasto.setCuenta_base_total(cuenta_base_total);

                Forma_Pago_GastosDAO fpgDAO = new Forma_Pago_GastosDAO();
                Forma_Pago forma_pago = fpgDAO.retrive(tabla.getInt("id_forma_pago"));
                gasto.setForma_pago(forma_pago);

                lista.add(gasto);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Gastos_para_ControlPago() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from gastos where id_forma_pago != 1 order by fecha_gasto");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Gastos gasto = new Gastos();
                gasto.setId_gastos(tabla.getInt("id_gastos"));
                gasto.setNum_boleta(tabla.getString("num_boleta"));
                gasto.setFecha_gasto(tabla.getDate("fecha_gasto"));
                gasto.setNeto_exento(tabla.getInt("neto_exento"));
                gasto.setNeto_afecto(tabla.getInt("neto_afecto"));
                gasto.setIva_afecto(tabla.getInt("iva_afecto"));
                gasto.setTotal_afecto(tabla.getInt("total_afecto"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                gasto.setProveedores(proveedores);

                Tipo_DocumentoDAO td = new Tipo_DocumentoDAO();
                Tipo_Documento tipo_documento = td.retrieve(tabla.getString("tipo_documento"));
                gasto.setTipo_documento(tipo_documento);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base_neto = cb.retrieve(tabla.getString("num_cuenta_neto"));
                gasto.setCuenta_base_neto(cuenta_base_neto);
                Cuentas_Base cuenta_base_iva = cb.retrieve(tabla.getString("num_cuenta_iva"));
                gasto.setCuenta_base_iva(cuenta_base_iva);
                Cuentas_Base cuenta_base_total = cb.retrieve(tabla.getString("num_cuenta_total"));
                gasto.setCuenta_base_total(cuenta_base_total);

                Forma_Pago_GastosDAO fpgDAO = new Forma_Pago_GastosDAO();
                Forma_Pago forma_pago = fpgDAO.retrive(tabla.getInt("id_forma_pago"));
                gasto.setForma_pago(forma_pago);

                lista.add(gasto);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Gastos_para_ControlPago_por_Proveedor(int rut_proveedor) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from gastos where id_forma_pago != 1 and rut_proveedor = ? order by fecha_gasto");
            ps.setInt(1, rut_proveedor);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Gastos gasto = new Gastos();
                gasto.setId_gastos(tabla.getInt("id_gastos"));
                gasto.setNum_boleta(tabla.getString("num_boleta"));
                gasto.setFecha_gasto(tabla.getDate("fecha_gasto"));
                gasto.setNeto_exento(tabla.getInt("neto_exento"));
                gasto.setNeto_afecto(tabla.getInt("neto_afecto"));
                gasto.setIva_afecto(tabla.getInt("iva_afecto"));
                gasto.setTotal_afecto(tabla.getInt("total_afecto"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                gasto.setProveedores(proveedores);

                Tipo_DocumentoDAO td = new Tipo_DocumentoDAO();
                Tipo_Documento tipo_documento = td.retrieve(tabla.getString("tipo_documento"));
                gasto.setTipo_documento(tipo_documento);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base_neto = cb.retrieve(tabla.getString("num_cuenta_neto"));
                gasto.setCuenta_base_neto(cuenta_base_neto);
                Cuentas_Base cuenta_base_iva = cb.retrieve(tabla.getString("num_cuenta_iva"));
                gasto.setCuenta_base_iva(cuenta_base_iva);
                Cuentas_Base cuenta_base_total = cb.retrieve(tabla.getString("num_cuenta_total"));
                gasto.setCuenta_base_total(cuenta_base_total);

                Forma_Pago_GastosDAO fpgDAO = new Forma_Pago_GastosDAO();
                Forma_Pago forma_pago = fpgDAO.retrive(tabla.getInt("id_forma_pago"));
                gasto.setForma_pago(forma_pago);

                lista.add(gasto);
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
