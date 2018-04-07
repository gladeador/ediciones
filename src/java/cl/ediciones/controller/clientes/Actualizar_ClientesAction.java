package cl.ediciones.controller.clientes;

import cl.ediciones.model.dao.ClientesDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_ClientesAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ClientesDAO cliDAO = new ClientesDAO();
        ArrayList lista_clientes = cliDAO.traerTodos_Clientes();

        request.setAttribute("lista_clientes", lista_clientes);

        return mapping.findForward("ActualizarClientes");
    }
}
