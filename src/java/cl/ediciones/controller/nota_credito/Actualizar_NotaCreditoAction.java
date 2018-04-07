package cl.ediciones.controller.nota_credito;

import cl.ediciones.model.dao.NotaCreditoDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_NotaCreditoAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        NotaCreditoDAO notcreDAO = new NotaCreditoDAO();
        ArrayList lista_notacredito = notcreDAO.traerTodos_NotaCredito();

        request.setAttribute("lista_notacredito", lista_notacredito);

        return mapping.findForward("ActualizaNotaCredito");
    }
}