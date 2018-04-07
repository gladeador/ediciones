package cl.ediciones.controller.banco;

import cl.ediciones.controller.banco.*;
import cl.ediciones.model.dao.BancoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class BancoDWR {
    
    public String agregar_Banco(String descripcion) throws SQLException {
        BancoDAO teDAO = new BancoDAO();
        String ok = teDAO.createBanco(descripcion);
        return ok;
    }
    
    public ArrayList traer_todos() throws SQLException{
        BancoDAO teDAO = new BancoDAO();
        ArrayList lista_Tipos = teDAO.traerBanco();
        return lista_Tipos;
    }
   
    public void eliminar_Banco(String id_banco) throws SQLException{
        BancoDAO teDAO = new BancoDAO();
        teDAO.deleteBanco(Integer.parseInt(id_banco));
    }

}
