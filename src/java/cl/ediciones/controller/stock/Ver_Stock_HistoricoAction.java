package cl.ediciones.controller.stock;

import cl.ediciones.model.dao.Stock_ProductoDAO;
import cl.ediciones.model.dao.Tipo_SalidaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Ver_Stock_HistoricoAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Stock_ProductoDAO stoDAO = new Stock_ProductoDAO();
        ArrayList lista_stock = stoDAO.traerTodos_Stock_Historico();
        
        Tipo_SalidaDAO t_sDAO = new Tipo_SalidaDAO();
        ArrayList lista_tipo_salida = t_sDAO.traerTodos_Tipo_Salida();
        
        request.setAttribute("lista_stock", lista_stock);
        request.setAttribute("lista_tipo_salida", lista_tipo_salida);
        request.setAttribute("lista_tipo_salida_2", lista_tipo_salida);
        
        return mapping.findForward("VerStockHistorico");
    }
}
