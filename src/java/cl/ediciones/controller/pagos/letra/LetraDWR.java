package cl.ediciones.controller.pagos.letra;

import cl.ediciones.model.Letra;
import cl.ediciones.model.Factura;
import cl.ediciones.model.Protesto;
import cl.ediciones.model.dao.LetraDAO;
import cl.ediciones.model.dao.FacturaDAO;
import cl.ediciones.model.dao.ProtestoDAO;
import cl.ediciones.util.FechaStr;
import java.sql.SQLException;

public class LetraDWR {

    public String agregar_Letra(int num_letra, int monto, int rut_cliente, String fecha_recepcion_documento, String fecha_vencimiento, int id_factura) throws SQLException {
        Letra letra = new Letra();
        letra.setNum_letra(num_letra);
        letra.setMonto(monto);
        letra.setRut_cliente(rut_cliente);
        letra.setFecha_recepcion_documento(FechaStr.StringToDate(fecha_recepcion_documento));
        letra.setFecha_vencimiento(FechaStr.StringToDate(fecha_vencimiento));

        FacturaDAO facDAO = new FacturaDAO();
        Factura factura = facDAO.retrive(id_factura);
        letra.setFactura(factura);

        LetraDAO letDAO = new LetraDAO();
        String ok = letDAO.create_Letra(letra);
        
        if(ok.equals("ok")){
            int saldo_pago = factura.getSaldo_pago() - letra.getMonto();
            facDAO.actualiza_Saldo_Pago(id_factura, saldo_pago);
        }
        
        return ok;

    }
    
    public String cancelar_Letra(int id_letra, int monto_cancelado, String fecha_cancela) throws SQLException {
        Letra letra = new Letra();
        letra.setId_letra(id_letra);
        letra.setMonto_cancelado(monto_cancelado);
        letra.setFecha_cancela(FechaStr.StringToDate(fecha_cancela));
        
        LetraDAO letDAO = new LetraDAO();
        String ok = letDAO.cancela_Letra(letra);
        
        if (ok.equals("ok")) {
            ProtestoDAO proDAO = new ProtestoDAO();
            Protesto protesto = proDAO.retrive_Protesto_por_Letra(id_letra);
            if (protesto != null) {
                proDAO.paga_Protesto(protesto.getId_protesto());
            }
        }
        
        return ok;

    }

    public String revertir_cancelar_Letra(int id_letra) throws SQLException {
        LetraDAO letDAO = new LetraDAO();
        String ok = letDAO.revertir_cancela_Letra(id_letra);
        
        if (ok.equals("ok")) {
            ProtestoDAO proDAO = new ProtestoDAO();
            Protesto protesto = proDAO.retrive_Protesto_por_Letra(id_letra);
            if (protesto != null) {
                proDAO.revertir_paga_Protesto(protesto.getId_protesto());
                letDAO.protesta_Letra(id_letra);
            }
        }
        
        return ok;
    }
}
