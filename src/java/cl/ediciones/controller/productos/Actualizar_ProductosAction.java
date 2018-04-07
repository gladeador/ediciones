package cl.ediciones.controller.productos;

import cl.ediciones.model.dao.ProductosDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Actualizar_ProductosAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ProductosDAO proDAO = new ProductosDAO();
        ArrayList lista_productos = proDAO.traerTodos_Productos();
        
        request.setAttribute("lista_productos", lista_productos);
        
        return mapping.findForward("ActualizarProductos");
    }
}
