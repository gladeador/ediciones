package cl.ediciones.controller.informes;

import cl.ediciones.model.dao.InformesDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Ventas_Por_FacturaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
        
        InformesDAO infDAO = new InformesDAO();
        ArrayList lista_facturas = infDAO.Ventas_por_Factura(mes, ano);
        
        
        request.setAttribute("Lista_facturas", lista_facturas);        
        
        return mapping.findForward("VentaPorFactura");
    }
}
