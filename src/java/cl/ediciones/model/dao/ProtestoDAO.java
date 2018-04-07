package cl.ediciones.model.dao;

import cl.ediciones.model.Cheque;
import cl.ediciones.model.Clientes;
import cl.ediciones.model.Estado_Protesto;
import cl.ediciones.model.Letra;
import cl.ediciones.model.Protesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProtestoDAO {

    public Protesto retrive_Protesto_Cheque(int id_protesto) throws SQLException {
        Protesto protesto = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from protesto where id_protesto = ? and id_cheque is not null");
            ps.setInt(1, id_protesto);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                protesto = new Protesto();
                protesto.setId_protesto(tabla.getInt("id_protesto"));

                Estado_ProtestoDAO epDAO = new Estado_ProtestoDAO();
                Estado_Protesto estado = epDAO.retrive_Estado_Protesto(tabla.getString("estado"));
                protesto.setEstado_protesto(estado);

                ChequeDAO cheDAO = new ChequeDAO();
                Cheque cheque = cheDAO.retrive_Cheque(tabla.getInt("id_cheque"));
                protesto.setCheque(cheque);

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                protesto.setClientes(clientes);

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return protesto;
    }

    public Protesto retrive_Protesto_por_Cheque(int id_cheque) throws SQLException {
        Protesto protesto = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from protesto where id_cheque = ?");
            ps.setInt(1, id_cheque);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                protesto = new Protesto();
                protesto.setId_protesto(tabla.getInt("id_protesto"));

                Estado_ProtestoDAO epDAO = new Estado_ProtestoDAO();
                Estado_Protesto estado = epDAO.retrive_Estado_Protesto(tabla.getString("estado"));
                protesto.setEstado_protesto(estado);

                ChequeDAO cheDAO = new ChequeDAO();
                Cheque cheque = cheDAO.retrive_Cheque(tabla.getInt("id_cheque"));
                protesto.setCheque(cheque);

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                protesto.setClientes(clientes);

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return protesto;
    }

    public Protesto retrive_Protesto_Letra(int id_protesto) throws SQLException {
        Protesto protesto = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from protesto where id_protesto = ? and id_letra is not null");
            ps.setInt(1, id_protesto);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                protesto = new Protesto();
                protesto.setId_protesto(tabla.getInt("id_protesto"));

                Estado_ProtestoDAO epDAO = new Estado_ProtestoDAO();
                Estado_Protesto estado = epDAO.retrive_Estado_Protesto(tabla.getString("estado"));
                protesto.setEstado_protesto(estado);

                LetraDAO cheDAO = new LetraDAO();
                Letra letra = cheDAO.retrive_Letra(tabla.getInt("id_letra"));
                protesto.setLetra(letra);

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                protesto.setClientes(clientes);

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return protesto;
    }

    public Protesto retrive_Protesto_por_Letra(int id_letra) throws SQLException {
        Protesto protesto = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from protesto where id_letra = ?");
            ps.setInt(1, id_letra);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                protesto = new Protesto();
                protesto.setId_protesto(tabla.getInt("id_protesto"));

                Estado_ProtestoDAO epDAO = new Estado_ProtestoDAO();
                Estado_Protesto estado = epDAO.retrive_Estado_Protesto(tabla.getString("estado"));
                protesto.setEstado_protesto(estado);

                LetraDAO cheDAO = new LetraDAO();
                Letra letra = cheDAO.retrive_Letra(tabla.getInt("id_letra"));
                protesto.setLetra(letra);

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                protesto.setClientes(clientes);

            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return protesto;
    }

    public String createProtesto_Cheque(Protesto protesto) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into protesto (id_cheque, rut_cliente) values (?, ?)");
            ps.setInt(1, protesto.getCheque().getId_cheque());
            ps.setInt(2, protesto.getClientes().getRut());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String createProtesto_Letra(Protesto protesto) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into protesto (id_letra, rut_cliente) values (?, ?)");
            ps.setInt(1, protesto.getLetra().getId_letra());
            ps.setInt(2, protesto.getClientes().getRut());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void deleteProtesto(int id_protesto) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from protesto where id_protesto = ?");
            ps.setInt(1, id_protesto);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public String deleteProtesto_Cheque(int id_cheque) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from protesto where id_cheque = ?");
            ps.setInt(1, id_cheque);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String deleteProtesto_Letra(int id_letra) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from protesto where id_letra = ?");
            ps.setInt(1, id_letra);

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void paga_Protesto(int id_protesto) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update protesto set estado = 'PA' where id_protesto = ?");
            ps.setInt(1, id_protesto);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void revertir_paga_Protesto(int id_protesto) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update protesto set estado = 'PR' where id_protesto = ?");
            ps.setInt(1, id_protesto);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerProtesto_Cheque() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from protesto");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Protesto protesto = new Protesto();
                protesto.setId_protesto(tabla.getInt("id_protesto"));

                Estado_ProtestoDAO epDAO = new Estado_ProtestoDAO();
                Estado_Protesto estado = epDAO.retrive_Estado_Protesto(tabla.getString("estado"));
                protesto.setEstado_protesto(estado);

                ChequeDAO cheDAO = new ChequeDAO();
                Cheque cheque = cheDAO.retrive_Cheque(tabla.getInt("id_cheque"));
                protesto.setCheque(cheque);

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                protesto.setClientes(clientes);

                lista.add(protesto);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerProtesto_Letra() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from protesto");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Protesto protesto = new Protesto();
                protesto.setId_protesto(tabla.getInt("id_protesto"));

                Estado_ProtestoDAO epDAO = new Estado_ProtestoDAO();
                Estado_Protesto estado = epDAO.retrive_Estado_Protesto(tabla.getString("estado"));
                protesto.setEstado_protesto(estado);

                LetraDAO cheDAO = new LetraDAO();
                Letra letra = cheDAO.retrive_Letra(tabla.getInt("id_letra"));
                protesto.setLetra(letra);

                ClientesDAO cliDAO = new ClientesDAO();
                Clientes clientes = cliDAO.retrieve(tabla.getInt("rut_cliente"));
                protesto.setClientes(clientes);

                lista.add(protesto);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
}
