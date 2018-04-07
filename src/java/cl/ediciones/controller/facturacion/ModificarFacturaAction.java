/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ediciones.controller.facturacion;

import cl.ediciones.model.Factura;
import cl.ediciones.model.dao.FacturaDAO;
import cl.ediciones.util.FechaStr;
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
public class ModificarFacturaAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id_factura = request.getParameter("id_factura");
        String sub_total = request.getParameter("sub_total");
        String descuento = request.getParameter("por_desc");
        String neto = request.getParameter("neto");
        String iva = request.getParameter("iva");
        String total = request.getParameter("total");
        String fecha_factura = request.getParameter("fecha_factura");

        Factura factura = new Factura();

        factura.setId_factura(Integer.parseInt(id_factura));
        factura.setSubtotal(Integer.parseInt(sub_total));
        factura.setDescuento(Integer.parseInt(descuento));
        factura.setNeto(Integer.parseInt(neto));
        factura.setIva(Integer.parseInt(iva));
        factura.setTotal(Integer.parseInt(total));
        factura.setFecha_factura(FechaStr.stringToDate(fecha_factura));

        FacturaDAO facDAO = new FacturaDAO();
        facDAO.update(factura);

        return mapping.findForward("ModificarFactura");
    }
}
