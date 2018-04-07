package cl.ediciones.controller.informes;

import cl.ediciones.model.Region;
import cl.ediciones.model.dao.ClientesDAO;
import cl.ediciones.model.dao.RegionDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ListaClientesAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id_region = request.getParameter("id_region");
        String id_ciudad = request.getParameter("id_ciudad");

        if (id_region == null) {
            id_region = "";
        }
        if (id_ciudad == null) {
            id_ciudad = "";
        }
        ArrayList lista_regiones = new ArrayList();
        ArrayList lista_clientes = new ArrayList();

        RegionDAO regDAO = new RegionDAO();
        ClientesDAO cliDAO = new ClientesDAO();

        if (id_region.equals("")) {
            lista_regiones = regDAO.traerRegion();
            lista_clientes = cliDAO.traerTodos_Clientes();
        } else {
            Region region = new Region();
            region = regDAO.retrive_Region(Integer.parseInt(id_region));
            lista_regiones.add(region);

            if (id_ciudad.equals("")) {
                lista_clientes = cliDAO.traerTodos_Clientes_Region(Integer.parseInt(id_region));
            } else {
                lista_clientes = cliDAO.traerTodos_Clientes_Comuna(Integer.parseInt(id_ciudad));
            }
        }

        request.setAttribute("lista_regiones", lista_regiones);
        request.setAttribute("lista_clientes", lista_clientes);

        return mapping.findForward("ListaClientes");
    }
}
