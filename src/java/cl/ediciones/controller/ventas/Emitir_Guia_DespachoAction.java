package cl.ediciones.controller.ventas;

import cl.ediciones.model.dao.Guia_DespachoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Emitir_Guia_DespachoAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id_guia_depacho = request.getParameter("id_guia_despacho");

        Guia_DespachoDAO bolDAO = new Guia_DespachoDAO();
        bolDAO.emitir_Guia_Despacho(Integer.parseInt(id_guia_depacho));

        return mapping.findForward("EmitirGuiaDespacho");
    }
}
