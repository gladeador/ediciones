package cl.ediciones.controller.comuna;

import cl.ediciones.model.dao.ComunaDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComunaDWR {
    
    public String agregar_Comuna(String descripcion, String id_region) throws SQLException {
        ComunaDAO teDAO = new ComunaDAO();
        String ok = teDAO.createComuna(descripcion, Integer.parseInt(id_region));
        return ok;
    }
    
    public ArrayList traer_todos() throws SQLException{
        ComunaDAO teDAO = new ComunaDAO();
        ArrayList lista_comunas = teDAO.traerComuna();
        return lista_comunas;
    }
   
    public ArrayList traer_todos_por_Region(String id_region) throws SQLException{
        ComunaDAO teDAO = new ComunaDAO();
        ArrayList lista_comunaes = teDAO.traerTodo_Comuna_por_Region(Integer.parseInt(id_region));
        return lista_comunaes;
    }
   
    public void eliminar_Comuna(String id_comuna) throws SQLException{
        ComunaDAO teDAO = new ComunaDAO();
        teDAO.deleteComuna(Integer.parseInt(id_comuna));
    }

}
