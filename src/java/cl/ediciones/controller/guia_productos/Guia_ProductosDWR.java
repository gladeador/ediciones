package cl.ediciones.controller.guia_productos;

import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.Guia_Productos;
import cl.ediciones.model.Productos;
import cl.ediciones.model.dao.Factura_ProductosDAO;
import cl.ediciones.model.dao.Guia_DespachoDAO;
import cl.ediciones.model.dao.Guia_ProductosDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Guia_ProductosDWR {

    public ArrayList agregar_Productos(String id_productos, String id_guia_despacho, String cantidad) throws SQLException {
        Guia_Productos guia_productos = new Guia_Productos();
        guia_productos.setCantidad(Integer.parseInt(cantidad));

        Guia_Despacho guia = new Guia_Despacho();
        guia.setId_guia_despacho(Integer.parseInt(id_guia_despacho));
        guia_productos.setGuia_despacho(guia);

        Productos productos = new Productos();
        productos.setId_productos(Integer.parseInt(id_productos));
        guia_productos.setProductos(productos);

        Guia_ProductosDAO guiDAO = new Guia_ProductosDAO();
        guiDAO.create(guia_productos);

        ArrayList lista = guiDAO.retrieve(Integer.parseInt(id_guia_despacho));
        return lista;
    }

    public ArrayList buscar_Productos(String id_guia_despacho) throws SQLException {
        Guia_ProductosDAO guiDAO = new Guia_ProductosDAO();
        ArrayList lista = guiDAO.retrieve(Integer.parseInt(id_guia_despacho));
        return lista;
    }

    public ArrayList eliminar_Productos(String id_guia_despacho, String id_productos) throws SQLException {
        Guia_ProductosDAO guiDAO = new Guia_ProductosDAO();
        guiDAO.delete(Integer.parseInt(id_productos), Integer.parseInt(id_guia_despacho));

        ArrayList lista = guiDAO.retrieve(Integer.parseInt(id_guia_despacho));
        return lista;
    }

    public ArrayList pasar_a_Factura(String id_guia_despacho, String id_factura) throws SQLException {
        Guia_ProductosDAO gpDAO = new Guia_ProductosDAO();
        Factura_ProductosDAO fpDAO = new Factura_ProductosDAO();
        ArrayList lista = gpDAO.retrieve(Integer.parseInt(id_guia_despacho));
        java.util.Iterator i = lista.iterator();
        while (i.hasNext()) {
            Guia_Productos guia_prod = (Guia_Productos) i.next();
            int cantidad = fpDAO.suma_productos_por_Factura(Integer.parseInt(id_factura), guia_prod.getProductos().getId_productos());
            if (cantidad == 0) {
                fpDAO.create(guia_prod.getProductos().getId_productos(), Integer.parseInt(id_factura), guia_prod.getCantidad());
            } else {
                cantidad = cantidad + guia_prod.getCantidad();
                fpDAO.updateCantidad(guia_prod.getProductos().getId_productos(), Integer.parseInt(id_factura), cantidad);
            }
        }

        Guia_DespachoDAO guiDAO = new Guia_DespachoDAO();
        guiDAO.agrega_Factura(Integer.parseInt(id_guia_despacho), Integer.parseInt(id_factura));

        ArrayList lista_prod = fpDAO.retrieve(Integer.parseInt(id_factura));
        return lista_prod;
    }
}
