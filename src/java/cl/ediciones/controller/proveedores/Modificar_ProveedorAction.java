package cl.ediciones.controller.proveedores;

import cl.ediciones.model.Fono_Contacto;
import cl.ediciones.model.Proveedores;
import cl.ediciones.model.dao.Fono_ContactoDAO;
import cl.ediciones.model.dao.ProveedoresDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Modificar_ProveedorAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut = request.getParameter("rut");
        String dv = request.getParameter("dv");
        String nombre = request.getParameter("nombre");
        String giro = request.getParameter("giro");
        String direccion_particular = request.getParameter("direccion_particular");
        String email = request.getParameter("email");
        String nombre_contacto = request.getParameter("nombre_contacto");
        String ciudad = request.getParameter("ciudad");
        String pais = request.getParameter("pais");
        String id_nacionalidad = request.getParameter("id_nacionalidad");
        String desc_corta = request.getParameter("dec_corta");
        String estado = request.getParameter("estado");
        String tipo_proveedor = request.getParameter("tipo_proveedor");

        Proveedores proveedor = new Proveedores();

        proveedor.setRut(Integer.parseInt(rut));
        proveedor.setDv(dv);
        proveedor.setNombre(nombre);
        proveedor.setGiro(giro);
        proveedor.setDireccion_particular(direccion_particular);
        proveedor.setEmail(email);
        proveedor.setNombre_contacto(nombre_contacto);
        proveedor.setCiudad(ciudad);
        proveedor.setPais(pais);
        proveedor.setId_nacionalidad(Integer.parseInt(id_nacionalidad));
        proveedor.setDesc_corta(desc_corta);
        proveedor.setEstado(estado);
        proveedor.setTipo_proveedor(Integer.parseInt(tipo_proveedor));

        ProveedoresDAO proveDAO = new ProveedoresDAO();
        proveDAO.update(proveedor);

        Fono_ContactoDAO fonDAO = new Fono_ContactoDAO();
        Fono_Contacto fono = new Fono_Contacto();
        fono.setRut(Integer.parseInt(rut));
/*
        String telefono_0 = request.getParameter("telefono_0");
        int idnuevo = 0;
        try {
            idnuevo = Integer.parseInt(request.getParameter("cajVal_0"));
        } catch (Exception e) {
        }

        if (telefono_0 == null || telefono_0.equals("")) {
            fono.setFono(Integer.parseInt(telefono_0));
            fono.setId(idnuevo);
            fonDAO.delete(fono);
        } else if (!telefono_0.equals("") && idnuevo == 0) {
            fono.setFono(Integer.parseInt(telefono_0));
            fonDAO.createFonoProveedores(fono);
        } else {
            fonDAO.updateFonoProveedores(fono, Integer.parseInt(telefono_0));
        }*/

        int numDos = 0;
        try {
            numDos = Integer.parseInt(request.getParameter("num"));
        } catch (Exception e) {
        }

        for (int i = 0; i < numDos; i++) {

            String telefono = request.getParameter("telefono_" + i );
            int idDos = 0;
            try {
                idDos = Integer.parseInt(request.getParameter("cajVal_" + i ));
            } catch (Exception e) {
            }

            if (telefono == null || telefono.equals("")) {
                fono.setFono(Integer.parseInt(telefono));
                fono.setId(idDos);
                fonDAO.delete(fono);
            } else if (!telefono.equals("") && idDos == 0) {
                fono.setFono(Integer.parseInt(telefono));
                fonDAO.createFonoProveedores(fono);
            } else {
                fonDAO.updateFonoProveedores(fono, idDos, Integer.parseInt(telefono));
            }
        }

        return mapping.findForward("ModificarProveedor");
    }
}
