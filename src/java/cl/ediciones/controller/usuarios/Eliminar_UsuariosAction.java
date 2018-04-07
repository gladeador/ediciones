package cl.ediciones.controller.usuarios;

import cl.ediciones.model.dao.UsuariosDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Eliminar_UsuariosAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String rut = request.getParameter("rut");
        System.out.println("Rut: "+rut);
        UsuariosDAO usuDAO = new UsuariosDAO();
        usuDAO.delete(Integer.parseInt(rut));
        
        return mapping.findForward("EliminarUsuarios");
    }
}
