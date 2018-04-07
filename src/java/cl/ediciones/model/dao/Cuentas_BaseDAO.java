package cl.ediciones.model.dao;

import cl.ediciones.model.Cuentas_Base;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cuentas_BaseDAO {
 
    public Cuentas_BaseDAO() {
    }

    public Cuentas_Base retrieve(String cuenta) throws SQLException {
        Cuentas_Base cuentas_base = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuentas_base where num_cuenta = ?");
            ps.setString(1, cuenta);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cuentas_base = new Cuentas_Base();
                cuentas_base.setNum_cuenta(tabla.getString("num_cuenta"));
                cuentas_base.setDescripcion(tabla.getString("descripcion"));
                cuentas_base.setTipo(tabla.getString("tipo"));
                cuentas_base.setCuentas(tabla.getString("cuentas"));
                cuentas_base.setCuenta_padre(tabla.getString("cuenta_padre"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cuentas_base;
    }

    public String create_Cuenta_Padre(Cuentas_Base cuentas_base) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into cuentas_base (num_cuenta, descripcion, tipo, cuentas, final) values (?, ?, ?, ?, 'N')");
            ps.setString(1, cuentas_base.getNum_cuenta());
            ps.setString(2, cuentas_base.getDescripcion());
            ps.setString(3, cuentas_base.getTipo());
            ps.setString(4, cuentas_base.getCuentas());

            ps.executeUpdate();
            ps.close();
            
            ok = "ok";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public String create_Cuenta_Hijo(Cuentas_Base cuentas_base) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into cuentas_base (num_cuenta, descripcion, tipo, cuentas, cuenta_padre, final) values (?, ?, ?, ?, ?, ?)");
            ps.setString(1, cuentas_base.getNum_cuenta());
            ps.setString(2, cuentas_base.getDescripcion());
            ps.setString(3, cuentas_base.getTipo());
            ps.setString(4, cuentas_base.getCuentas());
            ps.setString(5, cuentas_base.getCuenta_padre());
            ps.setString(6, cuentas_base.getFinalS());

            ps.executeUpdate();

            ps.close();
            
            ok = "ok";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void update(Cuentas_Base cuentas_base) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update cuentas_base set descripcion = ?, tipo = ?, cuentas = ?, cuenta_padre = ? where num_cuenta = ?");
            ps.setString(1, cuentas_base.getDescripcion());
            ps.setString(2, cuentas_base.getTipo());
            ps.setString(3, cuentas_base.getCuentas());
            ps.setString(4, cuentas_base.getCuenta_padre());
            ps.setString(5, cuentas_base.getNum_cuenta());

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete_Cuentas(String num_cuenta) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from cuentas_base where num_cuenta = ?");
            ps.setString(1, num_cuenta);

            ps.executeUpdate();

            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Cuentas_Base() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuentas_base order by num_cuenta");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuentas_Base cuentas_base = new Cuentas_Base();
                cuentas_base.setNum_cuenta(tabla.getString("num_cuenta"));
                cuentas_base.setDescripcion(tabla.getString("descripcion"));
                cuentas_base.setTipo(tabla.getString("tipo"));
                cuentas_base.setCuentas(tabla.getString("cuentas"));
                lista.add(cuentas_base);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cuentas_Base_Final_N() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuentas_base where final = 'N' order by num_cuenta");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuentas_Base cuentas_base = new Cuentas_Base();
                cuentas_base.setNum_cuenta(tabla.getString("num_cuenta"));
                cuentas_base.setDescripcion(tabla.getString("descripcion"));
                cuentas_base.setTipo(tabla.getString("tipo"));
                cuentas_base.setCuentas(tabla.getString("cuentas"));
                lista.add(cuentas_base);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cuentas_Base_Padre() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuentas_base where cuenta_padre is null");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuentas_Base cuentas_base = new Cuentas_Base();
                cuentas_base.setNum_cuenta(tabla.getString("num_cuenta"));
                cuentas_base.setDescripcion(tabla.getString("descripcion"));
                cuentas_base.setTipo(tabla.getString("tipo"));
                cuentas_base.setCuentas(tabla.getString("cuentas"));
                lista.add(cuentas_base);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cuentas_Base_Hijo() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuentas_base where cuenta_padre is not null");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuentas_Base cuentas_base = new Cuentas_Base();
                cuentas_base.setNum_cuenta(tabla.getString("num_cuenta"));
                cuentas_base.setDescripcion(tabla.getString("descripcion"));
                cuentas_base.setTipo(tabla.getString("tipo"));
                cuentas_base.setCuentas(tabla.getString("cuentas"));
                lista.add(cuentas_base);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cuentas_Base_Ultimo_Hijo() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuentas_base where cuenta_padre is not null and final = 'S'");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuentas_Base cuentas_base = new Cuentas_Base();
                cuentas_base.setNum_cuenta(tabla.getString("num_cuenta"));
                cuentas_base.setDescripcion(tabla.getString("descripcion"));
                cuentas_base.setTipo(tabla.getString("tipo"));
                cuentas_base.setCuentas(tabla.getString("cuentas"));
                lista.add(cuentas_base);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cuentas_Base_Vista_por_fechas(java.sql.Date fecha_inicio) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuentas_base where cuenta_padre is not null and final = 'S'");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuentas_Base cuentas_base = new Cuentas_Base();
                cuentas_base.setNum_cuenta(tabla.getString("num_cuenta"));
                cuentas_base.setDescripcion(tabla.getString("descripcion"));
                cuentas_base.setTipo(tabla.getString("tipo"));
                cuentas_base.setCuentas(tabla.getString("cuentas"));
                lista.add(cuentas_base);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cuentas_Base_Hijo_por_Padre(String cuenta_padre) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cuentas_base where cuenta_padre = ?");
            ps.setString(1, cuenta_padre);

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cuentas_Base cuentas_base = new Cuentas_Base();
                cuentas_base.setNum_cuenta(tabla.getString("num_cuenta"));
                cuentas_base.setDescripcion(tabla.getString("descripcion"));
                cuentas_base.setTipo(tabla.getString("tipo"));
                cuentas_base.setCuentas(tabla.getString("cuentas"));
                lista.add(cuentas_base);
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
