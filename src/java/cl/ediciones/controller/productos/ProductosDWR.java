package cl.ediciones.controller.productos;

import cl.ediciones.model.Productos;
import cl.ediciones.model.dao.ProductosDAO;
import java.sql.SQLException;

public class ProductosDWR {
    
    public Productos buscar_Productos(int id_productos) throws SQLException{
        ProductosDAO proDAO = new ProductosDAO();
        Productos productos = proDAO.retrieve(id_productos);
        return productos;
    }
}
