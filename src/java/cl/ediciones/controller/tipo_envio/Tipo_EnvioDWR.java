package cl.ediciones.controller.tipo_envio;

import cl.ediciones.model.dao.Tipo_EnvioDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tipo_EnvioDWR {
    
    public String agregar_Tipo_Envio(String descripcion) throws SQLException {
        Tipo_EnvioDAO teDAO = new Tipo_EnvioDAO();
        String ok = teDAO.createTipo_Envio(descripcion);
        return ok;
    }
    
    public ArrayList traer_todos() throws SQLException{
        Tipo_EnvioDAO teDAO = new Tipo_EnvioDAO();
        ArrayList lista_Tipos = teDAO.traerTipo_Envio();
        return lista_Tipos;
    }
   
    public void eliminar_Tipo_Envio(String id_tipo_envio) throws SQLException{
        Tipo_EnvioDAO teDAO = new Tipo_EnvioDAO();
        teDAO.deleteTipo_Envio(Integer.parseInt(id_tipo_envio));
    }

}
