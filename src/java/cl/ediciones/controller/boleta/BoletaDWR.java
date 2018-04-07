package cl.ediciones.controller.boleta;

import cl.ediciones.model.Boleta;
import cl.ediciones.model.dao.BoletaDAO;
import java.sql.SQLException;

public class BoletaDWR {

    public int guardar_Boleta(String num_boleta) throws SQLException {
        BoletaDAO boletaDAO = new BoletaDAO();
        int id_boleta = boletaDAO.create_Boleta(Integer.parseInt(num_boleta));
        return id_boleta;
    }

    public String validar_Num_Boleta(String num_boleta) throws SQLException {
        BoletaDAO boletaDAO = new BoletaDAO();
        String ok = boletaDAO.valida_num_boleta(Integer.parseInt(num_boleta));
        return ok;
    }

    public void agrega_Cliente(String rut, String id_boleta) throws SQLException {
        BoletaDAO boletaDAO = new BoletaDAO();
        boletaDAO.agrega_cliente_a_Boleta(Integer.parseInt(rut), Integer.parseInt(id_boleta));
    }

    public void agregar_valores(String sub_total, String desc, String neto, String iva, String total, String id_boleta) throws SQLException {
        BoletaDAO boletaDAO = new BoletaDAO();
        boletaDAO.agrega_valores_a_Boleta(Integer.parseInt(sub_total), Integer.parseInt(desc), Integer.parseInt(neto), Integer.parseInt(iva), Integer.parseInt(total), Integer.parseInt(id_boleta));
    }

    public void graba_envio(String id_tipo_envio, String id_boleta) throws SQLException {
        BoletaDAO boletaDAO = new BoletaDAO();
        boletaDAO.graba_Envio(Integer.parseInt(id_tipo_envio), Integer.parseInt(id_boleta));

    }
   public Boleta buscar_boleta(String id_boleta) throws SQLException {
        BoletaDAO boletaDao = new BoletaDAO();
        Boleta boleta = boletaDao.retrive(Integer.parseInt(id_boleta));
        return boleta;
    }
 public void modifica_fecha(String fecha_boleta, String id_boleta) throws SQLException {
        BoletaDAO bolDAO = new BoletaDAO();
        bolDAO.modifica_fecha_Boleta(fecha_boleta, Integer.parseInt(id_boleta));
    }

    public void  emitir_Boleta(String id_guia_despacho) throws SQLException {
        BoletaDAO guiaDAO = new BoletaDAO();
        guiaDAO.emitir_Boleta(Integer.parseInt(id_guia_despacho));
    }
   
}
