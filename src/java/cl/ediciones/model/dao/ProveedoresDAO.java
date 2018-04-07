package cl.ediciones.model.dao;

import cl.ediciones.model.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedoresDAO {

    public ProveedoresDAO() {
    }

    public Proveedores retrieve(int rut) throws SQLException {
        Proveedores proveedor = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from proveedores where rut = ? and estado = 'A'");
            ps.setInt(1, rut);

            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                proveedor = new Proveedores();
                proveedor.setRut(tabla.getInt("rut"));
                proveedor.setDv(tabla.getString("dv"));
                proveedor.setNombre(tabla.getString("nombre"));
                proveedor.setGiro(tabla.getString("giro"));
                proveedor.setDireccion_particular(tabla.getString("direccion_particular"));
                proveedor.setEmail(tabla.getString("email"));
                proveedor.setNombre_contacto(tabla.getString("nombre_contacto"));
                proveedor.setDesc_corta(tabla.getString("desc_corta"));
                proveedor.setPais(tabla.getString("pais"));
                proveedor.setCiudad(tabla.getString("ciudad"));
                proveedor.setId_nacionalidad(tabla.getInt("id_nacionalidad"));
                proveedor.setEstado(tabla.getString("estado"));
                proveedor.setTipo_proveedor(tabla.getInt("tipo_proveedor"));
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return proveedor;
    }

    public String create(Proveedores proveedor) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into proveedores (rut, dv, nombre, giro, direccion_particular, email, nombre_contacto, desc_corta, pais, ciudad, id_region, id_comuna, id_nacionalidad, tipo_proveedor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, proveedor.getRut());
            ps.setString(2, proveedor.getDv());
            ps.setString(3, proveedor.getNombre());
            ps.setString(4, proveedor.getGiro());
            ps.setString(5, proveedor.getDireccion_particular());
            ps.setString(6, proveedor.getEmail());
            ps.setString(7, proveedor.getNombre_contacto());
            ps.setString(8, proveedor.getDesc_corta());
            ps.setString(9, proveedor.getPais());
            ps.setString(10, proveedor.getCiudad());
            ps.setInt(11, proveedor.getId_region());
            ps.setInt(12, proveedor.getId_comuna());
            ps.setInt(13, proveedor.getId_nacionalidad());
            ps.setInt(14, proveedor.getTipo_proveedor());

            ps.executeUpdate();
            ok = "ok";
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }

    public void update(Proveedores proveedor) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update proveedores set dv = ?, nombre = ?, giro = ?, direccion_particular = ?, email = ?, nombre_contacto = ?, desc_corta = ?, pais = ?, ciudad = ?, id_nacionalidad = ?, estado = ?, tipo_proveedor = ? where rut = ?");
            ps.setString(1, proveedor.getDv());
            ps.setString(2, proveedor.getNombre());
            ps.setString(3, proveedor.getGiro());
            ps.setString(4, proveedor.getDireccion_particular());
            ps.setString(5, proveedor.getEmail());
            ps.setString(6, proveedor.getNombre_contacto());
            ps.setString(7, proveedor.getDesc_corta());
            ps.setString(8, proveedor.getPais());
            ps.setString(9, proveedor.getCiudad());
            ps.setInt(10, proveedor.getRut());
            ps.setInt(11, proveedor.getId_nacionalidad());
            ps.setString(12, proveedor.getEstado());
            ps.setInt(13, proveedor.getTipo_proveedor());

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
            ps = con.prepareStatement("update proveedores set estado = 'X' where rut = ?");
            ps.setInt(1, rut);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }

    public ArrayList traerTodos_Proveedores() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from proveedores where estado = 'A'");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Proveedores proveedor = new Proveedores();
                proveedor.setRut(tabla.getInt("rut"));
                proveedor.setDv(tabla.getString("dv"));
                proveedor.setNombre(tabla.getString("nombre"));
                proveedor.setGiro(tabla.getString("giro"));
                proveedor.setDireccion_particular(tabla.getString("direccion_particular"));
                proveedor.setEmail(tabla.getString("email"));
                proveedor.setNombre_contacto(tabla.getString("nombre_contacto"));
                proveedor.setDesc_corta(tabla.getString("desc_corta"));
                proveedor.setPais(tabla.getString("pais"));
                proveedor.setCiudad(tabla.getString("ciudad"));
                proveedor.setId_nacionalidad(tabla.getInt("id_nacionalidad"));
                proveedor.setEstado(tabla.getString("estado"));
                proveedor.setTipo_proveedor(tabla.getInt("tipo_proveedor"));
                lista.add(proveedor);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Proveedores_Productos() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from proveedores where estado='A'");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Proveedores proveedor = new Proveedores();
                proveedor.setRut(tabla.getInt("rut"));
                proveedor.setDv(tabla.getString("dv"));
                proveedor.setNombre(tabla.getString("nombre"));
                proveedor.setGiro(tabla.getString("giro"));
                proveedor.setDireccion_particular(tabla.getString("direccion_particular"));
                proveedor.setEmail(tabla.getString("email"));
                proveedor.setNombre_contacto(tabla.getString("nombre_contacto"));
                proveedor.setDesc_corta(tabla.getString("desc_corta"));
                proveedor.setPais(tabla.getString("pais"));
                proveedor.setCiudad(tabla.getString("ciudad"));
                proveedor.setId_nacionalidad(tabla.getInt("id_nacionalidad"));
                proveedor.setEstado(tabla.getString("estado"));
                proveedor.setTipo_proveedor(tabla.getInt("tipo_proveedor"));
                lista.add(proveedor);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }

    public ArrayList traerTodos_Proveedores_Gastos() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from proveedores where tipo_proveedor = 2");

            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Proveedores proveedor = new Proveedores();
                proveedor.setRut(tabla.getInt("rut"));
                proveedor.setDv(tabla.getString("dv"));
                proveedor.setNombre(tabla.getString("nombre"));
                proveedor.setGiro(tabla.getString("giro"));
                proveedor.setDireccion_particular(tabla.getString("direccion_particular"));
                proveedor.setEmail(tabla.getString("email"));
                proveedor.setNombre_contacto(tabla.getString("nombre_contacto"));
                proveedor.setDesc_corta(tabla.getString("desc_corta"));
                proveedor.setPais(tabla.getString("pais"));
                proveedor.setCiudad(tabla.getString("ciudad"));
                proveedor.setId_nacionalidad(tabla.getInt("id_nacionalidad"));
                proveedor.setEstado(tabla.getString("estado"));
                proveedor.setTipo_proveedor(tabla.getInt("tipo_proveedor"));
                lista.add(proveedor);
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
