package cl.ediciones.controller.gastos;

import cl.ediciones.model.Gastos;
import cl.ediciones.model.dao.GastosDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class GastosDWR {
    
    public Gastos buscar_Gastos(int id_gastos) throws SQLException{
        GastosDAO gasDAO = new GastosDAO();
        Gastos gastos = gasDAO.retrieve(id_gastos);
        return gastos;
    }
      
    public ArrayList buscar_por_Mes_Ano(String mes, String ano) throws SQLException{
        GastosDAO gasDAO = new GastosDAO();
        ArrayList lista = gasDAO.traerTodos_Gastos_por_Mes_Ano(mes, ano);
        return lista;
    }
}
