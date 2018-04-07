package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Letra {
    
    private int id_letra;
    private int num_letra;
    private int monto;
    private int rut_cliente;
    private java.util.Date fecha_recepcion_documento;
    private java.util.Date fecha_vencimiento;
    private int monto_cancelado;
    private java.util.Date fecha_cancela;
    private int mes_letra_ven;
    private int mes_letra_pag;
    private int ano_letra_ven;
    private int ano_letra_pag;
    
    private Factura factura;
    private Estado_Letra estado_letra;
    private Cuentas_Base cuentas_base;

    public int getNum_letra() {
        return num_letra;
    }

    public void setNum_letra(int num_letra) {
        this.num_letra = num_letra;
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

    public java.util.Date getFecha_recepcion_documento() {
        return fecha_recepcion_documento;
    }

//***********************************************************
    public String getFecha_recepcion_documentoStr() {
        if (!(getFecha_recepcion_documento() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_recepcion_documento().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_recepcion_documento(java.util.Date fecha_recepcion_documento) {
        this.fecha_recepcion_documento = fecha_recepcion_documento;
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

    public int getId_letra() {
        return id_letra;
    }

    public void setId_letra(int id_letra) {
        this.id_letra = id_letra;
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

    public int getMes_letra_ven() {
        return mes_letra_ven;
    }

    public void setMes_letra_ven(int mes_letra_ven) {
        this.mes_letra_ven = mes_letra_ven;
    }

    public int getMes_letra_pag() {
        return mes_letra_pag;
    }

    public void setMes_letra_pag(int mes_letra_pag) {
        this.mes_letra_pag = mes_letra_pag;
    }

    public int getAno_letra_ven() {
        return ano_letra_ven;
    }

    public void setAno_letra_ven(int ano_letra_ven) {
        this.ano_letra_ven = ano_letra_ven;
    }

    public int getAno_letra_pag() {
        return ano_letra_pag;
    }

    public void setAno_letra_pag(int ano_letra_pag) {
        this.ano_letra_pag = ano_letra_pag;
    }

    public Estado_Letra getEstado_letra() {
        return estado_letra;
    }

    public void setEstado_letra(Estado_Letra estado_letra) {
        this.estado_letra = estado_letra;
    }

    public Cuentas_Base getCuentas_base() {
        return cuentas_base;
    }

    public void setCuentas_base(Cuentas_Base cuentas_base) {
        this.cuentas_base = cuentas_base;
    }

}
