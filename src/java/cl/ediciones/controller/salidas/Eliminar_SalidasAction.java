package cl.ediciones.controller.salidas;

import cl.ediciones.model.dao.SalidasDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Eliminar_SalidasAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String id_salida = request.getParameter("id_salida");
        
        SalidasDAO cliDAO = new SalidasDAO();
        cliDAO.delete(Integer.parseInt(id_salida));
        
        return mapping.findForward("EliminarSalidas");
    }
}
