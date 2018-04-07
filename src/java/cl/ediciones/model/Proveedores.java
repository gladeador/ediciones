package cl.ediciones.model;

public class Proveedores {

    private int rut;
    private String dv;
    private String nombre;
    private String giro;
    private String direccion_particular;
    private String email;
    private String nombre_contacto;
    private String pais;
    private String ciudad;
    private String desc_corta;
    private int id_nacionalidad;
    private String estado;
    private int tipo_proveedor;
    private int id_region;
    private int id_comuna;

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_comuna) {
        this.id_comuna = id_comuna;
    }

  
    
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDesc_corta() {
        return desc_corta;
    }

    public void setDesc_corta(String desc_corta) {
        this.desc_corta = desc_corta;
    }

    public Fono_Contacto getFono() {
        return fono;
    }

    public void setFono(Fono_Contacto fono) {
        this.fono = fono;
    }

    public int getId_nacionalidad() {
        return id_nacionalidad;
    }

    public void setId_nacionalidad(int id_nacionalidad) {
        this.id_nacionalidad = id_nacionalidad;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the tipo_proveedor
     */
    public int getTipo_proveedor() {
        return tipo_proveedor;
    }

    /**
     * @param tipo_proveedor the tipo_proveedor to set
     */
    public void setTipo_proveedor(int tipo_proveedor) {
        this.tipo_proveedor = tipo_proveedor;
    }
}
