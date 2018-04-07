package cl.ediciones.controller.facturacion;

import cl.ediciones.model.dao.FacturaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_Factura_para_ControlPagoAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut_cliente = request.getParameter("rut_cliente_b");

        FacturaDAO facDAO = new FacturaDAO();
        ArrayList lista_facturas = new ArrayList();
        
        if (rut_cliente == null || rut_cliente.equals("Todos")) {
            lista_facturas = facDAO.traerTodos_Factura_para_ControlPago();
        } else {
            lista_facturas = facDAO.traerTodos_Factura_para_ControlPago_por_Cliente(Integer.parseInt(rut_cliente));
        }

        request.setAttribute("lista_facturas", lista_facturas);

        return mapping.findForward("ActualizarPagos");
    }
}
