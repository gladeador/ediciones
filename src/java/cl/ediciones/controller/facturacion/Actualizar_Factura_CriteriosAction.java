package cl.ediciones.controller.facturacion;

import cl.ediciones.model.dao.FacturaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_Factura_CriteriosAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut_cliente = request.getParameter("rut_cliente_b");
        String rut = request.getParameter("rut");
        String num_factura = request.getParameter("num_factura");
        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
        String estado = request.getParameter("estado");
        
        FacturaDAO facDAO = new FacturaDAO();
        ArrayList lista_facturas_nuevas = new ArrayList();
        ArrayList lista_facturas_emitidas = new ArrayList();

        if (estado.equals("")) {
            lista_facturas_nuevas = facDAO.traerTodos_Factura_Nuevas_Criterios(rut_cliente, rut, num_factura, mes, ano, estado);
            lista_facturas_emitidas = facDAO.traerTodos_Factura_Emitidas_Criterios(rut_cliente, rut, num_factura, mes, ano, estado);
        } else if(estado.equals("N")){
            lista_facturas_nuevas = facDAO.traerTodos_Factura_Nuevas_Criterios(rut_cliente, rut, num_factura, mes, ano, estado);
        } else if(estado.equals("E")){
            lista_facturas_emitidas = facDAO.traerTodos_Factura_Emitidas_Criterios(rut_cliente, rut, num_factura, mes, ano, estado);
        }

        request.setAttribute("lista_facturas_nuevas", lista_facturas_nuevas);
        request.setAttribute("lista_facturas_emitidas", lista_facturas_emitidas);

        return mapping.findForward("ActualizaFactura");
    }
}