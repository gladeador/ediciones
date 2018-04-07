package cl.ediciones.model.dao;

import cl.ediciones.model.Boleta;
import cl.ediciones.model.Clientes;
import cl.ediciones.model.Factura;
import cl.ediciones.model.Informes;
import cl.ediciones.model.NotaCredito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InformesDAO {

    public ArrayList Ventas_por_Año(String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        int cont = 1;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT c.rut, sum(f.neto) as suma_neto, sum(f.total) as suma FROM clientes c, factura f where c.rut = f.rut_cliente and f.estado != 'N' and DATE_FORMAT( f.fecha_factura , '%Y' ) = ? group by c.rut order by sum(f.total) desc");
            ps.setString(1, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Informes informes = new Informes();
                informes.setRkn(cont++);
                informes.setRut(tabla.getInt("rut"));
                informes.setSuma_neto(tabla.getInt("suma_neto"));
                informes.setSuma_total(tabla.getInt("suma"));
                lista.add(informes);
            }

            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList Ranking_Ventas_Regiones(String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        int cont = 1;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT r.id_region, r.descripcion, sum(f.neto) as suma_neto, sum(f.total) as suma_total FROM clientes c, factura f, ciudad ci, region r where c.rut = f.rut_cliente and f.estado != 'N' and DATE_FORMAT( f.fecha_factura , '%Y' ) = ? and c.id_ciudad = ci.id_ciudad and ci.id_region = r.id_region group by r.id_region, r.descripcion order by sum(f.total) desc");
            ps.setString(1, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Informes informes = new Informes();
                informes.setRkn(cont++);
                informes.setId_region(tabla.getInt("id_region"));
                informes.setDescripcion(tabla.getString("descripcion"));
                informes.setSuma_total(tabla.getInt("suma_total"));
                lista.add(informes);
            }

            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList Ventas_por_Factura(String mes, String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from factura f "
                    + "where f.estado != 'N' "
                    + "and DATE_FORMAT( f.fecha_factura , '%m' ) = ? "
                    + "and  DATE_FORMAT( f.fecha_factura , '%Y' ) = ? "
                    + "order by num_factura");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Factura factura = new Factura();
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

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes cliente = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                factura.setClientes(cliente);

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

    public ArrayList Ventas_por_Boleta(String mes, String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from boleta b "
                    + "where b.estado != 'N' "
                    + "and DATE_FORMAT( b.fecha_boleta , '%m' ) = ? "
                    + "and  DATE_FORMAT( b.fecha_boleta , '%Y' ) = ? "
                    + "order by num_boleta");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Boleta boleta = new Boleta();
                boleta.setId_boleta(tabla.getInt("id_boleta"));
                boleta.setEstado(tabla.getString("estado"));
                boleta.setFecha_boleta(tabla.getDate("fecha_boleta"));
                boleta.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                boleta.setIva(tabla.getInt("iva"));
                boleta.setDescuento(tabla.getInt("descuento"));
                boleta.setNeto(tabla.getInt("neto"));
                boleta.setNum_boleta(tabla.getInt("num_boleta"));
                boleta.setRut_cliente(tabla.getInt("rut_cliente"));
                boleta.setSubtotal(tabla.getInt("sub_total"));
                boleta.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes cliente = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                boleta.setClientes(cliente);

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

    public ArrayList Ventas_por_Nota_Credito(String mes, String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from notacredito f "
                    + "where f.estado != 'N' "
                    + "and DATE_FORMAT( f.fecha_notacredito , '%m' ) = ? "
                    + "and  DATE_FORMAT( f.fecha_notacredito , '%Y' ) = ? "
                    + "order by num_notacredito");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                NotaCredito notacredito = new NotaCredito();
                notacredito.setId_notacredito(tabla.getInt("id_notacredito"));
                notacredito.setEstado(tabla.getString("estado"));
                notacredito.setFecha_notacredito(tabla.getDate("fecha_notacredito"));
                notacredito.setId_tipo_envio(tabla.getInt("id_tipo_envio"));
                notacredito.setIva(tabla.getInt("iva"));
                notacredito.setDescuento(tabla.getInt("descuento"));
                notacredito.setNeto(tabla.getInt("neto"));
                notacredito.setNum_notacredito(tabla.getInt("num_notacredito"));
                notacredito.setRut_cliente(tabla.getInt("rut_cliente"));
                notacredito.setSubtotal(tabla.getInt("sub_total"));
                notacredito.setTotal(tabla.getInt("total"));

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes cliente = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                notacredito.setClientes(cliente);

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

    public Informes meses_Flujo_Mes_Cheque(String mes, String ano) throws SQLException {
        Informes informes = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT DATE_FORMAT( c.fecha_vencimiento , '%m' ) as mes_cheque, DATE_FORMAT( c.fecha_vencimiento , '%Y' ) as ano_cheque "
                    + "FROM factura f, cheque c "
                    + "WHERE f.id_factura = c.id_factura "
                    + "and DATE_FORMAT( f.fecha_factura , '%m' ) = ? "
                    + "and DATE_FORMAT( f.fecha_factura , '%Y' ) = ? "
                    + "group by DATE_FORMAT( c.fecha_vencimiento , '%m' ), DATE_FORMAT( c.fecha_vencimiento , '%Y' ) "
                    + "order by c.fecha_vencimiento");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                informes = new Informes();
                informes.setMes_inicio(mes);
                informes.setMes_fin(tabla.getString("mes_cheque"));
                informes.setAno(tabla.getString("ano_cheque"));
            }

            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return informes;
    }

    public Informes meses_Flujo_Mes_Cuota(String mes, String ano) throws SQLException {
        Informes informes = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT DATE_FORMAT( c.fecha_vencimiento , '%m' ) as mes_cuota, DATE_FORMAT( c.fecha_vencimiento , '%Y' ) as ano_cuota "
                    + "FROM factura f, cuota c "
                    + "WHERE f.id_factura = c.id_factura "
                    + "and DATE_FORMAT( f.fecha_factura , '%m' ) = ? "
                    + "and DATE_FORMAT( f.fecha_factura , '%Y' ) = ? "
                    + "group by DATE_FORMAT( c.fecha_vencimiento , '%m' ), DATE_FORMAT( c.fecha_vencimiento , '%Y' ) "
                    + "order by c.fecha_vencimiento");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                informes = new Informes();
                informes.setMes_inicio(mes);
                informes.setMes_fin(tabla.getString("mes_cuota"));
                informes.setAno(tabla.getString("ano_cuota"));
            }

            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return informes;
    }

    public Informes meses_Flujo_Mes_Lera(String mes, String ano) throws SQLException {
        Informes informes = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT DATE_FORMAT( c.fecha_vencimiento , '%m' ) as mes_letra, DATE_FORMAT( c.fecha_vencimiento , '%Y' ) as ano_letra "
                    + "FROM factura f, letra c "
                    + "WHERE f.id_factura = c.id_factura "
                    + "and DATE_FORMAT( f.fecha_factura , '%m' ) = ? "
                    + "and DATE_FORMAT( f.fecha_factura , '%Y' ) = ? "
                    + "group by DATE_FORMAT( c.fecha_vencimiento , '%m' ), DATE_FORMAT( c.fecha_vencimiento , '%Y' ) "
                    + "order by c.fecha_vencimiento");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                informes = new Informes();
                informes.setMes_inicio(mes);
                informes.setMes_fin(tabla.getString("mes_letra"));
                informes.setAno(tabla.getString("ano_letra"));
            }

            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return informes;
    }
}
