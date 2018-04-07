package cl.ediciones.controller.informes;

import cl.ediciones.model.Factura;
import cl.ediciones.model.dao.ClientesDAO;
import cl.ediciones.model.dao.FacturaDAO;
import cl.ediciones.model.dao.InformesDAO;
import cl.ediciones.model.dao.RegionDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Ranking_Ventas_RegionAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ano = request.getParameter("ano");
 
        RegionDAO regDAO = new RegionDAO();
        ArrayList lista_regiones = regDAO.traerRegion();

        ClientesDAO cliDAO = new ClientesDAO();
        ArrayList lista_clientes = cliDAO.traerTodos_Clientes_por_Ranking(ano);

        InformesDAO infDAO = new InformesDAO();
        ArrayList primer_ano = infDAO.Ventas_por_Año(ano);
        ArrayList ranking_region = infDAO.Ranking_Ventas_Regiones(ano);

        FacturaDAO facDAO = new FacturaDAO();
        Factura factura = facDAO.total_ventas_ano(ano);


        request.setAttribute("Lista_regiones", lista_regiones);
        request.setAttribute("Lista_clientes", lista_clientes);
        request.setAttribute("Lista_primer_ano", primer_ano);
        request.setAttribute("total_ano", factura);
        request.setAttribute("ranking_region", ranking_region);

        return mapping.findForward("RankingVentasRegion");
    }
}
