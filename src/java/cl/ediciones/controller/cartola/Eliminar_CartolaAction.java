package cl.ediciones.controller.cartola;

import cl.ediciones.model.dao.CartolaDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Eliminar_CartolaAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String id_cartola = request.getParameter("id_cartola");
        
        CartolaDAO carDAO = new CartolaDAO();
        carDAO.deleteCartola(Integer.parseInt(id_cartola));
        
        return mapping.findForward("EliminarCartola");
    }
}
