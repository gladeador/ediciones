package cl.ediciones.controller.facturacion;

import cl.ediciones.model.dao.FacturaDAO;
import cl.ediciones.model.dao.Factura_ProductosDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Anula_FacturaAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id_factura = request.getParameter("id_factura");

        FacturaDAO factDAO = new FacturaDAO();
        factDAO.anular_Factura(Integer.parseInt(id_factura));
        
        Factura_ProductosDAO fpDAO = new Factura_ProductosDAO();
        fpDAO.delete_por_Factura(Integer.parseInt(id_factura));
        
        return mapping.findForward("AnulaFactura");
    }
}
