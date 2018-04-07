package cl.ediciones.controller.perfil;

import cl.ediciones.model.dao.PerfilDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerfilDWR {
    
    public String agregar_Perfil(String descripcion) throws SQLException {
        PerfilDAO teDAO = new PerfilDAO();
        String ok = teDAO.createPerfil(descripcion);
        return ok;
    }
    
    public ArrayList traer_todos() throws SQLException{
        PerfilDAO teDAO = new PerfilDAO();
        ArrayList lista_Tipos = teDAO.traerPerfil();
        return lista_Tipos;
    }
   
    public void eliminar_Perfil(String id_perfil) throws SQLException{
        PerfilDAO teDAO = new PerfilDAO();
        teDAO.deletePerfil(Integer.parseInt(id_perfil));
    }

}
