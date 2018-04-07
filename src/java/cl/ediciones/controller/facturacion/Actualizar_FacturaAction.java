package cl.ediciones.controller.facturacion;

import cl.ediciones.model.dao.FacturaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_FacturaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut_cliente = request.getParameter("rut_cliente_b");

        FacturaDAO facDAO = new FacturaDAO();
        ArrayList lista_facturas_nuevas = new ArrayList();
        ArrayList lista_facturas_emitidas = new ArrayList();

        if (rut_cliente == null || rut_cliente.equals("Todos")) {
            lista_facturas_nuevas = facDAO.traerTodos_Factura_Nuevas();
            lista_facturas_emitidas = facDAO.traerTodos_Factura_Emitidas_para_Anular();
        } else {
            lista_facturas_nuevas = facDAO.traerTodos_Factura_Nuevas_por_Cliente(Integer.parseInt(rut_cliente));
            lista_facturas_emitidas = facDAO.traerTodos_Factura_Emitidas_para_Anular_por_Cliente(Integer.parseInt(rut_cliente));
        }

        request.setAttribute("lista_facturas_nuevas", lista_facturas_nuevas);
        request.setAttribute("lista_facturas_emitidas", lista_facturas_emitidas);

        return mapping.findForward("ActualizaFactura");
    }
}