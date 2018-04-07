package cl.ediciones.controller.region;

import cl.ediciones.model.dao.RegionDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegionDWR {
    
    public String agregar_Region(String descripcion) throws SQLException {
        RegionDAO teDAO = new RegionDAO();
        String ok = teDAO.createRegion(descripcion);
        return ok;
    }
    
    public ArrayList traer_todos() throws SQLException{
        RegionDAO teDAO = new RegionDAO();
        ArrayList lista_Tipos = teDAO.traerRegion();
        return lista_Tipos;
    }
   
    public void eliminar_Region(String id_region) throws SQLException{
        RegionDAO teDAO = new RegionDAO();
        teDAO.deleteRegion(Integer.parseInt(id_region));
    }

}
