package cl.ediciones.controller.nota_credito;

import cl.ediciones.model.NotaCredito;
import cl.ediciones.model.dao.NotaCreditoDAO;
import java.sql.SQLException;

public class NotaCreditoDWR {

    public int guardar_NotaCredito(String num_notacredito) throws SQLException {
        NotaCreditoDAO notacreditoDAO = new NotaCreditoDAO();
        int id_notacredito = notacreditoDAO.create_NotaCredito(Integer.parseInt(num_notacredito));
        return id_notacredito;
    }

    public String validar_Num_NotaCredito(String num_notacredito) throws SQLException {
        NotaCreditoDAO notacreditoDAO = new NotaCreditoDAO();
        String ok = notacreditoDAO.valida_num_notacredito(Integer.parseInt(num_notacredito));
        return ok;
    }

    public void agrega_Cliente(String rut, String id_notacredito) throws SQLException {
        NotaCreditoDAO notacreditoDAO = new NotaCreditoDAO();
        notacreditoDAO.agrega_cliente_a_NotaCredito(Integer.parseInt(rut), Integer.parseInt(id_notacredito));
    }

    public void agregar_valores(String sub_total, String desc, String neto, String iva, String total, String id_notacredito) throws SQLException {
        NotaCreditoDAO notacreditoDAO = new NotaCreditoDAO();
        notacreditoDAO.agrega_valores_a_NotaCredito(Integer.parseInt(sub_total), Integer.parseInt(desc), Integer.parseInt(neto), Integer.parseInt(iva), Integer.parseInt(total), Integer.parseInt(id_notacredito));
    }

    public void graba_envio(String id_tipo_envio, String id_notacredito) throws SQLException {
        NotaCreditoDAO notacreditoDAO = new NotaCreditoDAO();
        notacreditoDAO.graba_Envio(Integer.parseInt(id_tipo_envio), Integer.parseInt(id_notacredito));
    }

    public NotaCredito buscar_notacredito(String id_notacredito) throws SQLException {
        NotaCreditoDAO notacreditoDao = new NotaCreditoDAO();
        NotaCredito notacredito = notacreditoDao.retrive(Integer.parseInt(id_notacredito));
        return notacredito;
    }

    public void modifica_fecha(String fecha_notacredito, String id_notacredito) throws SQLException {
        NotaCreditoDAO facDAO = new NotaCreditoDAO();
        facDAO.modifica_fecha_NotaCredito(fecha_notacredito, Integer.parseInt(id_notacredito));
    }
}
