package cl.ediciones.model;

public class Clientes {

    private int rut;
    private String dv;
    private String nombre;
    private String giro;
    private String direccion_particular;
    private String direccion_facturacion;
    private String email;
    private String nombre_contacto;
    private String dv_facturacion;
    private int rut_facturacion;
    private int ranking;
    
    private Comuna comuna;
    
    private Fono_Contacto fono;

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getDireccion_particular() {
        return direccion_particular;
    }

    public void setDireccion_particular(String direccion_particular) {
        this.direccion_particular = direccion_particular;
    }

    public String getDireccion_facturacion() {
        return direccion_facturacion;
    }

    public void setDireccion_facturacion(String direccion_facturacion) {
        this.direccion_facturacion = direccion_facturacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public int getRut_facturacion() {
        return rut_facturacion;
    }

    public void setRut_facturacion(int rut_facturacion) {
        this.rut_facturacion = rut_facturacion;
    }

    public String getDv_facturacion() {
        return dv_facturacion;
    }

    public void setDv_facturacion(String dv_facturacion) {
        this.dv_facturacion = dv_facturacion;
    }

    public Fono_Contacto getFono() {
        return fono;
    }

    public void setFono(Fono_Contacto fono) {
        this.fono = fono;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
