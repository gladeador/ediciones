package cl.ediciones.controller.salidas;

import cl.ediciones.model.Salidas;
import cl.ediciones.model.dao.SalidasDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalidasDWR {
    
    public ArrayList detalle_salidas(String id_tipo_salida, String id_productos) throws SQLException{
        ArrayList lista_salidas = new ArrayList();
        SalidasDAO salDAO = new SalidasDAO();
        lista_salidas = salDAO.Detalle_Salidas(id_tipo_salida, id_productos);
        return lista_salidas;
    }
    
    public Salidas buscar_salidas(int id_salida) throws SQLException{
        SalidasDAO salDAO = new SalidasDAO();
        Salidas salidas = salDAO.retrive(id_salida);
        return salidas;
    }
}
