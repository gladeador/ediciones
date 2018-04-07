package cl.ediciones.model;

public class Protesto {
    
    private int id_protesto;
    
    private Estado_Protesto estado_protesto;
    private Cheque cheque;
    private Letra letra;
    private Clientes clientes;

    public int getId_protesto() {
        return id_protesto;
    }

    public void setId_protesto(int id_protesto) {
        this.id_protesto = id_protesto;
    }

    public Estado_Protesto getEstado_protesto() {
        return estado_protesto;
    }

    public void setEstado_protesto(Estado_Protesto estado_protesto) {
        this.estado_protesto = estado_protesto;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Letra getLetra() {
        return letra;
    }

    public void setLetra(Letra letra) {
        this.letra = letra;
    }

}
