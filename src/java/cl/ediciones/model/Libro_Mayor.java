package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Libro_Mayor {

    private int valor;
    private java.util.Date fecha;
    private String debe_haber;
    
    private Cuentas_Base cuentas_base;
    private Comprobante comprobante;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public java.util.Date getFecha() {
        return fecha;
    }

//***********************************************************
    public String getFechaStr() {
        if (!(getFecha() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    public String getDebe_haber() {
        return debe_haber;
    }

    public void setDebe_haber(String debe_haber) {
        this.debe_haber = debe_haber;
    }

    public Cuentas_Base getCuentas_base() {
        return cuentas_base;
    }

    public void setCuentas_base(Cuentas_Base cuentas_base) {
        this.cuentas_base = cuentas_base;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }
}
