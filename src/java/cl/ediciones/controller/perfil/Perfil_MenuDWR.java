package cl.ediciones.controller.perfil;

import cl.ediciones.model.Perfil_Menu;
import cl.ediciones.model.dao.Perfil_MenuDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Perfil_MenuDWR {

    public ArrayList traer_todos_por_perfil(int id_perfil) throws SQLException {
        Perfil_MenuDAO pmDAO = new Perfil_MenuDAO();
        ArrayList lista_Tipos = pmDAO.traerPerfil_Menu_por_Perfil(id_perfil);
        return lista_Tipos;
    }

    public void agregar_Menu(int id_perfil, String id_menu) throws SQLException {
        Perfil_MenuDAO pmDAO = new Perfil_MenuDAO();
        Perfil_Menu pm = pmDAO.retrive_Perfil_Menu(id_perfil, id_menu);
        if (pm == null) {
            pmDAO.createPerfil_Menu(id_perfil, id_menu);
        }
    }

    public void eliminar_Menu(int id_perfil, String id_menu) throws SQLException {
        Perfil_MenuDAO pmDAO = new Perfil_MenuDAO();
        Perfil_Menu pm = pmDAO.retrive_Perfil_Menu(id_perfil, id_menu);
        if (pm != null) {
            pmDAO.deletePerfil_Menu(id_perfil, id_menu);
        }
    }
}
