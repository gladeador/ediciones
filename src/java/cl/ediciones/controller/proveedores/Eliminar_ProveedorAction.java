package cl.ediciones.controller.proveedores;

import cl.ediciones.model.dao.ProveedoresDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Eliminar_ProveedorAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String rut = request.getParameter("rut");
        
        ProveedoresDAO proveDAO= new ProveedoresDAO();
        proveDAO.delete(Integer.parseInt(rut));
        
        
        return mapping.findForward("EliminarProveedor");
    }
}
