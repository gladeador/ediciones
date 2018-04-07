package cl.ediciones.controller.clientes;

import cl.ediciones.model.dao.ClientesDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Eliminar_ClientesAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String rut = request.getParameter("rut");
        
        ClientesDAO cliDAO = new ClientesDAO();
        cliDAO.delete(Integer.parseInt(rut));
        
        return mapping.findForward("EliminarClientes");
    }
}
