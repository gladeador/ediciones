package cl.ediciones.model;

public class Productos {

    private int id_productos;
    private String descripcion;
    private String desc_corta;
    private int valor_neto;

    public int getId_productos() {
        return id_productos;
    }

    public void setId_productos(int id_productos) {
        this.id_productos = id_productos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDesc_corta() {
        return desc_corta;
    }

    public void setDesc_corta(String desc_corta) {
        this.desc_corta = desc_corta;
    }

    public int getValor_neto() {
        return valor_neto;
    }

    public void setValor_neto(int valor_neto) {
        this.valor_neto = valor_neto;
    }
}
