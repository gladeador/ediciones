package cl.ediciones.controller.factura_productos;

import cl.ediciones.model.Factura_Productos;
import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.Productos;
import cl.ediciones.model.dao.Factura_ProductosDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Factura_ProductosDWR {

//    public ArrayList agregar_Productos(String id_productos, String id_guia_despacho, String cantidad) throws SQLException {
//        Factura_Productos factura_productos = new Factura_Productos();
//        factura_productos.setCantidad(Integer.parseInt(cantidad));
//
//        Guia_Despacho guia = new Guia_Despacho();
//        guia.setId_guia_despacho(Integer.parseInt(id_guia_despacho));
//        factura_productos.setGuia_despacho(guia);
//
//        Productos productos = new Productos();
//        productos.setId_productos(Integer.parseInt(id_productos));
//        factura_productos.setProductos(productos);
//
//        Factura_ProductosDAO facDAO = new Factura_ProductosDAO();
//        facDAO.create(factura_productos);
//
//        ArrayList lista = facDAO.retrieve(Integer.parseInt(id_guia_despacho));
//        return lista;
//    }
    public ArrayList buscar_Productos(String id_guia_despacho) throws SQLException {
        Factura_ProductosDAO facDAO = new Factura_ProductosDAO();
        ArrayList lista = facDAO.retrieve(Integer.parseInt(id_guia_despacho));
        return lista;
    }

    public ArrayList eliminar_Productos(String id_factura, String id_productos) throws SQLException {
        Factura_ProductosDAO facDAO = new Factura_ProductosDAO();
        facDAO.delete(Integer.parseInt(id_factura), Integer.parseInt(id_productos));

        ArrayList lista = facDAO.retrieve(Integer.parseInt(id_factura));
        return lista;
    }

    public ArrayList agregar_Productos(String id_producto, String id_factura, String cantidad) throws SQLException {
        Factura_ProductosDAO facproDAO = new Factura_ProductosDAO();
        facproDAO.create(Integer.parseInt(id_producto), Integer.parseInt(id_factura), Integer.parseInt(cantidad));

        ArrayList lista = facproDAO.retrieve(Integer.parseInt(id_factura));
        return lista;
    }
}
