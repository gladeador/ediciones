package cl.ediciones.controller.pagos;

import cl.ediciones.model.dao.GastosDAO;
import cl.ediciones.model.dao.Stock_ProductoDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_ControlPago_ComprasAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut_proveedor = request.getParameter("rut_proveedor_b");

        Stock_ProductoDAO spDAO = new Stock_ProductoDAO();
        ArrayList lista_facturas = new ArrayList();
        
        GastosDAO gasDAO = new GastosDAO();
        ArrayList lista_gastos = new ArrayList();
        
        if (rut_proveedor == null || rut_proveedor.equals("Todos")) {
            lista_facturas = spDAO.traerTodos_Factura_Compras_para_ControlPago();
            lista_gastos = gasDAO.traerTodos_Gastos_para_ControlPago();
        } else {
            lista_facturas = spDAO.traerTodos_Factura_Compras_para_ControlPago_por_Proveedor(Integer.parseInt(rut_proveedor));
            lista_gastos = gasDAO.traerTodos_Gastos_para_ControlPago_por_Proveedor(Integer.parseInt(rut_proveedor));
        }

        request.setAttribute("lista_facturas", lista_facturas);
        request.setAttribute("lista_gastos", lista_gastos);

        return mapping.findForward("ActualizarPagosCompras");
    }
}
