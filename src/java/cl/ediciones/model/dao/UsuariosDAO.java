package cl.ediciones.model.dao;

import cl.ediciones.model.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuariosDAO {

    public UsuariosDAO() {
    }

    public Usuarios retrieve(int rut) throws SQLException {
        Usuarios usuario = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from usuarios where rut = ?");
            ps.setInt(1, rut);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                usuario = new Usuarios();
                usuario.setId_usuarios(tabla.getInt("id_usuarios"));
                usuario.setRut(tabla.getInt("rut"));
                usuario.setDv(tabla.getString("dv"));
                usuario.setNombres(tabla.getString("nombres"));
                usuario.setEmail(tabla.getString("email"));
                usuario.setClave(tabla.getString("clave"));
                usuario.setId_perfil(tabla.getInt("id_perfil"));
                usuario.setObservacion(tabla.getString("observacion"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return usuario;
    }

    public void create(Usuarios usuario) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into usuarios (rut, dv, nombres, email, clave, id_perfil, observacion) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, usuario.getRut());
            ps.setString(2, usuario.getDv());
            ps.setString(3, usuario.getNombres());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getClave());
            ps.setInt(6, usuario.getId_perfil());
            ps.setString(7, usuario.getObservacion());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update(Usuarios usuario) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update usuarios set dv = ?, nombres = ?, email = ?, id_perfil = ?, observacion = ? where rut = ?");
            ps.setString(1, usuario.getDv());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getEmail());
            ps.setInt(4, usuario.getId_perfil());
            ps.setString(5, usuario.getObservacion());
            ps.setInt(6, usuario.getRut());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void update_Cambio_Clave(Usuarios usuario) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update usuarios set clave = ? where rut = ?");
            ps.setString(1, usuario.getClave());
            ps.setInt(2, usuario.getRut());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public void delete(int rut) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("delete from usuarios where rut = ?");
            ps.setInt(1, rut);

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Usuarios() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from usuarios");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId_usuarios(tabla.getInt("id_usuarios"));
                usuario.setRut(tabla.getInt("rut"));
                usuario.setDv(tabla.getString("dv"));
                usuario.setNombres(tabla.getString("nombres"));
                usuario.setEmail(tabla.getString("email"));
                usuario.setClave(tabla.getString("clave"));
                usuario.setId_perfil(tabla.getInt("id_perfil"));
                usuario.setObservacion(tabla.getString("observacion"));
                lista.add(usuario);
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
