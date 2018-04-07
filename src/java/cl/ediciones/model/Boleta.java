package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Boleta {

    private int id_boleta;
    private int num_boleta;
    private java.util.Date fecha_boleta;
    private int subtotal;
    private int descuento;
    private int neto;
    private int iva;
    private int total;
    private int rut_cliente;
    private String estado;
    private int id_tipo_envio;
    private Clientes clientes;

    public int getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(int id_boleta) {
        this.id_boleta = id_boleta;
    }

    public int getNum_boleta() {
        return num_boleta;
    }

    public void setNum_boleta(int num_boleta) {
        this.num_boleta = num_boleta;
    }

    public java.util.Date getFecha_boleta() {
        return fecha_boleta;
    }

//***********************************************************
    public String getFecha_boletaStr() {
        if (!(getFecha_boleta() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_boleta().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_boleta(java.util.Date fecha_boleta) {
        this.fecha_boleta = fecha_boleta;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
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

    public int getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(int rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_tipo_envio() {
        return id_tipo_envio;
    }

    public void setId_tipo_envio(int id_tipo_envio) {
        this.id_tipo_envio = id_tipo_envio;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
}
