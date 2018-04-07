package cl.ediciones.controller.informes;

import cl.ediciones.model.dao.ComprobanteDAO;
import cl.ediciones.util.FechaStr;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Informe_Libro_DiarioAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String mes_inicio = request.getParameter("mes_inicio");
        String ano_inicio = request.getParameter("ano_inicio");
        String mes_fin = request.getParameter("mes_fin");
        String ano_fin = request.getParameter("ano_fin");
        String fecha_inicio = mes_inicio+"-"+ano_inicio;
        String fecha_fin = mes_fin+"-"+ano_fin;
        
        ComprobanteDAO cliDAO = new ComprobanteDAO();
        ArrayList lista_comprobantes = cliDAO.traerTodos_Comprobante_por_Fecha(fecha_inicio, fecha_fin);

        request.setAttribute("lista_comprobantes", lista_comprobantes);       
        
        return mapping.findForward("InformeLibroDiario");
    }
}
