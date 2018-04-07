package cl.ediciones.controller.boleta;

import cl.ediciones.model.dao.BoletaDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Emitir_BoletaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id_boleta = request.getParameter("id_boleta");
        
        BoletaDAO bolDAO = new BoletaDAO();
        bolDAO.emitir_Boleta(Integer.parseInt(id_boleta));
        
        return mapping.findForward("EmitirBoleta");
    }
}
