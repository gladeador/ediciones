/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ediciones.model.dao;

import cl.ediciones.model.Bodega;
import cl.ediciones.model.NotaCredito;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author EGuzman
 */
public class BodegaDAO {

    public int busca_stock_producto(int id_producto) throws SQLException {
        int stock_bodega = 0;

        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        CallableStatement ps;
        try {

            ps = con.prepareCall("{call stock_bodega(" + "?" + ")}");
            ps.setInt(1, id_producto);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                stock_bodega = tabla.getInt("stock_bodega");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            con.rollback();
        }
        con.close();
        return stock_bodega;
    }

    public void limpiaBodega(NotaCredito notacredito) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        CallableStatement ps;
        try {

            ps = con.prepareCall("{call actualiza_stock(" + "?,?,?" + ")}");
            ps.setInt(1, notacredito.getId_factura());
            ps.setInt(2, notacredito.getId_notacredito());
            ps.setString(3, notacredito.getEstado());

            ps.executeQuery();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
    

}
