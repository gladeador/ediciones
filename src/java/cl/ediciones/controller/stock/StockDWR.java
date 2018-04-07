package cl.ediciones.controller.stock;

import cl.ediciones.model.Stock_Producto;
import cl.ediciones.model.dao.Stock_ProductoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDWR {

    public ArrayList detalle_stock_por_Producto(int id_productos) throws SQLException {
        Stock_ProductoDAO stoDAO = new Stock_ProductoDAO();
        ArrayList lista_stock = stoDAO.traerTodos_Stock_por_Productos(id_productos);
        return lista_stock;
    }
    
    public Stock_Producto buscarStock(String id_stock) throws SQLException{
        Stock_ProductoDAO spDAO = new Stock_ProductoDAO();
        Stock_Producto stock = spDAO.retrive_Stock(Integer.parseInt(id_stock));
        return stock;
    }
}
