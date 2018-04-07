package cl.ediciones.controller.comprobante;

import cl.ediciones.model.dao.ComprobanteDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_ComprobanteAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ComprobanteDAO cliDAO = new ComprobanteDAO();
        ArrayList lista_comprobantes = cliDAO.traerTodos_Comprobante();

        request.setAttribute("lista_comprobantes", lista_comprobantes);

        return mapping.findForward("ActualizarComprobante");
    }
}
