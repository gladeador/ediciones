package cl.ediciones.controller.proveedores;


import cl.ediciones.model.dao.ProveedoresDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_ProveedorAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ArrayList lista_proveedores= new ArrayList();
        
       ProveedoresDAO proveDAO = new ProveedoresDAO();
        lista_proveedores = proveDAO.traerTodos_Proveedores();
        
        request.setAttribute("lista_proveedores", lista_proveedores);
        
        return mapping.findForward("ActualizarProveedor");
    }
}
