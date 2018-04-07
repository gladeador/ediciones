package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Cartola {
    
    private int id_cartola;	
    private java.util.Date fecha;
    private String descripcion;
    private String documento;
    private int cargos;	
    private int abonos;
    private int saldos;

    public int getId_cartola() {
        return id_cartola;
    }

    public void setId_cartola(int id_cartola) {
        this.id_cartola = id_cartola;
    }

    public java.util.Date getFecha() {
        return fecha;
    }

//***********************************************************
    public String getFechaStr() {
        if (!(getFecha() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getCargos() {
        return cargos;
    }

    public void setCargos(int cargos) {
        this.cargos = cargos;
    }

    public int getAbonos() {
        return abonos;
    }

    public void setAbonos(int abonos) {
        this.abonos = abonos;
    }

    public int getSaldos() {
        return saldos;
    }

    public void setSaldos(int saldos) {
        this.saldos = saldos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
