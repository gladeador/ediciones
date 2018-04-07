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

public class Agregar_ProveedorAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id_nacionalidad = 0;
        int comuna=0;


        String rut = request.getParameter("rut");
        String dv = request.getParameter("dv");
        String nombre = request.getParameter("nombre");
        String giro = request.getParameter("giro");
        String direccion_particular = request.getParameter("direccion_particular");
        String email = request.getParameter("email");
        String nombre_contacto = request.getParameter("nombre_contacto");
        String ciudad = request.getParameter("ciudad");
        String pais = request.getParameter("pais");
        String region = request.getParameter("id_region");
     //   String comuna = request.getParameter("id_comuna");
        String desc_corta = request.getParameter("desc_corta");
        String tipo_proveedor = request.getParameter("tipo_proveedor");

        try {
            comuna = Integer.parseInt(request.getParameter("id_comuna"));
        } catch (Exception e) {
        }

        try {
            id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
        } catch (Exception e) {
        }

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
        proveedor.setId_region(Integer.parseInt(region));
        proveedor.setId_comuna(comuna);
        proveedor.setDesc_corta(desc_corta);
        proveedor.setId_nacionalidad(id_nacionalidad);
        proveedor.setTipo_proveedor(Integer.parseInt(tipo_proveedor));

        ProveedoresDAO proveeDAO = new ProveedoresDAO();
        String ok = proveeDAO.create(proveedor);

        if (ok != null) {
            int num = 0;
            Fono_ContactoDAO fonDAO = new Fono_ContactoDAO();
            Fono_Contacto Fono_Contacto = new Fono_Contacto();
            Fono_Contacto.setRut(Integer.parseInt(rut));
            try {
                 num = Integer.parseInt(request.getParameter("num"));
            } catch (Exception e) {
            }
           // System.out.println(num);
            while (num > 0) {
                int telefono = 0;
                telefono = Integer.parseInt(request.getParameter("telefono_" + num));
                Fono_Contacto.setFono(telefono);
                fonDAO.createFonoProveedores(Fono_Contacto);
                num = num - 1;
            }

        }
        request.setAttribute("Proveedor", proveedor);
        return mapping.findForward("AgregarProveedor");
    }
}
