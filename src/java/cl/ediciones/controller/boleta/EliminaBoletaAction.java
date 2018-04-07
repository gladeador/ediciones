/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ediciones.controller.boleta;

import cl.ediciones.model.dao.BoletaDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author killer
 */
public class EliminaBoletaAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id_boleta = request.getParameter("id_boleta");

        BoletaDAO bolDAO = new BoletaDAO();
        bolDAO.delete(id_boleta);

        //request.setAttribute("Factura", id_boleta);
        return mapping.findForward("EliminarBoleta");
    }
}
