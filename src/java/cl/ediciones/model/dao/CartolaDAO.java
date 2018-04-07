package cl.ediciones.model.dao;

import cl.ediciones.model.Cartola;
import cl.ediciones.util.FechaStr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartolaDAO {

    public Cartola retrive_Cartola(int id_cartola) throws SQLException {
        Cartola cartola = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cartola where id_cartola = ?");
            ps.setInt(1, id_cartola);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cartola = new Cartola();
                cartola.setId_cartola(tabla.getInt("id_cartola"));
                cartola.setFecha(tabla.getDate("fecha"));
                cartola.setDescripcion(tabla.getString("descripcion"));
                cartola.setDocumento(tabla.getString("documento"));
                cartola.setAbonos(tabla.getInt("abonos"));
                cartola.setCargos(tabla.getInt("cargos"));
                cartola.setSaldos(tabla.getInt("saldos"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cartola;
    }

    public String createCartola(Cartola cartola) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into cartola (fecha, descripcion, documento, abonos, cargos) values (?, ?, ?, ?, ?)");

            java.sql.Date fecha = new java.sql.Date(cartola.getFecha().getTime());
            ps.setDate(1, fecha);

            ps.setString(2, cartola.getDescripcion());
            ps.setString(3, cartola.getDocumento());
            ps.setInt(4, cartola.getAbonos());
            ps.setInt(5, cartola.getCargos());

            ps.executeUpdate();

            ps.close();
            ok = "ok";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void deleteCartola(int id_cartola) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from cartola where id_cartola = ?");
            ps.setInt(1, id_cartola);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerCartola() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from cartola where id_cartola != 0");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cartola cartola = new Cartola();
                cartola.setId_cartola(tabla.getInt("id_cartola"));
                cartola.setFecha(tabla.getDate("fecha"));
                cartola.setDescripcion(tabla.getString("descripcion"));
                cartola.setDocumento(tabla.getString("documento"));
                cartola.setAbonos(tabla.getInt("abonos"));
                cartola.setCargos(tabla.getInt("cargos"));
                cartola.setSaldos(tabla.getInt("saldos"));
                lista.add(cartola);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Cartola_por_Fecha(String mes_inicio, String mes_fin, String ano_inicio, String ano_fin) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM cartola where DATE_FORMAT(fecha, '%m') between '"+mes_inicio+"' and '"+mes_fin+"' and DATE_FORMAT(fecha, '%Y') between '"+ano_inicio+"' and '"+ano_fin+"' order by fecha");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Cartola cartola = new Cartola();
                cartola.setId_cartola(tabla.getInt("id_cartola"));
                cartola.setFecha(tabla.getDate("fecha"));
                cartola.setDescripcion(tabla.getString("descripcion"));
                cartola.setDocumento(tabla.getString("documento"));
                cartola.setCargos(tabla.getInt("cargos"));
                cartola.setAbonos(tabla.getInt("abonos"));
                lista.add(cartola);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public Cartola traerTodos_Saldo_Anterior_Cartola(String fecha_inicio) throws SQLException {
        Cartola cartola = null;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT sum(cargos) as cargos, sum(abonos) as abonos, sum(saldos) as saldos FROM cartola WHERE fecha < ?");
            ps.setDate(1, FechaStr.stringToDate(fecha_inicio));

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                cartola = new Cartola();
                cartola.setCargos(tabla.getInt("cargos"));
                cartola.setAbonos(tabla.getInt("abonos"));
                cartola.setAbonos(tabla.getInt("saldos"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cartola;
    }
}
