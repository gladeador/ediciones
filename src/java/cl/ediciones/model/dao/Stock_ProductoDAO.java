package cl.ediciones.model.dao;

import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.Productos;
import cl.ediciones.model.Proveedores;
import cl.ediciones.model.Stock_Producto;
import cl.ediciones.model.Tipo_Documento;
import cl.ediciones.model.Tipo_Moneda;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Stock_ProductoDAO {

    public Stock_ProductoDAO() {
    }

    public Stock_Producto retrive_Stock(int id_stock) throws SQLException {
        Stock_Producto stock = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from stock where id_stock = ?");
            ps.setInt(1, id_stock);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                stock = new Stock_Producto();
                stock.setId_stock(tabla.getInt("id_stock"));
                stock.setStock(tabla.getInt("stock"));
                stock.setObservaciones(tabla.getString("observaciones"));
                stock.setFecha_ingreso(tabla.getDate("fecha_ingreso"));
                stock.setCosto_producto(tabla.getFloat("costo_producto"));
                stock.setTipo_de_cambio(tabla.getInt("tipo_de_cambio"));
                stock.setPorsentaje_gastos(tabla.getFloat("porsentaje_gastos"));
                stock.setCosto_gastos(tabla.getFloat("costo_gastos"));
                stock.setNum_guia_despacho(tabla.getInt("num_guia_despacho"));
                stock.setNum_factura(tabla.getInt("num_factura"));
                stock.setNeto(tabla.getFloat("neto"));
                stock.setIva(tabla.getFloat("iva"));
                stock.setTotal(tabla.getFloat("total"));

                ProductosDAO prodDAO = new ProductosDAO();
                Productos productos = prodDAO.retrieve(tabla.getInt("id_productos"));
                stock.setProductos(productos);

                ProveedoresDAO provDAO = new ProveedoresDAO();
                Proveedores proveedores = provDAO.retrieve(tabla.getInt("rut_proveedor"));
                stock.setProveedores(proveedores);

                Tipo_MonedaDAO tmDAO = new Tipo_MonedaDAO();
                Tipo_Moneda tipo_moneda = tmDAO.retrieve(tabla.getInt("id_tipo_moneda"));
                stock.setTipo_moneda(tipo_moneda);

                Tipo_DocumentoDAO tdDAO = new Tipo_DocumentoDAO();
                Tipo_Documento tipo_documento = tdDAO.retrieve(tabla.getString("id_tipo_documento"));
                stock.setTipo_documento(tipo_documento);

                Cuentas_BaseDAO cbDAO = new Cuentas_BaseDAO();
                Cuentas_Base cuentas_base = cbDAO.retrieve(tabla.getString("num_cuenta"));
                stock.setCuentas_base(cuentas_base);

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return stock;
    }

    public void create(Stock_Producto stock) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into stock (observaciones, stock, rut_proveedor, id_productos, costo_producto, tipo_de_cambio, porsentaje_gastos, costo_gastos, fecha_ingreso, num_guia_despacho, num_factura, estado, id_tipo_moneda, neto, iva, total, num_cuenta) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'R', ?, ?, ?, ?, ?)");
            ps.setString(1, stock.getObservaciones());
            ps.setInt(2, stock.getStock());
            ps.setInt(3, stock.getProveedores().getRut());
            ps.setInt(4, stock.getProductos().getId_productos());
            ps.setFloat(5, stock.getCosto_producto());
            ps.setInt(6, stock.getTipo_de_cambio());
            ps.setFloat(7, stock.getPorsentaje_gastos());
            ps.setFloat(8, stock.getCosto_gastos());

            java.sql.Date fecha1 = new java.sql.Date(stock.getFecha_ingreso().getTime());
            ps.setDate(9, fecha1);

            ps.setInt(10, stock.getNum_guia_despacho());
            ps.setInt(11, stock.getNum_factura());
            ps.setInt(12, stock.getTipo_moneda().getId_tipo_moneda());
            ps.setFloat(13, stock.getNeto());
            ps.setFloat(14, stock.getIva());
            ps.setFloat(15, stock.getTotal());
            ps.setString(16, stock.getCuentas_base().getNum_cuenta());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Stock_Producto stock) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update stock set stock = ?, observaciones = ?, costo_producto = ?, tipo_de_cambio = ?, porsentaje_gastos = ?, costo_gastos = ?, fecha_ingreso = ?, num_guia_despacho = ?, num_factura = ?, neto = ?, iva = ?, total = ?, num_cuenta = ? where id_stock = ?");
            ps.setInt(1, stock.getStock());
            ps.setString(2, stock.getObservaciones());
            ps.setFloat(3, stock.getCosto_producto());
            ps.setFloat(4, stock.getTipo_de_cambio());
            ps.setFloat(5, stock.getPorsentaje_gastos());
            ps.setFloat(6, stock.getCosto_gastos());

            java.sql.Date fecha1 = new java.sql.Date(stock.getFecha_ingreso().getTime());
            ps.setDate(7, fecha1);

            ps.setInt(8, stock.getNum_guia_despacho());
            ps.setInt(9, stock.getNum_factura());
            ps.setFloat(10, stock.getNeto());
            ps.setFloat(11, stock.getIva());
            ps.setFloat(12, stock.getTotal());
            ps.setString(13, stock.getCuentas_base().getNum_cuenta());
            ps.setInt(14, stock.getId_stock());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(Stock_Producto stock) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete stock where id_stock = ?");
            ps.setInt(1, stock.getId_stock());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Stock_Historico() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select sum(s.stock) as stock, s.id_productos from stock as s, productos as p where s.id_productos = p.id_productos group by p.id_productos order by p.descripcion");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Stock_Producto stock = new Stock_Producto();
                stock.setStock(tabla.getInt("stock"));

                ProductosDAO prodDAO = new ProductosDAO();
                Productos productos = prodDAO.retrieve(tabla.getInt("id_productos"));
                stock.setProductos(productos);

                lista.add(stock);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Stock_por_Productos(int id_productos) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from stock where id_productos = ?");
            ps.setInt(1, id_productos);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Stock_Producto stock = new Stock_Producto();
                stock.setId_stock(tabla.getInt("id_stock"));
                stock.setStock(tabla.getInt("stock"));
                stock.setObservaciones(tabla.getString("observaciones"));
                stock.setFecha_ingreso(tabla.getDate("fecha_ingreso"));
                stock.setNeto(tabla.getFloat("neto"));
                stock.setIva(tabla.getFloat("iva"));
                stock.setTotal(tabla.getFloat("total"));

                ProductosDAO prodDAO = new ProductosDAO();
                Productos productos = prodDAO.retrieve(tabla.getInt("id_productos"));
                stock.setProductos(productos);

                ProveedoresDAO provDAO = new ProveedoresDAO();
                Proveedores proveedores = provDAO.retrieve(tabla.getInt("rut_proveedor"));
                stock.setProveedores(proveedores);

                Cuentas_BaseDAO cbDAO = new Cuentas_BaseDAO();
                Cuentas_Base cuentas_base = cbDAO.retrieve(tabla.getString("num_cuenta"));
                stock.setCuentas_base(cuentas_base);

                lista.add(stock);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Compras_para_ControlPago() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT num_factura, rut_proveedor, sum(total) total FROM `stock` group by num_factura, rut_proveedor");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Stock_Producto stock = new Stock_Producto();
                stock.setNum_factura(tabla.getInt("num_factura"));
                stock.setTotal(tabla.getInt("total"));

                ProveedoresDAO provDAO = new ProveedoresDAO();
                Proveedores proveedores = provDAO.retrieve(tabla.getInt("rut_proveedor"));
                stock.setProveedores(proveedores);

                lista.add(stock);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Compras_para_ControlPago_por_Proveedor(int rut_proveedor) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT num_factura, rut_proveedor, sum(total) total FROM `stock` where rut_proveedor = ? group by num_factura, rut_proveedor");
            ps.setInt(1, rut_proveedor);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Stock_Producto stock = new Stock_Producto();
                stock.setNum_factura(tabla.getInt("num_factura"));
                stock.setTotal(tabla.getInt("total"));

                ProveedoresDAO provDAO = new ProveedoresDAO();
                Proveedores proveedores = provDAO.retrieve(tabla.getInt("rut_proveedor"));
                stock.setProveedores(proveedores);

                lista.add(stock);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Compras_para_Comprobante() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT num_factura, rut_proveedor, sum(total) total, fecha_ingreso FROM stock where (num_factura, rut_proveedor) not in (select cf.num_factura, cf.rut_proveedor from comprobante cf where cf.num_factura is not null and cf.rut_proveedor is not null) group by num_factura, rut_proveedor");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Stock_Producto stock = new Stock_Producto();
                stock.setNum_factura(tabla.getInt("num_factura"));
                stock.setTotal(tabla.getInt("total"));
                stock.setFecha_ingreso(tabla.getDate("fecha_ingreso"));

                ProveedoresDAO provDAO = new ProveedoresDAO();
                Proveedores proveedores = provDAO.retrieve(tabla.getInt("rut_proveedor"));
                stock.setProveedores(proveedores);

                lista.add(stock);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Factura_Compras_para_Libro_Diario(int rut_proveedor, int num_factura) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT num_factura, rut_proveedor, sum(total) total, num_cuenta FROM stock where rut_proveedor = ? and num_factura = ? group by num_factura, rut_proveedor, num_cuenta");
            ps.setInt(1, rut_proveedor);
            ps.setInt(2, num_factura);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Stock_Producto stock = new Stock_Producto();
                stock.setNum_factura(tabla.getInt("num_factura"));
                stock.setTotal(tabla.getInt("total"));

                ProveedoresDAO provDAO = new ProveedoresDAO();
                Proveedores proveedores = provDAO.retrieve(tabla.getInt("rut_proveedor"));
                stock.setProveedores(proveedores);

                Cuentas_BaseDAO cbDAO = new Cuentas_BaseDAO();
                Cuentas_Base cuentas_base = cbDAO.retrieve(tabla.getString("num_cuenta"));
                stock.setCuentas_base(cuentas_base);

                lista.add(stock);
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
