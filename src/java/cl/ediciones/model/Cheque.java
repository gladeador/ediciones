package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Cheque {
    
    private int id_cheque;
    private int num_cheque;
    private int monto;
    private int rut_cliente;
    private java.util.Date fecha_recepcion_documento;
    private java.util.Date fecha_vencimiento;
    private int monto_cancelado;
    private java.util.Date fecha_cancela;
    private int mes_cheque_ven;
    private int mes_cheque_pag;
    private int ano_cheque_ven;
    private int ano_cheque_pag;
    
    private Banco banco;
    private Factura factura;
    private Estado_Cheque estado_cheque;
    private Cuentas_Base cuentas_base;

    public int getNum_cheque() {
        return num_cheque;
    }

    public void setNum_cheque(int num_cheque) {
        this.num_cheque = num_cheque;
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

    public int getId_cheque() {
        return id_cheque;
    }

    public void setId_cheque(int id_cheque) {
        this.id_cheque = id_cheque;
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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public int getMes_cheque_ven() {
        return mes_cheque_ven;
    }

    public void setMes_cheque_ven(int mes_cheque_ven) {
        this.mes_cheque_ven = mes_cheque_ven;
    }

    public int getMes_cheque_pag() {
        return mes_cheque_pag;
    }

    public void setMes_cheque_pag(int mes_cheque_pag) {
        this.mes_cheque_pag = mes_cheque_pag;
    }

    public int getAno_cheque_ven() {
        return ano_cheque_ven;
    }

    public void setAno_cheque_ven(int ano_cheque_ven) {
        this.ano_cheque_ven = ano_cheque_ven;
    }

    public int getAno_cheque_pag() {
        return ano_cheque_pag;
    }

    public void setAno_cheque_pag(int ano_cheque_pag) {
        this.ano_cheque_pag = ano_cheque_pag;
    }

    public Estado_Cheque getEstado_cheque() {
        return estado_cheque;
    }

    public void setEstado_cheque(Estado_Cheque estado_cheque) {
        this.estado_cheque = estado_cheque;
    }

    public Cuentas_Base getCuentas_base() {
        return cuentas_base;
    }

    public void setCuentas_base(Cuentas_Base cuentas_base) {
        this.cuentas_base = cuentas_base;
    }
    
}
