package cl.ediciones.controller.facturacion;

import cl.ediciones.model.Factura;
import cl.ediciones.model.dao.FacturaDAO;
import java.sql.SQLException;

public class FacturaDWR {

    public int guardar_Factura(String num_factura, String fecha_factura) throws SQLException {
        FacturaDAO facturaDAO = new FacturaDAO();
        int id_factura = facturaDAO.create_Factura(Integer.parseInt(num_factura), fecha_factura);
        return id_factura;
    }

    public String validar_Num_Factura(String num_factura) throws SQLException {
        FacturaDAO facturaDAO = new FacturaDAO();
        String ok = facturaDAO.valida_num_factura(Integer.parseInt(num_factura));
        return ok;
    }

    public void agrega_Cliente(String rut, String id_factura) throws SQLException {
        FacturaDAO facturaDAO = new FacturaDAO();
        facturaDAO.agrega_cliente_a_Factura(Integer.parseInt(rut), Integer.parseInt(id_factura));
    }

    public void agregar_valores(String sub_total, String desc, String neto, String iva, String total, String id_factura) throws SQLException {
        FacturaDAO facturaDAO = new FacturaDAO();
        facturaDAO.agrega_valores_a_Factura(Integer.parseInt(sub_total), Integer.parseInt(desc), Integer.parseInt(neto), Integer.parseInt(iva), Integer.parseInt(total), Integer.parseInt(id_factura));
    }

    public void graba_envio(String id_tipo_envio, String id_factura) throws SQLException {
        FacturaDAO facturaDAO = new FacturaDAO();
        facturaDAO.graba_Envio(Integer.parseInt(id_tipo_envio), Integer.parseInt(id_factura));

    }

    public Factura buscar_factura(String id_factura) throws SQLException {
        FacturaDAO facturaDao = new FacturaDAO();
        Factura factura = facturaDao.retrive(Integer.parseInt(id_factura));
        return factura;
    }

    public void modifica_fecha(String fecha_factura, String id_factura) throws SQLException {
        FacturaDAO facDAO = new FacturaDAO();
        facDAO.modifica_fecha_Factura(fecha_factura, Integer.parseInt(id_factura));
    }

    public void modifica_forma_pago(String id_forma_pago, String id_factura) throws SQLException {
        FacturaDAO facDAO = new FacturaDAO();
        facDAO.update_forma_pago(Integer.parseInt(id_forma_pago), Integer.parseInt(id_factura));
    }
}
