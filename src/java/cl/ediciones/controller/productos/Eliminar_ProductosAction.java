package cl.ediciones.controller.productos;

import cl.ediciones.model.dao.ProductosDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Eliminar_ProductosAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String id_productos = request.getParameter("id_productos");
        
        ProductosDAO proDAO = new ProductosDAO();
        proDAO.delete(Integer.parseInt(id_productos));
        
        return mapping.findForward("EliminarProductos");
    }
}
