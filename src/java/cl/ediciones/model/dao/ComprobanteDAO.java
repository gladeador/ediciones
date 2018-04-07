package cl.ediciones.model.dao;

import cl.ediciones.model.Cheque;
import cl.ediciones.model.Cheque_Pago;
import cl.ediciones.model.Comprobante;
import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Cuota;
import cl.ediciones.model.Factura;
import cl.ediciones.model.Gastos;
import cl.ediciones.model.Letra;
import cl.ediciones.model.Libro_Mayor;
import cl.ediciones.model.Proveedores;
import cl.ediciones.util.FechaStr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComprobanteDAO {

    public Comprobante retrive_Comprobante(int id_comprobante) throws SQLException {
        Comprobante comprobante = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from comprobante where id_comprobante = ?");
            ps.setInt(1, id_comprobante);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                comprobante = new Comprobante();
                comprobante.setId_comprobante(tabla.getInt("id_comprobante"));
                comprobante.setNum_comprobante(tabla.getString("num_comprobante"));
                comprobante.setDescripcion(tabla.getString("descripcion"));
                comprobante.setFecha_comprobante(tabla.getDate("fecha"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return comprobante;
    }

    public String retrive_Detalle_Comprobante(Comprobante comprobante) throws SQLException {
        String descripcion = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select descripcion from comprobante where num_comprobante = ? and fecha = ?");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                descripcion = tabla.getString("descripcion");
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return descripcion;
    }

    public void createComprobante_Factura_Venta(Comprobante comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comprobante (num_comprobante, fecha, descripcion, id_factura) values (?, ?, ?, ?)");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ps.setString(3, comprobante.getDescripcion());
            ps.setInt(4, comprobante.getFactura().getId_factura());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void createComprobante_Factura_Compra(Comprobante comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comprobante (num_comprobante, fecha, descripcion, num_factura, rut_proveedor) values (?, ?, ?, ?, ?)");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ps.setString(3, comprobante.getDescripcion());
            ps.setInt(4, comprobante.getNum_factura());
            ps.setInt(5, comprobante.getProveedor().getRut());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void createComprobante_Gastos(Comprobante comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comprobante (num_comprobante, fecha, descripcion, id_gastos) values (?, ?, ?, ?)");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ps.setString(3, comprobante.getDescripcion());
            ps.setInt(4, comprobante.getGastos().getId_gastos());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void createComprobante_Cheque(Comprobante comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comprobante (num_comprobante, fecha, descripcion, id_cheque) values (?, ?, ?, ?)");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ps.setString(3, comprobante.getDescripcion());
            ps.setInt(4, comprobante.getCheque().getId_cheque());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void createComprobante_Cheque_Pago(Comprobante comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comprobante (num_comprobante, fecha, descripcion, id_cheque_pago, debe_haber) values (?, ?, ?, ?, ?)");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ps.setString(3, comprobante.getDescripcion());
            ps.setInt(4, comprobante.getCheque_pago().getId_cheque_pago());
            ps.setString(5, comprobante.getDebe_haber());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void createComprobante_Cuota(Comprobante comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comprobante (num_comprobante, fecha, descripcion, id_cuota) values (?, ?, ?, ?)");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ps.setString(3, comprobante.getDescripcion());
            ps.setInt(4, comprobante.getCuota().getId_cuota());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void createComprobante_Letra(Comprobante comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comprobante (num_comprobante, fecha, descripcion, id_letra) values (?, ?, ?, ?)");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ps.setString(3, comprobante.getDescripcion());
            ps.setInt(4, comprobante.getLetra().getId_letra());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void createComprobante_Manual(Comprobante comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into comprobante (num_comprobante, fecha, descripcion, num_cuenta, monto, debe_haber, descripcion_2) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, comprobante.getNum_comprobante());

            java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
            ps.setDate(2, fecha_comprobante);

            ps.setString(3, comprobante.getDescripcion());
            ps.setString(4, comprobante.getCuenta_base().getNum_cuenta());
            ps.setInt(5, comprobante.getMonto());
            ps.setString(6, comprobante.getDebe_haber());
            ps.setString(7, comprobante.getDescripcion_2());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void deleteComprobante(int id_comprobante) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from comprobante where id_comprobante = ?");
            ps.setInt(1, id_comprobante);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Comprobante() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT num_comprobante, fecha FROM comprobante group by num_comprobante, fecha order by fecha, num_comprobante");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Comprobante comprobante = new Comprobante();
                comprobante.setNum_comprobante(tabla.getString("num_comprobante"));
                comprobante.setFecha_comprobante(tabla.getDate("fecha"));
                lista.add(comprobante);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Comprobante_por_Fecha(String fecha_inicio, String fecha_fin) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT num_comprobante, fecha, descripcion FROM comprobante where DATE_FORMAT( fecha , '%m-%Y'  ) between '" + fecha_inicio + "' and '" + fecha_fin + "' group by num_comprobante, fecha order by  num_comprobante, fecha");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Comprobante comprobante = new Comprobante();
                comprobante.setNum_comprobante(tabla.getString("num_comprobante"));
                comprobante.setFecha_comprobante(tabla.getDate("fecha"));
                comprobante.setDescripcion(tabla.getString("descripcion"));
                lista.add(comprobante);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Comprobante_Libro_Diario(String num_comprobante, java.sql.Date fecha) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM comprobante where num_comprobante = ? and fecha = ? order by num_comprobante");
            ps.setString(1, num_comprobante);
            ps.setDate(2, fecha);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Comprobante comprobante = new Comprobante();
                comprobante.setId_comprobante(tabla.getInt("id_comprobante"));
                comprobante.setNum_comprobante(tabla.getString("num_comprobante"));
                comprobante.setNum_factura(tabla.getInt("num_factura"));
                comprobante.setDescripcion(tabla.getString("descripcion"));
                comprobante.setFecha_comprobante(tabla.getDate("fecha"));
                comprobante.setMonto(tabla.getInt("monto"));
                comprobante.setDebe_haber(tabla.getString("debe_haber"));
                comprobante.setDescripcion_2(tabla.getString("descripcion_2"));

                FacturaDAO facDAO = new FacturaDAO();
                Factura factura = facDAO.retrive(tabla.getInt("id_factura"));
                comprobante.setFactura(factura);

                GastosDAO gasDAO = new GastosDAO();
                Gastos gastos = gasDAO.retrieve(tabla.getInt("id_gastos"));
                comprobante.setGastos(gastos);

                ChequeDAO cheDAO = new ChequeDAO();
                Cheque cheque = cheDAO.retrive_Cheque(tabla.getInt("id_cheque"));
                comprobante.setCheque(cheque);

                Cheque_PagoDAO cpDAO = new Cheque_PagoDAO();
                Cheque_Pago cheque_pago = cpDAO.retrive_Cheque_Pago(tabla.getInt("id_cheque_pago"));
                comprobante.setCheque_pago(cheque_pago);

                CuotaDAO cuoDAO = new CuotaDAO();
                Cuota cuota = cuoDAO.retrive_Cuota(tabla.getInt("id_cuota"));
                comprobante.setCuota(cuota);

                LetraDAO letDAO = new LetraDAO();
                Letra letra = letDAO.retrive_Letra(tabla.getInt("id_letra"));
                comprobante.setLetra(letra);

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedor = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                comprobante.setProveedor(proveedor);

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                comprobante.setCuenta_base(cuenta_base);

                lista.add(comprobante);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Libro_Mayor_por_Fecha(String mes_inicio, String mes_fin, String ano_inicio, String ano_fin) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM v_cuentas_base where DATE_FORMAT(fecha, '%m') between '" + mes_inicio + "' and '" + mes_fin + "' and DATE_FORMAT(fecha, '%Y') between '" + ano_inicio + "' and '" + ano_fin + "'");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Libro_Mayor libro = new Libro_Mayor();
                libro.setValor(tabla.getInt("valor"));
                libro.setFecha(tabla.getDate("fecha"));
                libro.setDebe_haber(tabla.getString("debe_haber"));

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                libro.setCuentas_base(cuenta_base);

                ComprobanteDAO comDAO = new ComprobanteDAO();
                Comprobante comprobante = comDAO.retrive_Comprobante(tabla.getInt("id_comprobante"));
                libro.setComprobante(comprobante);

                lista.add(libro);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Saldo_Anterior_Libro_Mayor(String fecha_inicio) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT num_cuenta, sum(valor) as valor, debe_haber FROM v_cuentas_base WHERE fecha < ? group by num_cuenta, debe_haber");
            ps.setDate(1, FechaStr.stringToDate(fecha_inicio));

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Libro_Mayor libro = new Libro_Mayor();
                libro.setValor(tabla.getInt("valor"));
                libro.setDebe_haber(tabla.getString("debe_haber"));

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                libro.setCuentas_base(cuenta_base);

                lista.add(libro);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Balance_por_Fecha(String mes_inicio, String mes_fin, String ano_inicio, String ano_fin) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT num_cuenta, sum(valor) as valor, debe_haber FROM v_cuentas_base where DATE_FORMAT(fecha, '%m') between '" + mes_inicio + "' and '" + mes_fin + "' and DATE_FORMAT(fecha, '%Y') between '" + ano_inicio + "' and '" + ano_fin + "' group by num_cuenta, debe_haber");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Libro_Mayor libro = new Libro_Mayor();
                libro.setValor(tabla.getInt("valor"));
                libro.setDebe_haber(tabla.getString("debe_haber"));

                Cuentas_BaseDAO cb = new Cuentas_BaseDAO();
                Cuentas_Base cuenta_base = cb.retrieve(tabla.getString("num_cuenta"));
                libro.setCuentas_base(cuenta_base);

                lista.add(libro);
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
