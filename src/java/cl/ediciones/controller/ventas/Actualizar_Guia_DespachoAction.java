package cl.ediciones.controller.ventas;

import cl.ediciones.model.dao.Guia_DespachoDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_Guia_DespachoAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        ArrayList lista_guias = guiaDAO.traerTodos_Guia_Despacho();

        request.setAttribute("lista_guias", lista_guias);

        return mapping.findForward("ActualizarGuiaDespacho");
    }
}
