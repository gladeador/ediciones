package cl.ediciones.controller.usuarios;

import cl.ediciones.model.dao.UsuariosDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_UsuariosAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        UsuariosDAO cliDAO = new UsuariosDAO();
        ArrayList lista_usuarios = cliDAO.traerTodos_Usuarios();
        
        request.setAttribute("lista_usuarios", lista_usuarios);
        
        return mapping.findForward("ActualizarUsuarios");
    }
}
