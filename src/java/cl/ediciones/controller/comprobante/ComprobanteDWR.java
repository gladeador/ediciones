package cl.ediciones.controller.comprobante;

import cl.ediciones.model.Cheque;
import cl.ediciones.model.Cheque_Pago;
import cl.ediciones.model.Comprobante;
import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.Cuota;
import cl.ediciones.model.Factura;
import cl.ediciones.model.Gastos;
import cl.ediciones.model.Letra;
import cl.ediciones.model.Proveedores;
import cl.ediciones.model.dao.ComprobanteDAO;
import cl.ediciones.util.FechaStr;
import java.sql.SQLException;

public class ComprobanteDWR {
    
    public void enviaComprobante_FacturaVenta(String num_comprobante, String fecha, String descripcion, int id_factura) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        comprobante.setDescripcion(descripcion);
        
        Factura factura = new Factura();
        factura.setId_factura(id_factura);
        comprobante.setFactura(factura);
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        comDAO.createComprobante_Factura_Venta(comprobante);
    }
     
    public void enviaComprobante_Gastos(String num_comprobante, String fecha, String descripcion, int id_gastos) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        comprobante.setDescripcion(descripcion);
        
        Gastos gastos = new Gastos();
        gastos.setId_gastos(id_gastos);
        comprobante.setGastos(gastos);
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        comDAO.createComprobante_Gastos(comprobante);
    }
     
    public void enviaComprobante_FacturaCompra(String num_comprobante, String fecha, String descripcion, int num_factura, int rut_proveedor) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        comprobante.setDescripcion(descripcion);
        comprobante.setNum_factura(num_factura);
        
        Proveedores proveedores = new Proveedores();
        proveedores.setRut(rut_proveedor);
        comprobante.setProveedor(proveedores);
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        comDAO.createComprobante_Factura_Compra(comprobante);
    }
     
    public void enviaComprobante_Cheque(String num_comprobante, String fecha, String descripcion, int id_cheque) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        comprobante.setDescripcion(descripcion);
        
        Cheque cheque = new Cheque();
        cheque.setId_cheque(id_cheque);
        comprobante.setCheque(cheque);
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        comDAO.createComprobante_Cheque(comprobante);
    }
     
    public void enviaComprobante_Cheque_Pago(String num_comprobante, String fecha, String descripcion, int id_cheque_pago, String debe_haber) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        comprobante.setDescripcion(descripcion);
        if(debe_haber == null){
            debe_haber = "";
        }
        comprobante.setDebe_haber(debe_haber);
        
        Cheque_Pago cheque = new Cheque_Pago();
        cheque.setId_cheque_pago(id_cheque_pago);
        comprobante.setCheque_pago(cheque);
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        comDAO.createComprobante_Cheque_Pago(comprobante);
    }
     
    public void enviaComprobante_Cuota(String num_comprobante, String fecha, String descripcion, int id_cuota) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        comprobante.setDescripcion(descripcion);
        
        Cuota cuota = new Cuota();
        cuota.setId_cuota(id_cuota);
        comprobante.setCuota(cuota);
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        comDAO.createComprobante_Cuota(comprobante);
    }
     
    public void enviaComprobante_Letra(String num_comprobante, String fecha, String descripcion, int id_letra) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        comprobante.setDescripcion(descripcion);
        
        Letra letra = new Letra();
        letra.setId_letra(id_letra);
        comprobante.setLetra(letra);
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        comDAO.createComprobante_Letra(comprobante);
    }
     
    public void agregaComprobanteManual(String num_comprobante, String fecha, String descripcion, String num_cuenta, int monto, String debe_haber, String observacion) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        comprobante.setDescripcion(descripcion);
        comprobante.setMonto(monto);
        comprobante.setDebe_haber(debe_haber);
        comprobante.setDescripcion_2(observacion);
        
        Cuentas_Base cuenta_base = new Cuentas_Base();
        cuenta_base.setNum_cuenta(num_cuenta);
        comprobante.setCuenta_base(cuenta_base);
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        comDAO.createComprobante_Manual(comprobante);
    }
     
    public String recupera_Descripcion_Comprobante(String num_comprobante, String fecha) throws SQLException{
        Comprobante comprobante = new Comprobante();
        comprobante.setNum_comprobante(num_comprobante);
        comprobante.setFecha_comprobante(FechaStr.stringToDate(fecha));
        
        ComprobanteDAO comDAO = new ComprobanteDAO();
        String descripcion = comDAO.retrive_Detalle_Comprobante(comprobante);
        return descripcion;
    }
     
}
