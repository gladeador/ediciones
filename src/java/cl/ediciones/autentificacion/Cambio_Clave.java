package cl.ediciones.autentificacion;

import cl.ediciones.model.Usuarios;
import cl.ediciones.model.dao.UsuariosDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class Cambio_Clave extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession sesion = request.getSession();
        Usuarios usuarios = (Usuarios) sesion.getAttribute("usuario");

        sesion.setAttribute("usuario", null);

        String rut = request.getParameter("rut");
        String nuevaclave = request.getParameter("nuevaclave");

        usuarios = new Usuarios();

        usuarios.setRut(Integer.parseInt(rut));
        usuarios.setClave(nuevaclave);

        UsuariosDAO conDB = new UsuariosDAO();
        conDB.update_Cambio_Clave(usuarios);

        usuarios = conDB.retrieve(Integer.parseInt(rut));

        sesion.setAttribute("usuario", usuarios);

        return mapping.findForward("CambioClave");

    }
}
