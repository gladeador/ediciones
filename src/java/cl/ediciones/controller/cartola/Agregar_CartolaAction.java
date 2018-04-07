package cl.ediciones.controller.cartola;

import cl.ediciones.model.Comuna;
import cl.ediciones.model.Cartola;
import cl.ediciones.model.Fono_Contacto;
import cl.ediciones.model.dao.CartolaDAO;
import cl.ediciones.model.dao.Fono_ContactoDAO;
import cl.ediciones.util.FechaStr;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Agregar_CartolaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fecha = request.getParameter("fecha");
        String descripcion = request.getParameter("descripcion");
        String documento = request.getParameter("documento");
        String abonos = request.getParameter("abonos");
        String cargos = request.getParameter("cargos");

        Cartola cartola = new Cartola();

        cartola.setFecha(FechaStr.StringToDate(fecha));
        cartola.setDescripcion(descripcion);
        cartola.setDocumento(documento);
        cartola.setAbonos(Integer.parseInt(abonos));
        cartola.setCargos(Integer.parseInt(cargos));

        CartolaDAO carDAO = new CartolaDAO();
        carDAO.createCartola(cartola);

        return mapping.findForward("AgregarCartola");
    }
}
