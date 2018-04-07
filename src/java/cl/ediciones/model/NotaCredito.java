package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class NotaCredito {

    private int id_notacredito;
    private int num_notacredito;
    private java.util.Date fecha_notacredito;
    private int id_factura;
    private int subtotal;
    private int descuento;
    private int neto;
    private int iva;
    private int total;
    private int rut_cliente;
    private String estado;
    private int id_tipo_envio;
    private Clientes clientes;

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_notacredito() {
        return id_notacredito;
    }

    public void setId_notacredito(int id_notacredito) {
        this.id_notacredito = id_notacredito;
    }

    public int getNum_notacredito() {
        return num_notacredito;
    }

    public void setNum_notacredito(int num_notacredito) {
        this.num_notacredito = num_notacredito;
    }

    public java.util.Date getFecha_notacredito() {
        return fecha_notacredito;
    }

//***********************************************************
    public String getFecha_notacreditoStr() {
        if (!(getFecha_notacredito() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_notacredito().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_notacredito(java.util.Date fecha_notacredito) {
        this.fecha_notacredito = fecha_notacredito;
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
