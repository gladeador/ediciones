package cl.ediciones.controller.protesto;

import cl.ediciones.model.Cheque;
import cl.ediciones.model.Clientes;
import cl.ediciones.model.Letra;
import cl.ediciones.model.Protesto;
import cl.ediciones.model.dao.ChequeDAO;
import cl.ediciones.model.dao.LetraDAO;
import cl.ediciones.model.dao.ProtestoDAO;
import java.sql.SQLException;

public class ProtestoDWR {

    public String crea_Protesto_Cheque(int id_cheque, int rut_cliente) throws SQLException {
        Protesto protesto = new Protesto();

        Cheque cheque = new Cheque();
        cheque.setId_cheque(id_cheque);
        protesto.setCheque(cheque);

        Clientes clientes = new Clientes();
        clientes.setRut(rut_cliente);
        protesto.setClientes(clientes);

        ProtestoDAO proDAO = new ProtestoDAO();
        String ok = proDAO.createProtesto_Cheque(protesto);

        if (ok.equals("ok")) {
            ChequeDAO cheDAO = new ChequeDAO();
            cheDAO.protesta_Cheque(protesto.getCheque().getId_cheque());
        }

        return ok;
    }

    public String crea_Protesto_Letra(int id_letra, int rut_cliente) throws SQLException {
        Protesto protesto = new Protesto();

        Letra letra = new Letra();
        letra.setId_letra(id_letra);
        protesto.setLetra(letra);

        Clientes clientes = new Clientes();
        clientes.setRut(rut_cliente);
        protesto.setClientes(clientes);

        ProtestoDAO proDAO = new ProtestoDAO();
        String ok = proDAO.createProtesto_Letra(protesto);

        if (ok.equals("ok")) {
            LetraDAO letDAO = new LetraDAO();
            letDAO.protesta_Letra(protesto.getLetra().getId_letra());
        }

        return ok;
    }

    public String revertir_Protesto_Cheque(int id_cheque) throws SQLException {
        ProtestoDAO proDAO = new ProtestoDAO();
        String ok = proDAO.deleteProtesto_Cheque(id_cheque);

        if (ok.equals("ok")) {
            ChequeDAO cheDAO = new ChequeDAO();
            cheDAO.revertir_protesta_Cheque(id_cheque);
        }

        return ok;
    }

    public String revertir_Protesto_Letra(int id_letra) throws SQLException {
        ProtestoDAO proDAO = new ProtestoDAO();
        String ok = proDAO.deleteProtesto_Letra(id_letra);

        if (ok.equals("ok")) {
            LetraDAO letDAO = new LetraDAO();
            letDAO.revertir_protesta_Letra(id_letra);
        }

        return ok;
    }
}
