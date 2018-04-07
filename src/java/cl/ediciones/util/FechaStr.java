package cl.ediciones.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;

public class FechaStr {
    
    //Paso una fecha como java.sql.Date y se transforma a String
    public static String dateToString(java.sql.Date fecha){
        java.sql.Date fechaPaso = new java.sql.Date(fecha.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaPaso);
    }
    
    //Le paso un String y lo transforma a java.sql.Date
    public static java.sql.Date stringToDate(String fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date fechaPaso=null;;
        try {
            fechaPaso = new java.sql.Date(sdf.parse(fecha).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fechaPaso;
    }
    
    public java.sql.Date fechaMas(java.sql.Date fch, int dias){
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        java.sql.Date fecha = new java.sql.Date(cal.getTimeInMillis());
        return fecha;
    }
    
    
    long timeStamp;
    private static org.apache.log4j.Logger log = Logger.getLogger(FechaStr.class);

    public FechaStr() {
    }

    public FechaStr(long seg) {
        timeStamp = seg;
    }

    public String getDateString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String strFecha = "";
        java.util.Date fecha = new java.util.Date(this.timeStamp);
        strFecha = df.format(fecha);
        return strFecha;
    }

    public String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        String strFecha = "";
        java.util.Date fecha = new java.util.Date(this.timeStamp);
        strFecha = df.format(fecha);
        return strFecha;
    }

    public static long StringToLongDate(String strFecha) {
        if (strFecha.length() == 10) {
            strFecha += " 00:00:00";
        } else if (strFecha.length() == 16) {
            strFecha += ":00";
        }
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        long lFecha = 0;
        try {
            java.util.Date fecha = df.parse(strFecha);
            lFecha = fecha.getTime();
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return lFecha;
    }

    public static java.sql.Date SumaFecha(String strFecha, int dias) {
        java.sql.Date fechaSQL;
        if (strFecha.equals("")) {
            fechaSQL = null;
        } else {
            long longDate = FechaStr.StringToLongDate(strFecha.trim());
            fechaSQL = new java.sql.Date(longDate);
        }
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fechaSQL.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static Date StringToDate(String strFecha) {
        java.util.Date fecha = null;
        if (strFecha.length() == 10) {
            strFecha += " 00:00:00";
        } else if (strFecha.length() == 16) {
            strFecha += ":00";
        } else if (strFecha.length() == 21) {
            strFecha = strFecha.substring(0, 19);
        }
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        long lFecha = 0;
        if (strFecha != null || !strFecha.equals("")) {
            try {
                fecha = df.parse(strFecha);
                lFecha = fecha.getTime();
            } catch (ParseException e) {
                log.error(e.getMessage());
            }
        }
        return fecha;
    }

    public static String LongDateToString(long lFecha) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String strFecha = "";
        java.util.Date fecha = new java.util.Date(lFecha);
        strFecha = df.format(fecha);
        return strFecha;
    }

    public static int fechaDateInt(Date fecha, String tipo) {
        int dev = 0;
        Calendar cal = Calendar.getInstance();
        if (fecha != null) {
            cal.setTime(fecha);
        }
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = (cal.get(Calendar.MONTH)) + 1;
        int ano = cal.get(Calendar.YEAR);
        if (tipo.equals("d") || tipo.equals("D")) {
            dev = dia;
        } else if (tipo.equals("m") || tipo.equals("M")) {
            dev = mes;
        } else if (tipo.equals("a") || tipo.equals("A")) {
            dev = ano;
        }
        return dev;
    }

    public static String mes2Str(int mes) {
        String dev ="";
        if(mes==1){
            dev="Enero";
        }else if (mes==2) {
            dev="Febrero";
        }else if (mes==3) {
            dev="Marzo";
        }else if (mes==4) {
            dev="Abril";
        }else if (mes==5) {
            dev="Mayo";
        }else if (mes==6) {
            dev="Junio";
        }else if (mes==7) {
            dev="Julio";
        }else if (mes==8) {
            dev="Agosto";
        }else if (mes==9) {
            dev="Septiembre";
        }else if (mes==10) {
            dev="Octubre";
        }else if (mes==11) {
            dev="Noviembre";
        }else if (mes==12) {
            dev="Diciembre";
        }
        return dev;
    }
    
}
