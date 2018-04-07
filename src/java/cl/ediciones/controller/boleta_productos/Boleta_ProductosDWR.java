package cl.ediciones.controller.boleta_productos;

import cl.ediciones.model.Factura_Productos;
import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.Productos;
import cl.ediciones.model.dao.Boleta_ProductosDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Boleta_ProductosDWR {

//    public ArrayList agregar_Productos(String id_productos, String id_boleta, String cantidad) throws SQLException {
//        Factura_Productos factura_productos = new Factura_Productos();
//        factura_productos.setCantidad(Integer.parseInt(cantidad));
//
//        Guia_Despacho guia = new Guia_Despacho();
//        guia.setId_guia_despacho(Integer.parseInt(id_boleta));
//        factura_productos.setGuia_despacho(guia);
//
//        Productos productos = new Productos();
//        productos.setId_productos(Integer.parseInt(id_productos));
//        factura_productos.setProductos(productos);
//
//        Boleta_ProductosDAO bolDAO = new Boleta_ProductosDAO();
//        bolDAO.create(factura_productos);
//
//        ArrayList lista = bolDAO.retrieve(Integer.parseInt(id_boleta));
//        return lista;
//    }
    public ArrayList buscar_Productos(String id_boleta) throws SQLException {
        Boleta_ProductosDAO bolDAO = new Boleta_ProductosDAO();
        ArrayList lista = bolDAO.retrieve(Integer.parseInt(id_boleta));
        return lista;
    }

    public ArrayList eliminar_Productos(String id_boleta, String id_productos) throws SQLException {
        Boleta_ProductosDAO bolDAO = new Boleta_ProductosDAO();
        bolDAO.delete(Integer.parseInt(id_boleta), Integer.parseInt(id_productos));

        ArrayList lista = bolDAO.retrieve(Integer.parseInt(id_boleta));
        return lista;
    }

    public ArrayList agregar_Productos(String id_producto, String id_boleta, String cantidad) throws SQLException {
        Boleta_ProductosDAO facproDAO = new Boleta_ProductosDAO();
        facproDAO.create(Integer.parseInt(id_producto), Integer.parseInt(id_boleta), Integer.parseInt(cantidad));

        ArrayList lista = facproDAO.retrieve(Integer.parseInt(id_boleta));
        return lista;
    }
}
