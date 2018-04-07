package cl.ediciones.model;

public class Region {

   private int id_region;
   private String descripcion;
   private String n_romano;
   private int num_provincia;
   private int num_comuna;


    /**
     * @return the id_region
     */
    public int getId_region() {
        return id_region;
    }

    /**
     * @param id_region the id_region to set
     */
    public void setId_region(int id_region) {
        this.id_region = id_region;
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
    
     public String getN_romano() {
        return n_romano;
    }

    public void setN_romano(String n_romano) {
        this.n_romano = n_romano;
    }

    public int getNum_provincia() {
        return num_provincia;
    }

    public void setNum_provincia(int num_provincia) {
        this.num_provincia = num_provincia;
    }

    public int getNum_comuna() {
        return num_comuna;
    }

    public void setNum_comuna(int num_comuna) {
        this.num_comuna = num_comuna;
    }
}
