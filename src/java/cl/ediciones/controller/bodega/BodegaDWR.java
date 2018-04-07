/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ediciones.controller.bodega;

import cl.ediciones.model.Bodega;
import cl.ediciones.model.dao.BodegaDAO;
import java.sql.SQLException;

/**
 *
 * @author EGuzman
 */
public class BodegaDWR {
    
    
    public int buscarStockBodega(int id_producto) throws SQLException {
        BodegaDAO bodegaDAO = new BodegaDAO();
        int stock_cantidad = bodegaDAO.busca_stock_producto(id_producto);
        return stock_cantidad;
    }
}
