package cl.ediciones.controller.pagos.cheque;

import cl.ediciones.model.Banco;
import cl.ediciones.model.Cheque;
import cl.ediciones.model.Factura;
import cl.ediciones.model.Protesto;
import cl.ediciones.model.dao.BancoDAO;
import cl.ediciones.model.dao.ChequeDAO;
import cl.ediciones.model.dao.FacturaDAO;
import cl.ediciones.model.dao.ProtestoDAO;
import cl.ediciones.util.FechaStr;
import java.sql.SQLException;

public class ChequeDWR {
    
    public String agregar_Cheque(int num_cheque, int id_banco, int monto, int rut_cliente, String fecha_recepcion_documento, String fecha_vencimiento, int id_factura) throws SQLException {
        Cheque cheque = new Cheque();
        cheque.setNum_cheque(num_cheque);
        cheque.setMonto(monto);
        cheque.setRut_cliente(rut_cliente);
        cheque.setFecha_recepcion_documento(FechaStr.StringToDate(fecha_recepcion_documento));
        cheque.setFecha_vencimiento(FechaStr.StringToDate(fecha_vencimiento));
        
        BancoDAO banDAO = new BancoDAO();
        Banco banco = banDAO.retrive_Banco(id_banco);
        cheque.setBanco(banco);
        
        FacturaDAO facDAO = new FacturaDAO();
        Factura factura = facDAO.retrive(id_factura);
        cheque.setFactura(factura);
        
        ChequeDAO cheDAO = new ChequeDAO();
        String ok = cheDAO.create_Cheque(cheque);
        
        if (ok.equals("ok")) {
            int saldo_pago = factura.getSaldo_pago() - cheque.getMonto();
            facDAO.actualiza_Saldo_Pago(id_factura, saldo_pago);
        }
        
        return ok;
        
    }
    
    public String cancelar_Cheque(int id_cheque, int monto_cancelado, String fecha_cancela) throws SQLException {
        Cheque cheque = new Cheque();
        cheque.setId_cheque(id_cheque);
        cheque.setMonto_cancelado(monto_cancelado);
        cheque.setFecha_cancela(FechaStr.StringToDate(fecha_cancela));
        
        ChequeDAO cheDAO = new ChequeDAO();
        String ok = cheDAO.cancela_Cheque(cheque);
        
        if (ok.equals("ok")) {
            ProtestoDAO proDAO = new ProtestoDAO();
            Protesto protesto = proDAO.retrive_Protesto_por_Cheque(id_cheque);
            if (protesto != null) {
                proDAO.paga_Protesto(protesto.getId_protesto());
            }
        }
        
        return ok;
        
    }
    
    public String revertir_cancelar_Cheque(int id_cheque) throws SQLException {
        ChequeDAO cheDAO = new ChequeDAO();
        String ok = cheDAO.revertir_cancela_Cheque(id_cheque);
        
        if (ok.equals("ok")) {
            ProtestoDAO proDAO = new ProtestoDAO();
            Protesto protesto = proDAO.retrive_Protesto_por_Cheque(id_cheque);
            if (protesto != null) {
                proDAO.revertir_paga_Protesto(protesto.getId_protesto());
                cheDAO.protesta_Cheque(id_cheque);
            }
        }
        
        return ok;
    }
}
