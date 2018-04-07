package cl.ediciones.model.dao;

import cl.ediciones.model.Libro_Compras;
import cl.ediciones.model.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Libro_ComprasDAO {

    public Libro_ComprasDAO() {
    }

    public ArrayList traerTodos_Libro_Compras(String mes, String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM gastos WHERE DATE_FORMAT( fecha_gasto, '%m' ) = ? AND DATE_FORMAT( fecha_gasto, '%Y' ) = ? ORDER BY fecha_gasto, num_boleta");
            ps.setString(1, mes);
            ps.setString(2, ano);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Libro_Compras libro = new Libro_Compras();
                libro.setTipo(tabla.getString("tipo_documento"));
                libro.setDocumento(tabla.getString("num_boleta"));
                libro.setFecha(tabla.getDate("fecha_gasto"));
                libro.setExento(tabla.getInt("neto_exento"));
                libro.setAfecto(tabla.getInt("neto_afecto"));
                libro.setIva(tabla.getInt("iva_afecto"));
                libro.setTotal(tabla.getInt("total_afecto"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                libro.setProveedores(proveedores);

                lista.add(libro);
            }

            ps = con.prepareStatement("SELECT num_factura, fecha_ingreso, sum(neto) as neto_afecto, sum(iva) as iva_afecto, sum(total) as total_afecto, rut_proveedor FROM stock WHERE DATE_FORMAT( fecha_ingreso, '%m' ) = ? AND DATE_FORMAT( fecha_ingreso, '%Y' ) = ?  group by num_factura, rut_proveedor ORDER BY fecha_ingreso, num_factura");
            ps.setString(1, mes);
            ps.setString(2, ano);

            tabla = ps.executeQuery();
            while (tabla.next()) {
                Libro_Compras libro = new Libro_Compras();
                libro.setDocumento(tabla.getString("num_factura"));
                libro.setFecha(tabla.getDate("fecha_ingreso"));
                libro.setAfecto(tabla.getInt("neto_afecto"));
                libro.setIva(tabla.getInt("iva_afecto"));
                libro.setTotal(tabla.getInt("total_afecto"));

                ProveedoresDAO proDAO = new ProveedoresDAO();
                Proveedores proveedores = proDAO.retrieve(tabla.getInt("rut_proveedor"));
                libro.setProveedores(proveedores);

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
