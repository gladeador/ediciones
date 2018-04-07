package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Cheque_Pago {
    
    private int id_cheque_pago;
    private int num_cheque_pago;
    private int monto;
    private int num_factura_compra;
    private int id_gastos;
    private java.util.Date fecha_recepcion_documento;
    private java.util.Date fecha_vencimiento;
    private int monto_cancelado;
    private java.util.Date fecha_cancela;
    
    private Proveedores proveedores;
    private Banco banco;
    private Cuentas_Base cuentas_base;

    public int getNum_cheque_pago() {
        return num_cheque_pago;
    }

    public void setNum_cheque_pago(int num_cheque_pago) {
        this.num_cheque_pago = num_cheque_pago;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
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

    public int getId_cheque_pago() {
        return id_cheque_pago;
    }

    public void setId_cheque_pago(int id_cheque_pago) {
        this.id_cheque_pago = id_cheque_pago;
    }

    public int getNum_factura_compra() {
        return num_factura_compra;
    }

    public void setNum_factura_compra(int num_factura_compra) {
        this.num_factura_compra = num_factura_compra;
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

    public int getId_gastos() {
        return id_gastos;
    }

    public void setId_gastos(int id_gastos) {
        this.id_gastos = id_gastos;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public Cuentas_Base getCuentas_base() {
        return cuentas_base;
    }

    public void setCuentas_base(Cuentas_Base cuentas_base) {
        this.cuentas_base = cuentas_base;
    }

}
