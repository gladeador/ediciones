package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Guia_Despacho {

    private int id_guia_despacho;
    private int num_guia;
    private java.util.Date fecha_guia;
    private int sub_total;
    private int descuento;
    private int neto;
    private int iva;
    private int total;
    
    private Clientes clientes;

    public int getId_guia_despacho() {
        return id_guia_despacho;
    }

    public void setId_guia_despacho(int id_guia_despacho) {
        this.id_guia_despacho = id_guia_despacho;
    }

    public int getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(int num_guia) {
        this.num_guia = num_guia;
    }

    public java.util.Date getFecha_guia() {
        return fecha_guia;
    }

//***********************************************************
    public String getFecha_guiaStr() {
        if (!(getFecha_guia() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_guia().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_guia(java.util.Date fecha_guia) {
        this.fecha_guia = fecha_guia;
    }

    public int getSub_total() {
        return sub_total;
    }

    public void setSub_total(int sub_total) {
        this.sub_total = sub_total;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getNeto() {
        return neto;
    }

    public void setNeto(int neto) {
        this.neto = neto;
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

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

}
