package cl.ediciones.controller.informes;

import cl.ediciones.model.Clientes;
import cl.ediciones.model.Region;
import cl.ediciones.model.dao.ClientesDAO;
import cl.ediciones.model.dao.RegionDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Lista_Pagos_ClientesAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut = request.getParameter("rut_cliente");
        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");

        ClientesDAO cliDAO = new ClientesDAO();
        Clientes clientes = cliDAO.retrieve(Integer.parseInt(rut));
        
        request.setAttribute("clientes", clientes);

        return mapping.findForward("ListaPagosClientes");
    }
}
