package cl.ediciones.controller.salidas;

import cl.ediciones.model.Productos;
import cl.ediciones.model.Salidas;
import cl.ediciones.model.Tipo_Salida;
import cl.ediciones.model.dao.SalidasDAO;
import cl.ediciones.util.FechaStr;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Agregar_SalidasAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id_tipo_salida = request.getParameter("id_tipo_salida");
        String id_productos = request.getParameter("id_productos");
        String cantidad = request.getParameter("cantidad");
        String fecha_salida = request.getParameter("fecha_salida");
        String observaciones = request.getParameter("observaciones");

        Salidas salida = new Salidas();

        salida.setCantidad(Integer.parseInt(cantidad));
        salida.setFecha_salida(FechaStr.stringToDate(fecha_salida));
        salida.setObservaciones(observaciones);
        
        Tipo_Salida tipo_salida = new Tipo_Salida();
        tipo_salida.setId_tipo_salida(Integer.parseInt(id_tipo_salida));
        salida.setTipo_salida(tipo_salida);
        
        Productos productos = new Productos();
        productos.setId_productos(Integer.parseInt(id_productos));
        salida.setProductos(productos);
        
        SalidasDAO salDAO = new SalidasDAO();
        salDAO.create(salida);

        return mapping.findForward("AgregarSalidas");
    }
}
