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

public class Informe_Libro_VentasAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
        
        InformesDAO infDAO = new InformesDAO();
        ArrayList lista_facturas = infDAO.Ventas_por_Factura(mes, ano);
        ArrayList lista_boletas = infDAO.Ventas_por_Boleta(mes, ano);
        ArrayList lista_notacredito = infDAO.Ventas_por_Nota_Credito(mes, ano);
        
        
        request.setAttribute("lista_facturas", lista_facturas); 
        request.setAttribute("lista_boletas", lista_boletas); 
        request.setAttribute("lista_notacredito", lista_notacredito);        
        
        return mapping.findForward("InformeLibroVentas");
    }
}
