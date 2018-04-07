package cl.ediciones.model;

public class Guia_Productos {
    
    private int cantidad;
    
    private Guia_Despacho guia_despacho;
    private Productos productos;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Guia_Despacho getGuia_despacho() {
        return guia_despacho;
    }

    public void setGuia_despacho(Guia_Despacho guia_despacho) {
        this.guia_despacho = guia_despacho;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
}
