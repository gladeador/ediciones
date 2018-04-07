package cl.ediciones.controller.usuarios;

import cl.ediciones.model.Usuarios;
import cl.ediciones.model.dao.UsuariosDAO;
import java.sql.SQLException;

public class UsuariosDWR {
    
    public Usuarios buscar_Usuarios(int rut) throws SQLException{
        UsuariosDAO usuDAO = new UsuariosDAO();
        Usuarios usuarios = usuDAO.retrieve(rut);
        return usuarios;
    }
}
