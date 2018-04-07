package cl.ediciones.controller.stock;

import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Stock_Producto;
import cl.ediciones.model.dao.Stock_ProductoDAO;
import cl.ediciones.util.FechaStr;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Modificar_Stock_ProductoAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id_stock = request.getParameter("id_stock");
        String stock = request.getParameter("stock");
        String observaciones = request.getParameter("observaciones");
        String costo_producto = request.getParameter("costo_producto");
        String tipo_de_cambio = request.getParameter("tipo_de_cambio");
        String porsentaje_gastos = request.getParameter("porsentaje_gastos");
        String costo_gastos = request.getParameter("costo_gastos");
        String fecha_ingreso = request.getParameter("fecha_ingreso");
        String num_guia_despacho = request.getParameter("num_guia_despacho");
        String num_factura = request.getParameter("num_factura");
        String neto = request.getParameter("neto");
        String iva = request.getParameter("iva");
        String total = request.getParameter("total");
        String num_cuenta = request.getParameter("num_cuenta");

        Stock_Producto stock_pro = new Stock_Producto();

        stock_pro.setId_stock(Integer.parseInt(id_stock));
        stock_pro.setStock(Integer.parseInt(stock));
        stock_pro.setCosto_producto(Float.parseFloat(costo_producto));
        stock_pro.setTipo_de_cambio(Integer.parseInt(tipo_de_cambio));
        stock_pro.setPorsentaje_gastos(Integer.parseInt(porsentaje_gastos));
        stock_pro.setCosto_gastos(Float.parseFloat(costo_gastos));
        stock_pro.setFecha_ingreso(FechaStr.stringToDate(fecha_ingreso));
        stock_pro.setNum_guia_despacho(Integer.parseInt(num_guia_despacho));
        stock_pro.setNum_factura(Integer.parseInt(num_factura));
        stock_pro.setObservaciones(observaciones);
        stock_pro.setNeto(Float.parseFloat(neto));
        stock_pro.setIva(Float.parseFloat(iva));
        stock_pro.setTotal(Float.parseFloat(total));

        Cuentas_Base cuentas = new Cuentas_Base();
        cuentas.setNum_cuenta(num_cuenta);
        stock_pro.setCuentas_base(cuentas);
        
        Stock_ProductoDAO stoDAO = new Stock_ProductoDAO();
        stoDAO.update(stock_pro);


        return mapping.findForward("ModificarStockProducto");
    }
}
