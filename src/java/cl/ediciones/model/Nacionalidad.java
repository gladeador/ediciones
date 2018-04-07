package cl.ediciones.model;

public class Nacionalidad {

   private int id_nacionalidad;
   private String descripcion;

    /**
     * @return the id_nacionalidad
     */
    public int getId_nacionalidad() {
        return id_nacionalidad;
    }

    /**
     * @param id_nacionalidad the id_nacionalidad to set
     */
    public void setId_nacionalidad(int id_nacionalidad) {
        this.id_nacionalidad = id_nacionalidad;
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
