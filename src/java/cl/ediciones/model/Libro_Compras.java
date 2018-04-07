package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Libro_Compras {

    private String tipo;
    private String documento;
    private java.util.Date fecha;
    private int exento;
    private int afecto;
    private int iva;
    private int total;
    
    private Proveedores proveedores;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public int getExento() {
        return exento;
    }

    public void setExento(int exento) {
        this.exento = exento;
    }

    public int getAfecto() {
        return afecto;
    }

    public void setAfecto(int afecto) {
        this.afecto = afecto;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
