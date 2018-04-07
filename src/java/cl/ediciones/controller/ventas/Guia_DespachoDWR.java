package cl.ediciones.controller.ventas;

import cl.ediciones.model.Guia_Despacho;
import cl.ediciones.model.dao.Guia_DespachoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Guia_DespachoDWR {

    public Guia_Despacho buscar_guia(String id_guia_despacho) throws SQLException {
        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        Guia_Despacho guia_despacho = guiaDAO.retrive_Guia_Despacho(Integer.parseInt(id_guia_despacho));
        return guia_despacho;
    }

    public int guardar_Guia(String num_guia, String fecha_guia) throws SQLException {
        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        int id_guia_despacho = guiaDAO.create_Guia_Despacho(Integer.parseInt(num_guia), fecha_guia);
        return id_guia_despacho;
    }

    public String validar_Num_Guia(String num_guia) throws SQLException {
        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        String ok = guiaDAO.valida_num_guia(Integer.parseInt(num_guia));
        return ok;
    }

    public void agrega_Cliente(String rut, String id_guia_despacho) throws SQLException {
        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        guiaDAO.agrega_cliente_a_Guia(Integer.parseInt(rut), Integer.parseInt(id_guia_despacho));
    }

    public void modifica_fecha(String fecha_guia, String id_guia_despacho) throws SQLException {
        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        guiaDAO.modifica_fecha_Guia(fecha_guia, Integer.parseInt(id_guia_despacho));
    }

    public void agregar_valores(String sub_total, String desc, String neto, String iva, String total, String id_guia_despacho) throws SQLException {
        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        guiaDAO.update_valores(Integer.parseInt(sub_total), Integer.parseInt(desc), Integer.parseInt(neto), Integer.parseInt(iva), Integer.parseInt(total), Integer.parseInt(id_guia_despacho));
    }
    
    public ArrayList buscar_para_Facturacion() throws SQLException {
        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        ArrayList lista = guiaDAO.traerTodos_Guia_Despacho_para_Facturacion();
        return lista;
    }
    
    public void  emitir_Guia_Despacho(String id_guia_despacho) throws SQLException {
        Guia_DespachoDAO guiaDAO = new Guia_DespachoDAO();
        guiaDAO.emitir_Guia_Despacho(Integer.parseInt(id_guia_despacho));
    }
}
