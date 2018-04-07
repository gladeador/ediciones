package cl.ediciones.controller.factura_productos;

import cl.ediciones.model.Factura_Productos;
import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.Productos;
import cl.ediciones.model.dao.NotaCredito_ProductosDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotaCredito_ProductosDWR {

//    public ArrayList agregar_Productos(String id_productos, String id_notacredito, String cantidad) throws SQLException {
//        Factura_Productos factura_productos = new Factura_Productos();
//        factura_productos.setCantidad(Integer.parseInt(cantidad));
//
//        Guia_Despacho guia = new Guia_Despacho();
//        guia.setId_guia_despacho(Integer.parseInt(id_notacredito));
//        factura_productos.setGuia_despacho(guia);
//
//        Productos productos = new Productos();
//        productos.setId_productos(Integer.parseInt(id_productos));
//        factura_productos.setProductos(productos);
//
//        NotaCredito_ProductosDAO notcreDAO = new NotaCredito_ProductosDAO();
//        notcreDAO.create(factura_productos);
//
//        ArrayList lista = notcreDAO.retrieve(Integer.parseInt(id_notacredito));
//        return lista;
//    }
    
    public ArrayList buscar_Productos(String id_notacredito) throws SQLException {
        NotaCredito_ProductosDAO notcreDAO = new NotaCredito_ProductosDAO();
        ArrayList lista = notcreDAO.retrieve(Integer.parseInt(id_notacredito));
        return lista;
    }
    
    public ArrayList eliminar_Productos(String id_notacredito,String id_productos) throws SQLException {
        NotaCredito_ProductosDAO notcreDAO = new NotaCredito_ProductosDAO();
        notcreDAO.delete(Integer.parseInt(id_notacredito), Integer.parseInt(id_productos));

        ArrayList lista = notcreDAO.retrieve(Integer.parseInt(id_notacredito));
        return lista;
    }
     public ArrayList agregar_Productos(String id_producto,String id_notacredito,String cantidad) throws SQLException {
        NotaCredito_ProductosDAO facproDAO = new NotaCredito_ProductosDAO();
        facproDAO.create(Integer.parseInt(id_producto),Integer.parseInt(id_notacredito),Integer.parseInt(cantidad));
        
        ArrayList lista = facproDAO.retrieve(Integer.parseInt(id_notacredito));
        return lista;
    }
}
