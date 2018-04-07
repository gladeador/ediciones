package cl.ediciones.controller.clientes;

import cl.ediciones.model.Clientes;
import cl.ediciones.model.dao.ClientesDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientesDWR {
    
    public Clientes buscar_Clientes(int rut) throws SQLException{
        ClientesDAO cliDAO = new ClientesDAO();
        Clientes clientes = cliDAO.retrieve(rut);
        return clientes;
    }
      
    public ArrayList listar_Clientes() throws SQLException{
        ClientesDAO cliDAO = new ClientesDAO();
        ArrayList lista = cliDAO.traerTodos_Clientes();
        return lista;
    }
    
    public ArrayList listar_Clientes_por_Nombre(String nombre) throws SQLException{
        ClientesDAO cliDAO = new ClientesDAO();
        ArrayList lista = cliDAO.traerTodos_Clientes_por_Nombre(nombre);
        return lista;
    }
}
