package cl.ediciones.model.dao;

import cl.ediciones.model.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAO {

    public ArrayList traer_todoMenu() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from menu");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Menu menu = new Menu();
                menu.setId_menu(tabla.getString("id_menu"));
                lista.add(menu);
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
