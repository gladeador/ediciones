package cl.ediciones.controller.productos;

import cl.ediciones.model.Productos;
import cl.ediciones.model.dao.ProductosDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Agregar_ProductosAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String descripcion = request.getParameter("descripcion");
        String desc_corta = request.getParameter("desc_corta");
        String valor_neto = request.getParameter("valor_neto");

        Productos producto = new Productos();

        producto.setDescripcion(descripcion);
        producto.setDesc_corta(desc_corta);
        producto.setValor_neto(Integer.parseInt(valor_neto));
        
        ProductosDAO proDAO = new ProductosDAO();
        proDAO.create(producto);

        return mapping.findForward("AgregarProductos");
    }
}
