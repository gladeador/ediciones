package cl.ediciones.controller.fono_contacto;

import cl.ediciones.model.dao.Fono_ContactoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fono_ContactoDWR {
    
    public ArrayList buscar_Fonos_por_Rut_Cliente(int rut) throws SQLException{
        Fono_ContactoDAO fonDAO = new Fono_ContactoDAO();
        ArrayList lista = fonDAO.retrieve_por_Rut_Clientes(rut);
        return lista;
    }
}
