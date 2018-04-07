/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ediciones.controller.boleta;


import cl.ediciones.model.Boleta;
import cl.ediciones.model.dao.BoletaDAO;
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
public class NuevaBoletaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        java.sql.Date fechaSQL = null;


        int sub_total = 0;
        int descuento = 0;
        int neto = 0;
        int iva = 0;
        int total = 0;
        long longDate = 0;
        
        String id_boleta = request.getParameter("id_boleta");

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
            longDate = FechaStr.StringToLongDate(request.getParameter("fecha_boleta"));
            fechaSQL = new java.sql.Date(longDate);
        } catch (Exception e) {
        }

        Boleta boleta = new Boleta();

        boleta.setSubtotal(sub_total);
        boleta.setDescuento(descuento);
        boleta.setNeto(neto);
        boleta.setIva(iva);
        boleta.setTotal(total);
        boleta.setFecha_boleta(fechaSQL);
        boleta.setId_boleta(Integer.parseInt(id_boleta));
        //boleta.setEstado(estado);

        BoletaDAO facDAO = new BoletaDAO();
        facDAO.update(boleta);


        request.setAttribute("Boleta", boleta);
        return mapping.findForward("NuevaBoleta");
    }
}
