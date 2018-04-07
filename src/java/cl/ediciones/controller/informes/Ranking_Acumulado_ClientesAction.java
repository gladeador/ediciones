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

public class Ranking_Acumulado_ClientesAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ano = request.getParameter("ano");
        String mes = null;

        ClientesDAO cliDAO = new ClientesDAO();
        ArrayList lista_clientes = cliDAO.traerTodos_Clientes_por_Ranking(ano);

        InformesDAO infDAO = new InformesDAO();
        ArrayList arreglos = new ArrayList();

        for (int i = 1; i <= 12; i++) {
            if (Integer.toString(i).length() == 1) {
                mes = "0" + i;
            } else {
                mes = "" + i;
            }
            ArrayList lista_facturas = infDAO.Ventas_por_Factura(mes, ano);
            arreglos.add(lista_facturas);
        }

        request.setAttribute("Lista_clientes", lista_clientes);
        request.setAttribute("Lista_arreglos", arreglos);

        return mapping.findForward("RankingAcumuladoClientes");
    }
}
