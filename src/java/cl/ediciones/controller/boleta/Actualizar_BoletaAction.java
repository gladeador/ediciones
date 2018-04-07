package cl.ediciones.controller.boleta;

import cl.ediciones.controller.facturacion.*;
import cl.ediciones.model.dao.BoletaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_BoletaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        BoletaDAO bolDAO = new BoletaDAO();
        ArrayList lista_boleta = bolDAO.traerTodos_Boleta();

        request.setAttribute("lista_boleta", lista_boleta);

        return mapping.findForward("ActualizaBoleta");
    }
}