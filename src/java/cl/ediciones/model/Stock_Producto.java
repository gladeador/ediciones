package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Stock_Producto {

    private int id_stock;
    private String observaciones;
    private int stock;
    private float costo_producto;
    private float tipo_de_cambio;
//    private float tipo_de_cambio2;
    private float porsentaje_gastos;
    private float costo_gastos;
    private java.util.Date fecha_ingreso;
    private int num_guia_despacho;
    private int num_factura;
    private String estado;
    private float neto;
    private float iva;
    private float total;
    
    private Productos productos;
    private Proveedores proveedores;
    private Tipo_Moneda tipo_moneda;
    private Cuentas_Base cuentas_base;
    private Tipo_Documento tipo_documento;

    public float getTipo_de_cambio() {
        return tipo_de_cambio;
    }

    public void setTipo_de_cambio(float tipo_de_cambio) {
        this.tipo_de_cambio = tipo_de_cambio;
    }
    
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public float getCosto_producto() {
        return costo_producto;
    }

    public void setCosto_producto(float costo_producto) {
        this.costo_producto = costo_producto;
    }

//    public int getTipo_de_cambio() {
//        return tipo_de_cambio;
//    }
//
//    public void setTipo_de_cambio(int tipo_de_cambio) {
//        this.tipo_de_cambio = tipo_de_cambio;
//    }

    public float getPorsentaje_gastos() {
        return porsentaje_gastos;
    }

    public void setPorsentaje_gastos(float porsentaje_gastos) {
        this.porsentaje_gastos = porsentaje_gastos;
    }

    public float getCosto_gastos() {
        return costo_gastos;
    }

    public void setCosto_gastos(float costo_gastos) {
        this.costo_gastos = costo_gastos;
    }

    public java.util.Date getFecha_ingreso() {
        return fecha_ingreso;
    }

//***********************************************************
    public String getFecha_ingresoStr() {
        if (!(getFecha_ingreso() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_ingreso().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_ingreso(java.util.Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getNum_guia_despacho() {
        return num_guia_despacho;
    }

    public void setNum_guia_despacho(int num_guia_despacho) {
        this.num_guia_despacho = num_guia_despacho;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Tipo_Moneda getTipo_moneda() {
        return tipo_moneda;
    }

    public void setTipo_moneda(Tipo_Moneda tipo_moneda) {
        this.tipo_moneda = tipo_moneda;
    }

    public float getNeto() {
        return neto;
    }

    public void setNeto(float neto) {
        this.neto = neto;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Cuentas_Base getCuentas_base() {
        return cuentas_base;
    }

    public void setCuentas_base(Cuentas_Base cuentas_base) {
        this.cuentas_base = cuentas_base;
    }

    public Tipo_Documento getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Tipo_Documento tipo_documento) {
        this.tipo_documento = tipo_documento;
    }
}
