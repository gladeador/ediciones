/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ediciones.controller.nota_credito;

import cl.ediciones.controller.facturacion.*;
import cl.ediciones.model.NotaCredito;
import cl.ediciones.model.dao.BodegaDAO;
import cl.ediciones.model.dao.NotaCreditoDAO;
import java.sql.SQLException;
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
public class EliminaNotaCreditoAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id_factura = 0;
        String id_notacredito = request.getParameter("id_notacredito");
        String estado = request.getParameter("estado");
        NotaCredito notacredito = new NotaCredito();

        NotaCreditoDAO factDAO = new NotaCreditoDAO();
        factDAO.delete(id_notacredito);

        notacredito.setEstado(estado);
        notacredito.setId_notacredito(Integer.parseInt(id_notacredito));

        BodegaDAO limpiaNotaCRedito = new BodegaDAO();
        limpiaNotaCRedito.limpiaBodega(notacredito);
        //request.setAttribute("NotaCredito", id_notacredito);

        return mapping.findForward("EliminarNotaCredito");
    }
}
