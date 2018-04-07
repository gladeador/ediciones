package cl.ediciones.controller.gastos;

import cl.ediciones.model.dao.GastosDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Eliminar_GastosAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id_gastos = request.getParameter("id_gastos");

        GastosDAO gasDAO = new GastosDAO();
        gasDAO.delete(Integer.parseInt(id_gastos));

        return mapping.findForward("EliminarGastos");
    }
}
