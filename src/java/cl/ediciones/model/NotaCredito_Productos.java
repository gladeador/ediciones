package cl.ediciones.model;

public class NotaCredito_Productos {
    
   private int cantidad;
   private NotaCredito notacredito;
   private Productos productos;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public NotaCredito getNotacredito() {
        return notacredito;
    }

    public void setNotacredito(NotaCredito notacredito) {
        this.notacredito = notacredito;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
  
}
