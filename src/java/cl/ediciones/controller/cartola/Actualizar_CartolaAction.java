package cl.ediciones.controller.cartola;

import cl.ediciones.model.dao.CartolaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_CartolaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CartolaDAO carDAO = new CartolaDAO();
        ArrayList lista_cartolas = carDAO.traerCartola();

        request.setAttribute("lista_cartolas", lista_cartolas);

        return mapping.findForward("ActualizarCartola");
    }
}
