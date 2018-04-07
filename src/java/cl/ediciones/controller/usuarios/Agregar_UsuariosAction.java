package cl.ediciones.controller.usuarios;

import cl.ediciones.model.Usuarios;
import cl.ediciones.model.dao.UsuariosDAO;
import cl.ediciones.util.Sha256;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Agregar_UsuariosAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut = request.getParameter("rut");
        String dv = request.getParameter("dv");
        String nombres = request.getParameter("nombres");
        String email = request.getParameter("email");
        String id_perfil = request.getParameter("id_perfil");
        String observacion = request.getParameter("observacion");

        String clave = "";
        for (int i = 0; i < 4; i++) {
            clave = clave + rut.charAt(i);
        }

        clave = Sha256.getStringMessageDigest(clave, Sha256.SHA256);

        Usuarios usuario = new Usuarios();

        usuario.setRut(Integer.parseInt(rut));
        usuario.setDv(dv);
        usuario.setNombres(nombres);
        usuario.setEmail(email);
        usuario.setId_perfil(Integer.parseInt(id_perfil));
        usuario.setObservacion(observacion);
        usuario.setClave(clave);

        UsuariosDAO cliDAO = new UsuariosDAO();
        cliDAO.create(usuario);

        request.setAttribute("Usuario", usuario);

        return mapping.findForward("AgregarUsuarios");
    }
}
