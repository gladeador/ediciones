package cl.ediciones.controller.gastos;

import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Forma_Pago;
import cl.ediciones.model.Gastos;
import cl.ediciones.model.Proveedores;
import cl.ediciones.model.Tipo_Documento;
import cl.ediciones.model.dao.GastosDAO;
import cl.ediciones.util.FechaStr;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Agregar_GastosAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String num_boleta = request.getParameter("num_boleta");
        String fecha_gasto = request.getParameter("fecha_gasto");
        String neto_exento = request.getParameter("neto_exento");
        String neto_afecto = request.getParameter("neto_afecto");
        String iva_afecto = request.getParameter("iva_afecto");
        String total_afecto = request.getParameter("total_afecto");
        String rut_proveedor = request.getParameter("rut_proveedor");
        String id_tipo_documento = request.getParameter("id_tipo_documento");
        String num_cuenta = request.getParameter("num_cuenta");
        String id_forma_pago = request.getParameter("id_forma_pago");

        Gastos gastos = new Gastos();

        gastos.setNum_boleta(num_boleta);
        gastos.setFecha_gasto(FechaStr.stringToDate(fecha_gasto));
        gastos.setNeto_exento(Integer.parseInt(neto_exento));
        gastos.setNeto_afecto(Integer.parseInt(neto_afecto));
        gastos.setIva_afecto(Integer.parseInt(iva_afecto));
        gastos.setTotal_afecto(Integer.parseInt(total_afecto));

        Proveedores proveedores = new Proveedores();
        proveedores.setRut(Integer.parseInt(rut_proveedor));
        gastos.setProveedores(proveedores);
        
        Tipo_Documento tipo_documento = new Tipo_Documento();
        tipo_documento.setId_tipo_documento(id_tipo_documento);
        gastos.setTipo_documento(tipo_documento);

        Cuentas_Base cuentas = new Cuentas_Base();
        cuentas.setNum_cuenta(num_cuenta);
        gastos.setCuenta_base_neto(cuentas);
        
        Forma_Pago forma_pago = new Forma_Pago();
        forma_pago.setId_forma_pago(Integer.parseInt(id_forma_pago));
        gastos.setForma_pago(forma_pago);
        
        GastosDAO gasDAO = new GastosDAO();
        gasDAO.create(gastos);

        return mapping.findForward("AgregarGastos");
    }
}
