package cl.ediciones.controller.informes;

import cl.ediciones.model.dao.ClientesDAO;
import cl.ediciones.model.dao.InformesDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Informe_CobranzaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
        
        ClientesDAO cliDAO = new ClientesDAO();
        ArrayList lista_clientes = cliDAO.traerTodos_Clientes_con_Factura(mes, ano);
        
        InformesDAO infDAO = new InformesDAO();
        ArrayList lista_facturas = infDAO.Ventas_por_Factura(mes, ano);
        
        
        request.setAttribute("lista_clientes", lista_clientes);  
        request.setAttribute("Lista_facturas", lista_facturas);        
        
        return mapping.findForward("InformeCobranza");
    }
}
