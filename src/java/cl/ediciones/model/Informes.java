package cl.ediciones.model;

public class Informes {
    
    private int rut;
    private int suma_total;
    private int suma_neto;
    private int rkn;
    private int id_region;
    private String descripcion;
    private String mes_inicio;
    private String mes_fin;
    private String ano;

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public int getSuma_total() {
        return suma_total;
    }

    public void setSuma_total(int suma_total) {
        this.suma_total = suma_total;
    }

    public int getSuma_neto() {
        return suma_neto;
    }

    public void setSuma_neto(int suma_neto) {
        this.suma_neto = suma_neto;
    }

    public int getRkn() {
        return rkn;
    }

    public void setRkn(int rkn) {
        this.rkn = rkn;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMes_inicio() {
        return mes_inicio;
    }

    public void setMes_inicio(String mes_inicio) {
        this.mes_inicio = mes_inicio;
    }

    public String getMes_fin() {
        return mes_fin;
    }

    public void setMes_fin(String mes_fin) {
        this.mes_fin = mes_fin;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

}
