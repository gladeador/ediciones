package cl.ediciones.controller.proveedores;

import cl.ediciones.model.Proveedores;
import cl.ediciones.model.dao.ProveedoresDAO;
import java.sql.SQLException;

public class ProveedorDWR {
    
        public Proveedores buscar_Proveedor(int rut) throws SQLException{
        System.out.println("Rut: "+rut);
        ProveedoresDAO provDAO = new ProveedoresDAO();
        Proveedores proveedor = provDAO.retrieve(rut);
        return proveedor;
    }
}
