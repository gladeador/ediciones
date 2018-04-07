package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Gastos {

    private int id_gastos;
    private String num_boleta;
    private java.util.Date fecha_gasto;
    private int neto_exento;
    private int neto_afecto;
    private int iva_afecto;
    private int total_afecto;
    
    private Proveedores proveedores;
    private Tipo_Documento tipo_documento;
    private Cuentas_Base cuenta_base_neto;
    private Cuentas_Base cuenta_base_iva;
    private Cuentas_Base cuenta_base_total;
    private Forma_Pago forma_pago;

    public int getId_gastos() {
        return id_gastos;
    }

    public void setId_gastos(int id_gastos) {
        this.id_gastos = id_gastos;
    }

    public String getNum_boleta() {
        return num_boleta;
    }

    public void setNum_boleta(String num_boleta) {
        this.num_boleta = num_boleta;
    }

    public java.util.Date getFecha_gasto() {
        return fecha_gasto;
    }

//***********************************************************
    public String getFecha_gastoStr() {
        if (!(getFecha_gasto() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_gasto().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_gasto(java.util.Date fecha_gasto) {
        this.fecha_gasto = fecha_gasto;
    }

    public int getNeto_exento() {
        return neto_exento;
    }

    public void setNeto_exento(int neto_exento) {
        this.neto_exento = neto_exento;
    }

    public int getNeto_afecto() {
        return neto_afecto;
    }

    public void setNeto_afecto(int neto_afecto) {
        this.neto_afecto = neto_afecto;
    }

    public int getIva_afecto() {
        return iva_afecto;
    }

    public void setIva_afecto(int iva_afecto) {
        this.iva_afecto = iva_afecto;
    }

    public int getTotal_afecto() {
        return total_afecto;
    }

    public void setTotal_afecto(int total_afecto) {
        this.total_afecto = total_afecto;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public Tipo_Documento getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Tipo_Documento tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public Forma_Pago getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(Forma_Pago forma_pago) {
        this.forma_pago = forma_pago;
    }

    public Cuentas_Base getCuenta_base_neto() {
        return cuenta_base_neto;
    }

    public void setCuenta_base_neto(Cuentas_Base cuenta_base_neto) {
        this.cuenta_base_neto = cuenta_base_neto;
    }

    public Cuentas_Base getCuenta_base_iva() {
        return cuenta_base_iva;
    }

    public void setCuenta_base_iva(Cuentas_Base cuenta_base_iva) {
        this.cuenta_base_iva = cuenta_base_iva;
    }

    public Cuentas_Base getCuenta_base_total() {
        return cuenta_base_total;
    }

    public void setCuenta_base_total(Cuentas_Base cuenta_base_total) {
        this.cuenta_base_total = cuenta_base_total;
    }

}
