package cl.ediciones.model;

public class Cuentas_Base {

    private String num_cuenta;
    private String descripcion;
    private String tipo;
    private String cuentas;
    private String cuenta_padre;
    private String finalS;

    public String getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCuentas() {
        return cuentas;
    }

    public void setCuentas(String cuentas) {
        this.cuentas = cuentas;
    }

    public String getCuenta_padre() {
        return cuenta_padre;
    }

    public void setCuenta_padre(String cuenta_padre) {
        this.cuenta_padre = cuenta_padre;
    }

    public String getFinalS() {
        return finalS;
    }

    public void setFinalS(String finalS) {
        this.finalS = finalS;
    }
}
