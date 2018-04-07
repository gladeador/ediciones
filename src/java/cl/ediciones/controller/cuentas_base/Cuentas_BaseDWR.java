package cl.ediciones.controller.cuentas_base;

import cl.ediciones.model.Cuentas_Base;
import cl.ediciones.model.dao.Cuentas_BaseDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cuentas_BaseDWR {

    public ArrayList buscar_Todas_Cuentas_final_N() throws SQLException {
        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        ArrayList lista = cueDAO.traerTodos_Cuentas_Base_Final_N();
        return lista;
    }

    public ArrayList agregar_Cuentas_Padre(String num_cuenta, String descripcion, String tipo, String cuentas) throws SQLException {
        Cuentas_Base cuentas_base = new Cuentas_Base();

        cuentas_base.setNum_cuenta(num_cuenta + "-00-00");
        cuentas_base.setDescripcion(descripcion);
        cuentas_base.setTipo(tipo);
        cuentas_base.setCuentas(cuentas);

        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        cueDAO.create_Cuenta_Padre(cuentas_base);
        ArrayList lista = cueDAO.traerTodos_Cuentas_Base_Padre();
        return lista;
    }

    public ArrayList agregar_Cuentas_Hijo(String cuenta_padre, String num_cuenta, String descripcion, String tipo, String cuentas) throws SQLException {
        String cuenta_aux = "";
        String finalS = "N";
        for (int i = 0; i < cuenta_padre.length(); i++) {
            String aux = "";
            if (i > 0) {
                aux = aux + cuenta_padre.charAt(i - 1);
            }
            aux = aux + cuenta_padre.charAt(i);
            if (aux.equals("00")) {
                cuenta_aux = cuenta_aux + num_cuenta;
                i = cuenta_padre.length();
            } else if (i > 0) {
                cuenta_aux = cuenta_aux + cuenta_padre.charAt(i - 1);
            }
        }
        if (cuenta_aux.length() < 8) {
            cuenta_aux = cuenta_aux + "-00";
        } else {
            finalS = "S";
        }

        Cuentas_Base cuentas_base = new Cuentas_Base();

        cuentas_base.setCuenta_padre(cuenta_padre);
        cuentas_base.setNum_cuenta(cuenta_aux);
        cuentas_base.setDescripcion(descripcion);
        cuentas_base.setTipo(tipo);
        cuentas_base.setCuentas(cuentas);
        cuentas_base.setFinalS(finalS);

        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        cueDAO.create_Cuenta_Hijo(cuentas_base);
        ArrayList lista = cueDAO.traerTodos_Cuentas_Base_Hijo();
        return lista;
    }

    public ArrayList traerTodos_Padres() throws SQLException {
        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        ArrayList lista = cueDAO.traerTodos_Cuentas_Base_Padre();
        return lista;
    }

    public ArrayList traerTodos_Hijos() throws SQLException {
        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        ArrayList lista = cueDAO.traerTodos_Cuentas_Base_Hijo();
        return lista;
    }

    public Cuentas_Base valida_Cuenta_Padre(String num_cuenta) throws SQLException {
        num_cuenta = num_cuenta + "-00-00";

        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        Cuentas_Base cuentas_base = cueDAO.retrieve(num_cuenta);
        return cuentas_base;
    }

    public Cuentas_Base valida_Cuenta_Hijo(String cuenta_padre, String num_cuenta) throws SQLException {
        System.out.println("cuenta_padre: "+cuenta_padre);
        System.out.println("num_cuenta: "+num_cuenta);
        String cuenta_aux = "";
        for (int i = 0; i < cuenta_padre.length(); i++) {
            String aux = "";
            if (i > 0) {
                aux = aux + cuenta_padre.charAt(i - 1);
            }
            aux = aux + cuenta_padre.charAt(i);
            if (aux.equals("00")) {
                cuenta_aux = cuenta_aux + num_cuenta;
                i = cuenta_padre.length();
            } else if (i > 0) {
                cuenta_aux = cuenta_aux + cuenta_padre.charAt(i - 1);
            }
        }
        if (cuenta_aux.length() < 8) {
            cuenta_aux = cuenta_aux + "-00";
        }

        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        Cuentas_Base cuentas_base = cueDAO.retrieve(cuenta_aux);
        return cuentas_base;
    }
    
    public void delete_Cuentas_Base(String num_cuenta) throws SQLException {
        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
        cueDAO.delete_Cuentas(num_cuenta);
    }

}
