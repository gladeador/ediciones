package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Cuota {
    
    private int id_cuota;
    private int monto;
    private int rut_cliente;
    private java.util.Date fecha_vencimiento;
    private int monto_cancelado;
    private java.util.Date fecha_cancela;
    private int mes_cuota_ven;
    private int mes_cuota_pag;
    private int ano_cuota_ven;
    private int ano_cuota_pag;
    
    private Factura factura;
    private Cuentas_Base cuentas_base;

    public int getId_cuota() {
        return id_cuota;
    }

    public void setId_cuota(int id_cuota) {
        this.id_cuota = id_cuota;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(int rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public java.util.Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

//***********************************************************
    public String getFecha_vencimientoStr() {
        if (!(getFecha_vencimiento() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_vencimiento().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_vencimiento(java.util.Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public int getMonto_cancelado() {
        return monto_cancelado;
    }

    public void setMonto_cancelado(int monto_cancelado) {
        this.monto_cancelado = monto_cancelado;
    }

    public java.util.Date getFecha_cancela() {
        return fecha_cancela;
    }

//***********************************************************
    public String getFecha_cancelaStr() {
        if (!(getFecha_cancela() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_cancela().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_cancela(java.util.Date fecha_cancela) {
        this.fecha_cancela = fecha_cancela;
    }

    public int getMes_cuota_ven() {
        return mes_cuota_ven;
    }

    public void setMes_cuota_ven(int mes_cuota_ven) {
        this.mes_cuota_ven = mes_cuota_ven;
    }

    public int getMes_cuota_pag() {
        return mes_cuota_pag;
    }

    public void setMes_cuota_pag(int mes_cuota_pag) {
        this.mes_cuota_pag = mes_cuota_pag;
    }

    public int getAno_cuota_ven() {
        return ano_cuota_ven;
    }

    public void setAno_cuota_ven(int ano_cuota_ven) {
        this.ano_cuota_ven = ano_cuota_ven;
    }

    public int getAno_cuota_pag() {
        return ano_cuota_pag;
    }

    public void setAno_cuota_pag(int ano_cuota_pag) {
        this.ano_cuota_pag = ano_cuota_pag;
    }

    public Cuentas_Base getCuentas_base() {
        return cuentas_base;
    }

    public void setCuentas_base(Cuentas_Base cuentas_base) {
        this.cuentas_base = cuentas_base;
    }
    
}
