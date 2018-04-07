package cl.ediciones.controller.informes;

import cl.ediciones.model.dao.ComprobanteDAO;
import cl.ediciones.model.dao.Cuentas_BaseDAO;
import cl.ediciones.util.FechaStr;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Informe_Libro_MayorAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String mes_inicio = request.getParameter("mes_inicio");
        String ano_inicio = request.getParameter("ano_inicio");
        String mes_fin = request.getParameter("mes_fin");
        String ano_fin = request.getParameter("ano_fin");
        String fecha_inicio = "01/" + mes_inicio + "/" + ano_inicio;

        Cuentas_BaseDAO cbDAO = new Cuentas_BaseDAO();
        ArrayList lista_cuentas = cbDAO.traerTodos_Cuentas_Base_Ultimo_Hijo();

        ComprobanteDAO cliDAO = new ComprobanteDAO();
        ArrayList lista_comprobantes = cliDAO.traerTodos_Libro_Mayor_por_Fecha(mes_inicio, mes_fin, ano_inicio, ano_fin);

        ArrayList lista_saldo_anterior = cliDAO.traerTodos_Saldo_Anterior_Libro_Mayor(fecha_inicio);

        request.setAttribute("lista_cuentas", lista_cuentas);
        request.setAttribute("lista_comprobantes", lista_comprobantes);
        request.setAttribute("lista_saldo_anterior", lista_saldo_anterior);

        return mapping.findForward("InformeLibroMayor");
    }
}
