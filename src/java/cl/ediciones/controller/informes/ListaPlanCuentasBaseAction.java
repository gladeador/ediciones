package cl.ediciones.controller.informes;

import cl.ediciones.model.dao.Cuentas_BaseDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ListaPlanCuentasBaseAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        ArrayList lista = cueDAO.traerTodos_Cuentas_Base_Padre();
        
        request.setAttribute("Lista_Cuentas", lista);
        
        return mapping.findForward("ListaPlanCuentasBase");
    }
}
