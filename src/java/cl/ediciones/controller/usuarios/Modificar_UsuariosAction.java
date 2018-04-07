package cl.ediciones.controller.usuarios;

import cl.ediciones.model.Usuarios;
import cl.ediciones.model.dao.UsuariosDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Modificar_UsuariosAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String rut = request.getParameter("rut");
        String dv = request.getParameter("dv");
        String nombres = request.getParameter("nombres");
        String email = request.getParameter("email");
        String id_perfil = request.getParameter("id_perfil");
        String observacion = request.getParameter("observacion");
        
        Usuarios usuario = new Usuarios();
        
        usuario.setRut(Integer.parseInt(rut));
        usuario.setDv(dv);
        usuario.setNombres(nombres);
        usuario.setEmail(email);
        usuario.setId_perfil(Integer.parseInt(id_perfil));
        usuario.setObservacion(observacion);
        
        UsuariosDAO usuDAO = new UsuariosDAO();
        usuDAO.update(usuario);
        
        return mapping.findForward("ModificarUsuarios");
    }
}
