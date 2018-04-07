package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Salidas {

    private int id_salida;
    private int cantidad;
    private java.util.Date fecha_salida;
    private String observaciones;
    private Tipo_Salida tipo_salida;
    private Productos productos;

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Tipo_Salida getTipo_salida() {
        return tipo_salida;
    }

    public void setTipo_salida(Tipo_Salida tipo_salida) {
        this.tipo_salida = tipo_salida;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public java.util.Date getFecha_salida() {
        return fecha_salida;
    }

//***********************************************************
    public String getFecha_salidaStr() {
        if (!(getFecha_salida() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_salida().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_salida(java.util.Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
