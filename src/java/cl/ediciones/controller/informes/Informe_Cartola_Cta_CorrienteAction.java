package cl.ediciones.controller.informes;

import cl.ediciones.model.Cartola;
import cl.ediciones.model.dao.CartolaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Informe_Cartola_Cta_CorrienteAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String mes_inicio = request.getParameter("mes_inicio");
        String ano_inicio = request.getParameter("ano_inicio");
        String mes_fin = request.getParameter("mes_fin");
        String ano_fin = request.getParameter("ano_fin");
        String fecha_inicio = "01/" + mes_inicio + "/" + ano_inicio;

        CartolaDAO carDAO = new CartolaDAO();
        ArrayList lista_cartolas = carDAO.traerTodos_Cartola_por_Fecha(mes_inicio, mes_fin, ano_inicio, ano_fin);

        Cartola cartola = carDAO.traerTodos_Saldo_Anterior_Cartola(fecha_inicio);

        String periodo = mes_inicio + "/" + ano_inicio + " al " + mes_fin + "/" + ano_fin;

        request.setAttribute("lista_cartolas", lista_cartolas);
        request.setAttribute("cartola_saldo_anterior", cartola);
        request.setAttribute("periodo", periodo);

        return mapping.findForward("InformeCartolaCtaCorriente");
    }
}
