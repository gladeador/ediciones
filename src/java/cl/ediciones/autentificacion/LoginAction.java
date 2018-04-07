package cl.ediciones.autentificacion;

import cl.ediciones.model.Fecha_Actual;
import cl.ediciones.model.Usuarios;
import cl.ediciones.model.dao.Fecha_ActualDAO;
import cl.ediciones.model.dao.UsuariosDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut = request.getParameter("rut");
        String clave = request.getParameter("clave");

        System.out.println("Rut: " + rut);
        System.out.println("Clave: " + clave);

        if (rut.equals("") || clave.equals("")) {
            System.out.println("(Admin) El usuario no se encuentra en nuestros registros");
            return mapping.findForward("error");
        }


        Usuarios usuario = new Usuarios();
        UsuariosDAO usuDAO = new UsuariosDAO();
        usuario = usuDAO.retrieve(Integer.parseInt(rut));

        if (usuario == null) {
            System.out.println("(Admin) El usuario no se encuentra en nuestros registros");
            return mapping.findForward("error");
        }

        if (usuario.getClave().equals(clave)) {
            Fecha_ActualDAO fecDAO = new Fecha_ActualDAO();
            Fecha_Actual fecha = fecDAO.retrieve();
            
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            sesion.setAttribute("fecha_actual", fecha);
            return mapping.findForward("intermedia");
        } else {
            System.out.println("(usuarioDBS) Los datos Ingresados no coinciden con nuestros registros");
            return mapping.findForward("error");
        }
    }
}