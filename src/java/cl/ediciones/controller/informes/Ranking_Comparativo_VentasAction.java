package cl.ediciones.controller.informes;

import cl.ediciones.model.Factura;
import cl.ediciones.model.dao.ClientesDAO;
import cl.ediciones.model.dao.FacturaDAO;
import cl.ediciones.model.dao.InformesDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Ranking_Comparativo_VentasAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ano_1 = request.getParameter("ano_1");
        String ano_2 = request.getParameter("ano_2");
        
        ClientesDAO cliDAO = new ClientesDAO();
        ArrayList lista_clientes = cliDAO.traerTodos_Clientes_por_Ranking(ano_2);
        
        InformesDAO infDAO = new InformesDAO();
        ArrayList primer_ano = infDAO.Ventas_por_Año(ano_1);
        ArrayList segundo_ano = infDAO.Ventas_por_Año(ano_2);
        
        FacturaDAO facDAO = new FacturaDAO();
        Factura total_ano_1 = facDAO.total_ventas_ano(ano_1);
        Factura total_ano_2 = facDAO.total_ventas_ano(ano_2);
        
        request.setAttribute("Lista_clientes", lista_clientes);
        request.setAttribute("Lista_primer_ano", primer_ano);
        request.setAttribute("Lista_segundo_ano", segundo_ano);
        request.setAttribute("total_ano_1", total_ano_1);
        request.setAttribute("total_ano_2", total_ano_2);
        
        return mapping.findForward("RankingComparativoVentas");
    }
}
