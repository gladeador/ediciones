package cl.ediciones.controller.clientes;

import cl.ediciones.model.Comuna;
import cl.ediciones.model.Clientes;
import cl.ediciones.model.Fono_Contacto;
import cl.ediciones.model.dao.ClientesDAO;
import cl.ediciones.model.dao.Fono_ContactoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Agregar_ClientesAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String rut = request.getParameter("rut");
        String dv = request.getParameter("dv");
        String nombre = request.getParameter("nombre");
        String giro = request.getParameter("giro");
        String direccion_particular = request.getParameter("direccion_particular");
        String direccion_facturacion = request.getParameter("direccion_facturacion");
        String email = request.getParameter("email");
        String nombre_contacto = request.getParameter("nombre_contacto");
        String rut_facturacion = request.getParameter("rut_facturacion");
        String dv_facturacion = request.getParameter("dv_facturacion");
        String id_comuna = request.getParameter("id_comuna");

        Clientes cliente = new Clientes();

        cliente.setRut(Integer.parseInt(rut));
        cliente.setDv(dv);
        cliente.setNombre(nombre);
        cliente.setGiro(giro);
        cliente.setDireccion_particular(direccion_particular);
        cliente.setEmail(email);
        if (direccion_facturacion.equals("")) {
            cliente.setDireccion_facturacion(direccion_particular);
        } else {
            cliente.setDireccion_facturacion(direccion_facturacion);
        }
        cliente.setEmail(email);
        cliente.setNombre_contacto(nombre_contacto);
        if (rut_facturacion.equals("")) {
            cliente.setRut_facturacion(Integer.parseInt(rut));
            cliente.setDv_facturacion(dv);
        } else {
            cliente.setRut_facturacion(Integer.parseInt(rut_facturacion));
            cliente.setDv_facturacion(dv_facturacion);
        }
        
        Comuna comuna = new Comuna();
        comuna.setId_comuna(Integer.parseInt(id_comuna));
        cliente.setComuna(comuna);

        ClientesDAO cliDAO = new ClientesDAO();
        String ok = cliDAO.create(cliente);

        if (ok != null) {

            int aux = 1;
            while (aux <= 4) {
                Fono_ContactoDAO fonDAO = new Fono_ContactoDAO();
                Fono_Contacto fono = new Fono_Contacto();
                fono.setRut(Integer.parseInt(rut));
                String telefono = request.getParameter("telefono_" + aux);
                if (!(telefono.equals(""))) {
                    fono.setFono(Integer.parseInt(telefono));
                    fonDAO.createFonoClientes(fono);
                }
                aux++;
            }

        }

        return mapping.findForward("AgregarClientes");
    }
}
