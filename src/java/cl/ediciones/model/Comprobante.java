package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Comprobante {

    private int id_comprobante;
    private String num_comprobante;
    private java.util.Date fecha_comprobante;
    private String descripcion;
    private int num_factura;
    private int monto;
    private String debe_haber;
    private String descripcion_2;
    
    private Factura factura;
    private Gastos gastos;
    private Cheque cheque;
    private Cheque_Pago cheque_pago;
    private Cuota cuota;
    private Letra letra;
    private Proveedores proveedor;
    private Cuentas_Base cuenta_base;

    public int getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.id_comprobante = id_comprobante;
    }

    public String getNum_comprobante() {
        return num_comprobante;
    }

    public void setNum_comprobante(String num_comprobante) {
        this.num_comprobante = num_comprobante;
    }

    public java.util.Date getFecha_comprobante() {
        return fecha_comprobante;
    }

//***********************************************************
    public String getFecha_comprobanteStr() {
        if (!(getFecha_comprobante() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_comprobante().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_comprobante(java.util.Date fecha_comprobante) {
        this.fecha_comprobante = fecha_comprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Gastos getGastos() {
        return gastos;
    }

    public void setGastos(Gastos gastos) {
        this.gastos = gastos;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public Cuota getCuota() {
        return cuota;
    }

    public void setCuota(Cuota cuota) {
        this.cuota = cuota;
    }

    public Letra getLetra() {
        return letra;
    }

    public void setLetra(Letra letra) {
        this.letra = letra;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Cheque_Pago getCheque_pago() {
        return cheque_pago;
    }

    public void setCheque_pago(Cheque_Pago cheque_pago) {
        this.cheque_pago = cheque_pago;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getDescripcion_2() {
        return descripcion_2;
    }

    public void setDescripcion_2(String descripcion_2) {
        this.descripcion_2 = descripcion_2;
    }

    public Cuentas_Base getCuenta_base() {
        return cuenta_base;
    }

    public void setCuenta_base(Cuentas_Base cuenta_base) {
        this.cuenta_base = cuenta_base;
    }

    public String getDebe_haber() {
        return debe_haber;
    }

    public void setDebe_haber(String debe_haber) {
        this.debe_haber = debe_haber;
    }
}
