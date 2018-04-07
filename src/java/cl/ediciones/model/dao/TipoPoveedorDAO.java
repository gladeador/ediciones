package cl.ediciones.model.dao;

import cl.ediciones.model.Clientes;
import cl.ediciones.model.Tipo_Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoPoveedorDAO {

    public ArrayList traerTipoProveedor() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from tipo_proveedor");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Tipo_Proveedor tipoProveedor = new Tipo_Proveedor();
                tipoProveedor.setId_tipo_proveedor(tabla.getInt("id_tipo_proveedor"));
                tipoProveedor.setDescripcion(tabla.getString("descripcion"));
                lista.add(tipoProveedor);
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
