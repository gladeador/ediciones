package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Factura {
    private int id_factura;
    private int num_factura;
    private java.util.Date fecha_factura;
    private int subtotal;
    private int descuento;
    private int neto;
    private int iva;
    private int total;
    private int rut_cliente;
    private String estado;
    private int id_tipo_envio;
    private int saldo_pago;
    private int mes_factura;
    private int ano_factura;
    
    private Clientes clientes;
    private Forma_Pago forma_pago;
    private Cuentas_Base cuenta_base_neto;
    private Cuentas_Base cuenta_base_iva;
    private Cuentas_Base cuenta_base_total;

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public java.util.Date getFecha_factura() {
        return fecha_factura;
    }

//***********************************************************
    public String getFecha_facturaStr() {
        if (!(getFecha_factura() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_factura().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_factura(java.util.Date fecha_factura) {
        this.fecha_factura = fecha_factura;
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

    public Forma_Pago getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(Forma_Pago forma_pago) {
        this.forma_pago = forma_pago;
    }

    public int getSaldo_pago() {
        return saldo_pago;
    }

    public void setSaldo_pago(int saldo_pago) {
        this.saldo_pago = saldo_pago;
    }

    public int getMes_factura() {
        return mes_factura;
    }

    public void setMes_factura(int mes_factura) {
        this.mes_factura = mes_factura;
    }

    public int getAno_factura() {
        return ano_factura;
    }

    public void setAno_factura(int ano_factura) {
        this.ano_factura = ano_factura;
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
