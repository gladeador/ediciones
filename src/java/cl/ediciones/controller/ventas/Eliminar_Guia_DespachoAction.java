package cl.ediciones.controller.ventas;

import cl.ediciones.model.dao.Guia_DespachoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Eliminar_Guia_DespachoAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String id_guia_despacho = request.getParameter("id_guia_despacho");
        
        Guia_DespachoDAO cliDAO = new Guia_DespachoDAO();
        cliDAO.delete_guia(Integer.parseInt(id_guia_despacho));
        
        return mapping.findForward("EliminarGuiaDespacho");
    }
}
