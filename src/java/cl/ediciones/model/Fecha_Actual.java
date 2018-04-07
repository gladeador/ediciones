package cl.ediciones.model;

import cl.ediciones.util.FechaStr;

public class Fecha_Actual {
    
    private java.util.Date fecha_actual;
    private String mes;
    private String ano;

    public java.util.Date getFecha_actual() {
        return fecha_actual;
    }

//***********************************************************
    public String getFecha_actualStr() {
        if (!(getFecha_actual() == null)) {
            return FechaStr.dateToString(new java.sql.Date(getFecha_actual().getTime()));
        } else {
            String x = null;
            return x;
        }
    }

    public void setFecha_actual(java.util.Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
