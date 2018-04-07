package cl.ediciones.controller.pagos.cheque;

import cl.ediciones.model.Banco;
import cl.ediciones.model.Cheque_Pago;
import cl.ediciones.model.Proveedores;
import cl.ediciones.model.dao.BancoDAO;
import cl.ediciones.model.dao.Cheque_PagoDAO;
import cl.ediciones.model.dao.ProveedoresDAO;
import cl.ediciones.util.FechaStr;
import java.sql.SQLException;

public class Cheque_PagoDWR {

    public String agregar_Cheque_Pago(int num_cheque_pago, int id_banco, int monto, int rut_proveedor, String fecha_recepcion_documento, String fecha_vencimiento, int num_factura_compra) throws SQLException {
        Cheque_Pago cheque = new Cheque_Pago();
        cheque.setNum_cheque_pago(num_cheque_pago);
        cheque.setMonto(monto);
        cheque.setNum_factura_compra(num_factura_compra);
        cheque.setFecha_recepcion_documento(FechaStr.StringToDate(fecha_recepcion_documento));
        cheque.setFecha_vencimiento(FechaStr.StringToDate(fecha_vencimiento));

        ProveedoresDAO proDAO = new ProveedoresDAO();
        Proveedores proveedores = proDAO.retrieve(rut_proveedor);
        cheque.setProveedores(proveedores);
        
        BancoDAO banDAO = new BancoDAO();
        Banco banco = banDAO.retrive_Banco(id_banco);
        cheque.setBanco(banco);

        Cheque_PagoDAO cheDAO = new Cheque_PagoDAO();
        String ok = cheDAO.create_Cheque_Pago(cheque);

        return ok;

    }

    public String agregar_Cheque_Pago_Gastos(int num_cheque_pago, int id_banco, int monto, int rut_proveedor, String fecha_recepcion_documento, String fecha_vencimiento, int id_gastos) throws SQLException {
        Cheque_Pago cheque = new Cheque_Pago();
        cheque.setNum_cheque_pago(num_cheque_pago);
        cheque.setMonto(monto);
        cheque.setId_gastos(id_gastos);
        cheque.setFecha_recepcion_documento(FechaStr.StringToDate(fecha_recepcion_documento));
        cheque.setFecha_vencimiento(FechaStr.StringToDate(fecha_vencimiento));

        ProveedoresDAO proDAO = new ProveedoresDAO();
        Proveedores proveedores = proDAO.retrieve(rut_proveedor);
        cheque.setProveedores(proveedores);
        
        BancoDAO banDAO = new BancoDAO();
        Banco banco = banDAO.retrive_Banco(id_banco);
        cheque.setBanco(banco);

        Cheque_PagoDAO cheDAO = new Cheque_PagoDAO();
        String ok = cheDAO.create_Cheque_Pago_Gastos(cheque);

        return ok;

    }

    public String cancelar_Cheque_Pago(int id_cheque_pago, int monto_cancelado, String fecha_cancela) throws SQLException {
        Cheque_Pago cheque = new Cheque_Pago();
        cheque.setId_cheque_pago(id_cheque_pago);
        cheque.setMonto_cancelado(monto_cancelado);
        cheque.setFecha_cancela(FechaStr.StringToDate(fecha_cancela));

        Cheque_PagoDAO cheDAO = new Cheque_PagoDAO();
        String ok = cheDAO.cancela_Cheque_Pago(cheque);
        return ok;

    }

    public String revertir_cancelar_Cheque_Pago(int id_cheque_pago) throws SQLException {
        Cheque_PagoDAO cheDAO = new Cheque_PagoDAO();
        String ok = cheDAO.revertir_cancela_Cheque_Pago(id_cheque_pago);
        return ok;
    }
}
