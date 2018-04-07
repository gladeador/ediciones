/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ediciones.controller.nota_credito;

import cl.ediciones.model.NotaCredito;
import cl.ediciones.model.dao.BodegaDAO;
import cl.ediciones.model.dao.NotaCreditoDAO;
import cl.ediciones.util.FechaStr;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Enrique Guzmán Magna
 */
public class NuevaNotaCreditoAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        java.sql.Date fechaSQL = null;

        
        int id_factura = 0;
        int sub_total = 0;
        int descuento = 0;
        int neto = 0;
        int iva = 0;
        int total = 0;
        long longDate = 0;
        
        String estado = request.getParameter("estado");
        String id_notacredito = request.getParameter("id_notacredito");

        try {
            id_factura = Integer.parseInt(request.getParameter("id_factura"));
        } catch (Exception e) {
        }
        try {
            sub_total = Integer.parseInt(request.getParameter("sub_total"));
        } catch (Exception e) {
        }
        try {
            descuento = Integer.parseInt(request.getParameter("por_desc"));
        } catch (Exception e) {
        }
        try {
            neto = Integer.parseInt(request.getParameter("neto"));
        } catch (Exception e) {
        }
        try {
            iva = Integer.parseInt(request.getParameter("iva"));
        } catch (Exception e) {
        }
        try {
            total = Integer.parseInt(request.getParameter("total"));
        } catch (Exception e) {
        }
        try {
            longDate = FechaStr.StringToLongDate(request.getParameter("fecha_notacredito"));
            fechaSQL = new java.sql.Date(longDate);
        } catch (Exception e) {
        }

        NotaCredito notacredito = new NotaCredito();
        
        notacredito.setEstado(estado);
        notacredito.setEstado(id_notacredito);
        notacredito.setId_factura(id_factura);
        notacredito.setSubtotal(sub_total);
        notacredito.setDescuento(descuento);
        notacredito.setNeto(neto);
        notacredito.setIva(iva);
        notacredito.setTotal(total);
        notacredito.setFecha_notacredito(fechaSQL);
        notacredito.setId_notacredito(Integer.parseInt(id_notacredito));
        //notacredito.setEstado(estado);

        NotaCreditoDAO notcreDAO = new NotaCreditoDAO();
        notcreDAO.update(notacredito);

        BodegaDAO limpiaNotaCRedito= new BodegaDAO();
        limpiaNotaCRedito.limpiaBodega(notacredito);
        

        request.setAttribute("NotaCredito", notacredito);
        return mapping.findForward("NuevaNotaCredito");
    }
}
