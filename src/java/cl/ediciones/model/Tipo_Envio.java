package cl.ediciones.model;

public class Tipo_Envio {

   private int id_tipo_envio;
   private String descripcion;

    /**
     * @return the id_tipo_envio
     */
    public int getId_tipo_envio() {
        return id_tipo_envio;
    }

    /**
     * @param id_tipo_envio the id_tipo_envio to set
     */
    public void setId_tipo_envio(int id_tipo_envio) {
        this.id_tipo_envio = id_tipo_envio;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
