package cl.ediciones.controller.pagos.cuota;

import cl.ediciones.model.Cuota;
import cl.ediciones.model.Factura;
import cl.ediciones.model.dao.CuotaDAO;
import cl.ediciones.model.dao.FacturaDAO;
import cl.ediciones.util.FechaStr;
import java.sql.SQLException;

public class CuotaDWR {
    
    public String agregar_Cuota(int monto, int rut_cliente, String fecha_vencimiento, int id_factura) throws SQLException{
        Cuota cuota = new Cuota();
        cuota.setMonto(monto);
        cuota.setRut_cliente(rut_cliente);
        cuota.setFecha_vencimiento(FechaStr.stringToDate(fecha_vencimiento));
        
        FacturaDAO facDAO = new FacturaDAO();
        Factura factura = facDAO.retrive(id_factura);
        cuota.setFactura(factura);
        
        CuotaDAO cuoDAO = new CuotaDAO();
        String ok = cuoDAO.create_Cuota(cuota);
        
        if(ok.equals("ok")){
            int saldo_pago = factura.getSaldo_pago() - cuota.getMonto();
            facDAO.actualiza_Saldo_Pago(id_factura, saldo_pago);
        }
        
        return ok;
    }
    
    public String cancelar_Cuota(int id_cuota, int monto_cancelado, String fecha_cancela) throws SQLException {
        Cuota cuota = new Cuota();
        cuota.setId_cuota(id_cuota);
        cuota.setMonto_cancelado(monto_cancelado);
        cuota.setFecha_cancela(FechaStr.StringToDate(fecha_cancela));
        
        CuotaDAO cheDAO = new CuotaDAO();
        String ok = cheDAO.cancela_Cuota(cuota);
        return ok;

    }

    public String revertir_cancelar_Cuota(int id_cuota) throws SQLException {
        CuotaDAO cheDAO = new CuotaDAO();
        String ok = cheDAO.revertir_cancela_Cuota(id_cuota);
        return ok;
    }
}
