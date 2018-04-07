package cl.ediciones.model.dao;

import cl.ediciones.model.Fono_Contacto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fono_ContactoDAO {

    public Fono_ContactoDAO() {
    }

    public ArrayList retrieve_por_Rut_Clientes(int rut) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from fono_clientes where rut = ?");
            ps.setInt(1, rut);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Fono_Contacto fono = new Fono_Contacto();
                fono.setRut(tabla.getInt("rut"));
                fono.setFono(tabla.getInt("fono"));
                lista.add(fono);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList retrieve_por_Rut_Proveedores(int rut) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from fono_proveedores where rut = ?");
            ps.setInt(1, rut);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Fono_Contacto fono = new Fono_Contacto();
                fono.setRut(tabla.getInt("rut"));
                fono.setFono(tabla.getInt("fono"));
                lista.add(fono);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void createFonoClientes(Fono_Contacto fono_clientes) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into fono_clientes (rut, fono) values (?, ?)");
            ps.setInt(1, fono_clientes.getRut());
            ps.setInt(2, fono_clientes.getFono());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
    public void createFonoProveedores(Fono_Contacto fono_proveedores) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into fono_proveedores (rut, fono) values (?, ?)");
            ps.setInt(1, fono_proveedores.getRut());
            ps.setInt(2, fono_proveedores.getFono());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Fono_Contacto fono, int new_fono) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update fono set fono = ? where rut = ? and fono = ?");
            ps.setInt(1, new_fono);
            ps.setInt(2, fono.getRut());
            ps.setInt(3, fono.getFono());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(Fono_Contacto fono) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete fono where rut = ? and fono = ?");
            ps.setInt(1, fono.getRut());
            ps.setInt(2, fono.getFono());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList retrieveCliente_por_Rut(int rut) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from fono_clientes where rut = ?");
            ps.setInt(1, rut);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Fono_Contacto fono = new Fono_Contacto();
                fono.setRut(tabla.getInt("rut"));
                fono.setFono(tabla.getInt("fono"));
                //fono.setId(tabla.getInt("id"));
                lista.add(fono);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList retrieveProveedor_por_Rut(int rut) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from fono_proveedores where rut = ?");
            ps.setInt(1, rut);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Fono_Contacto fono = new Fono_Contacto();
                fono.setRut(tabla.getInt("rut"));
                fono.setFono(tabla.getInt("fono"));
                fono.setId(tabla.getInt("id"));
                lista.add(fono);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public void updateFonoProveedores(Fono_Contacto fono, int id, int new_fono) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update fono_proveedores set fono = ? where id = ?");
            ps.setInt(1, new_fono);
          //  ps.setInt(2, fono.getRut());
            ps.setInt(2, id);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void updateFonoClientes(Fono_Contacto fono, int new_fono) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update fono_clientes set fono = ? where rut = ? and fono = ?");
            ps.setInt(1, new_fono);
            ps.setInt(2, fono.getRut());
            ps.setInt(3, fono.getFono());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void borraFono(int rut, int fono) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from fono_proveedores where rut = ? and fono=? ");
            ps.setInt(1, rut);
            ps.setInt(2, fono);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete_malo(Fono_Contacto fono) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete fono where rut = ? and id = ?");
            ps.setInt(1, fono.getRut());
            ps.setInt(2, fono.getId());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
}
