package cl.ediciones.controller.informes;

import cl.ediciones.model.dao.Libro_ComprasDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Genera_Libro_ComprasAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
        
        Libro_ComprasDAO lcDAO = new Libro_ComprasDAO();
        ArrayList compras = lcDAO.traerTodos_Libro_Compras(mes, ano);
        
        request.setAttribute("Libro_Compras", compras);
        
        return mapping.findForward("GeneraLibroCompras");
    }
}
