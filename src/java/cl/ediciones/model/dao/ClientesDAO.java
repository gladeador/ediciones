package cl.ediciones.model.dao;

import cl.ediciones.model.Comuna;
import cl.ediciones.model.Clientes;
import cl.ediciones.model.Region;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientesDAO {
    
    public ClientesDAO() {
    }
    
    public Clientes retrieve(int rut) throws SQLException {
        Clientes cliente = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from clientes where rut = ? and estado = 'A'");
            ps.setInt(1, rut);
            
            ResultSet tabla = ps.executeQuery();
            if (tabla.next()) {
                cliente = new Clientes();
                cliente.setRut(tabla.getInt("rut"));
                cliente.setDv(tabla.getString("dv"));
                cliente.setNombre(tabla.getString("nombre"));
                cliente.setGiro(tabla.getString("giro"));
                cliente.setDireccion_particular(tabla.getString("direccion_particular"));
                cliente.setDireccion_facturacion(tabla.getString("direccion_facturacion"));
                cliente.setEmail(tabla.getString("email"));
                cliente.setNombre_contacto(tabla.getString("nombre_contacto"));
                cliente.setRut_facturacion(tabla.getInt("rut_facturacion"));
                cliente.setDv_facturacion(tabla.getString("dv_facturacion"));
                
                ComunaDAO ciuDAO = new ComunaDAO();
                Comuna comuna = ciuDAO.retrive_Comuna(tabla.getInt("id_comuna"));
                cliente.setComuna(comuna);
                
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return cliente;
    }
    
    public String create(Clientes cliente) throws SQLException {
        String ok = null;
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into clientes (rut, dv, nombre, giro, direccion_particular, direccion_facturacion, email, nombre_contacto, rut_facturacion, dv_facturacion, id_comuna) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, cliente.getRut());
            ps.setString(2, cliente.getDv());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getGiro());
            ps.setString(5, cliente.getDireccion_particular());
            ps.setString(6, cliente.getDireccion_facturacion());
            ps.setString(7, cliente.getEmail());
            ps.setString(8, cliente.getNombre_contacto());
            ps.setInt(9, cliente.getRut_facturacion());
            ps.setString(10, cliente.getDv_facturacion());
            ps.setInt(11, cliente.getComuna().getId_comuna());
            
            ps.executeUpdate();
            
            ps.close();
            ok = "ok";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return ok;
    }
    
    public void update(Clientes cliente) throws SQLException {
        ConexionDAOPool conDAO = new ConexionDAOPool();
        Connection con = conDAO.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("update clientes set dv = ?, nombre = ?, giro = ?, direccion_particular = ?, direccion_facturacion = ?, email = ?, nombre_contacto = ?, rut_facturacion = ?, dv_facturacion = ?, id_comuna = ? where rut = ?");
            ps.setString(1, cliente.getDv());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getGiro());
            ps.setString(4, cliente.getDireccion_particular());
            ps.setString(5, cliente.getDireccion_facturacion());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getNombre_contacto());
            ps.setInt(8, cliente.getRut_facturacion());
            ps.setString(9, cliente.getDv_facturacion());
            ps.setInt(10, cliente.getComuna().getId_comuna());
            ps.setInt(11, cliente.getRut());
            
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
            ps = con.prepareStatement("update clientes set estado = 'X' where rut = ?");
            ps.setInt(1, rut);
            
            ps.executeUpdate();
            
            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
    
    public ArrayList traerTodos_Clientes() throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from clientes c, comuna ci, region r "
                    + "where c.estado = 'A' "
                    + "and c.id_comuna = ci.id_comuna "
                    + "and ci.id_region = r.id_region "
                    + "order by c.nombre");
            
            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Clientes cliente = new Clientes();
                cliente.setRut(tabla.getInt("rut"));
                cliente.setDv(tabla.getString("dv"));
                cliente.setNombre(tabla.getString("nombre"));
                cliente.setGiro(tabla.getString("giro"));
                cliente.setDireccion_particular(tabla.getString("direccion_particular"));
                cliente.setDireccion_facturacion(tabla.getString("direccion_facturacion"));
                cliente.setEmail(tabla.getString("email"));
                cliente.setNombre_contacto(tabla.getString("nombre_contacto"));
                cliente.setRut_facturacion(tabla.getInt("rut_facturacion"));
                cliente.setDv_facturacion(tabla.getString("dv_facturacion"));
                
                Comuna comuna = new Comuna();
                comuna.setId_comuna(tabla.getInt("id_comuna"));
                comuna.setDescripcion(tabla.getString("descripcion"));
                
                Region region = new Region();
                region.setId_region(tabla.getInt("id_region"));
                region.setDescripcion(tabla.getString("descripcion"));
                comuna.setRegion(region);
                
                cliente.setComuna(comuna);
                
                lista.add(cliente);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Clientes_Region(int id_region) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from clientes c, comuna ci, region r "
                    + "where c.estado = 'A' "
                    + "and c.id_comuna = ci.id_comuna "
                    + "and ci.id_region = r.id_region "
                    + "and ci.id_region = ? "
                    + "order by c.nombre");
            ps.setInt(1, id_region);
            
            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Clientes cliente = new Clientes();
                cliente.setRut(tabla.getInt("rut"));
                cliente.setDv(tabla.getString("dv"));
                cliente.setNombre(tabla.getString("nombre"));
                cliente.setGiro(tabla.getString("giro"));
                cliente.setDireccion_particular(tabla.getString("direccion_particular"));
                cliente.setDireccion_facturacion(tabla.getString("direccion_facturacion"));
                cliente.setEmail(tabla.getString("email"));
                cliente.setNombre_contacto(tabla.getString("nombre_contacto"));
                cliente.setRut_facturacion(tabla.getInt("rut_facturacion"));
                cliente.setDv_facturacion(tabla.getString("dv_facturacion"));
                
                Comuna comuna = new Comuna();
                comuna.setId_comuna(tabla.getInt("id_comuna"));
                comuna.setDescripcion(tabla.getString("descripcion"));
                
                Region region = new Region();
                region.setId_region(tabla.getInt("id_region"));
                region.setDescripcion(tabla.getString("descripcion"));
                comuna.setRegion(region);
                
                cliente.setComuna(comuna);
                
                lista.add(cliente);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Clientes_Comuna(int id_comuna) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from clientes c, comuna ci, region r "
                    + "where c.estado = 'A' "
                    + "and c.id_comuna = ci.id_comuna "
                    + "and c.id_comuna = ? "
                    + "and ci.id_region = r.id_region "
                    + "order by c.nombre");
            ps.setInt(1, id_comuna);
            
            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Clientes cliente = new Clientes();
                cliente.setRut(tabla.getInt("rut"));
                cliente.setDv(tabla.getString("dv"));
                cliente.setNombre(tabla.getString("nombre"));
                cliente.setGiro(tabla.getString("giro"));
                cliente.setDireccion_particular(tabla.getString("direccion_particular"));
                cliente.setDireccion_facturacion(tabla.getString("direccion_facturacion"));
                cliente.setEmail(tabla.getString("email"));
                cliente.setNombre_contacto(tabla.getString("nombre_contacto"));
                cliente.setRut_facturacion(tabla.getInt("rut_facturacion"));
                cliente.setDv_facturacion(tabla.getString("dv_facturacion"));
                
                Comuna comuna = new Comuna();
                comuna.setId_comuna(tabla.getInt("id_comuna"));
                comuna.setDescripcion(tabla.getString("descripcion"));
                
                Region region = new Region();
                region.setId_region(tabla.getInt("id_region"));
                region.setDescripcion(tabla.getString("descripcion"));
                comuna.setRegion(region);
                
                cliente.setComuna(comuna);
                
                lista.add(cliente);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Clientes_por_Nombre(String nombre) throws SQLException {
        ArrayList lista = new ArrayList();
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from clientes where estado = 'A' and nombre like '" + nombre + "%' order by nombre");
            
            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Clientes cliente = new Clientes();
                cliente.setRut(tabla.getInt("rut"));
                cliente.setDv(tabla.getString("dv"));
                cliente.setNombre(tabla.getString("nombre"));
                cliente.setGiro(tabla.getString("giro"));
                cliente.setDireccion_particular(tabla.getString("direccion_particular"));
                cliente.setDireccion_facturacion(tabla.getString("direccion_facturacion"));
                cliente.setEmail(tabla.getString("email"));
                cliente.setNombre_contacto(tabla.getString("nombre_contacto"));
                cliente.setRut_facturacion(tabla.getInt("rut_facturacion"));
                cliente.setDv_facturacion(tabla.getString("dv_facturacion"));
                
                ComunaDAO ciuDAO = new ComunaDAO();
                Comuna comuna = ciuDAO.retrive_Comuna(tabla.getInt("id_comuna"));
                cliente.setComuna(comuna);
                
                lista.add(cliente);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Clientes_por_Ranking(String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        int rkn = 1;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from clientes c where c.id_comuna in (select ci.id_comuna from comuna ci) order by (SELECT sum(f.total) FROM factura f where c.rut = f.rut_cliente and f.estado in ('P', 'E') and DATE_FORMAT( f.fecha_factura , '%Y' ) = ? group by c.rut) desc");
            ps.setString(1, ano);
            
            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Clientes cliente = new Clientes();
                cliente.setRanking(rkn++);
                cliente.setRut(tabla.getInt("rut"));
                cliente.setDv(tabla.getString("dv"));
                cliente.setNombre(tabla.getString("nombre"));
                cliente.setGiro(tabla.getString("giro"));
                cliente.setDireccion_particular(tabla.getString("direccion_particular"));
                cliente.setDireccion_facturacion(tabla.getString("direccion_facturacion"));
                cliente.setEmail(tabla.getString("email"));
                cliente.setNombre_contacto(tabla.getString("nombre_contacto"));
                cliente.setRut_facturacion(tabla.getInt("rut_facturacion"));
                cliente.setDv_facturacion(tabla.getString("dv_facturacion"));
                
                ComunaDAO ciuDAO = new ComunaDAO();
                Comuna comuna = ciuDAO.retrive_Comuna(tabla.getInt("id_comuna"));
                cliente.setComuna(comuna);
                
                lista.add(cliente);
            }
            tabla.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();
        return lista;
    }
    
    public ArrayList traerTodos_Clientes_con_Factura(String mes, String ano) throws SQLException {
        ArrayList lista = new ArrayList();
        int rkn = 1;
        ConexionDAOPool conDB = new ConexionDAOPool();
        Connection con = conDB.obtenerConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from clientes c where rut in (select f.rut_cliente from factura f where f.estado in ('P', 'E') and DATE_FORMAT( f.fecha_factura , '%m' ) = ? and  DATE_FORMAT( f.fecha_factura , '%Y' ) = ? order by fecha_factura)");
            ps.setString(1, mes);
            ps.setString(2, ano);
            
            ResultSet tabla = ps.executeQuery();
            while (tabla.next()) {
                Clientes cliente = new Clientes();
                cliente.setRanking(rkn++);
                cliente.setRut(tabla.getInt("rut"));
                cliente.setDv(tabla.getString("dv"));
                cliente.setNombre(tabla.getString("nombre"));
                cliente.setGiro(tabla.getString("giro"));
                cliente.setDireccion_particular(tabla.getString("direccion_particular"));
                cliente.setDireccion_facturacion(tabla.getString("direccion_facturacion"));
                cliente.setEmail(tabla.getString("email"));
                cliente.setNombre_contacto(tabla.getString("nombre_contacto"));
                cliente.setRut_facturacion(tabla.getInt("rut_facturacion"));
                cliente.setDv_facturacion(tabla.getString("dv_facturacion"));
                
                ComunaDAO ciuDAO = new ComunaDAO();
                Comuna comuna = ciuDAO.retrive_Comuna(tabla.getInt("id_comuna"));
                cliente.setComuna(comuna);
                
                lista.add(cliente);
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
