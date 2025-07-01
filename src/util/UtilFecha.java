package util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class UtilFecha {

    private static final Locale LOCALE_ES = new Locale("es", "ES");

    // Convierte un LocalDate a String con formato corto (ej. 14/ene/25)
    public static String formatearCorto(LocalDate fecha) {
    	 String resultado = "";
         if (fecha != null) {
             DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MMM/yy", LOCALE_ES);
             resultado = fecha.format(formato).toLowerCase(LOCALE_ES);
         }
         return resultado;
    }
  
    // Convierte un LocalDate a String en formato largo (ej. 21 de enero de 2025)
    public static String formatearLargoEsp(LocalDate fecha) {
        String resultado = "";
        if (fecha != null) {
            int dia = fecha.getDayOfMonth();
            String mes = fecha.getMonth().getDisplayName(TextStyle.FULL, LOCALE_ES);
            int anio = fecha.getYear();
            resultado = String.format("%d de %s de %d", dia, mes, anio);
        }
        return resultado;
    }

	// Convierte un LocalDate a java.util.Date
    public static Date convertirALegacyDate(LocalDate fecha) {
        Date resultado = null;
        if (fecha != null) {
            resultado = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return resultado;
    }

 
	// Convierte un java.util.Date a LocalDate
    public static LocalDate convertirALocalDate(Date fecha) {
        LocalDate resultado = null;
        if (fecha != null) {
            resultado = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return resultado;
    }
    
    public static boolean esFechaValida(String fecha) {
        boolean esValida = true;

        if (fecha == null || !fecha.matches("\\d{6}")) {
            esValida = false;
        } else {
            try {
                int anio = Integer.parseInt(fecha.substring(0, 2));
                int mes = Integer.parseInt(fecha.substring(2, 4));
                int dia = Integer.parseInt(fecha.substring(4, 6));

                int anioActual = LocalDate.now().getYear() % 100;
                int siglo = (anio > anioActual) ? 1900 : 2000;
                int anioCompleto = siglo + anio;

                LocalDate.of(anioCompleto, mes, dia); // Validación real
            } catch (Exception e) {
                esValida = false;
            }
        }

        return esValida;
    }

    public static int obtenerAniosDesdeString(String fecha) {
        int anios = -1;
        boolean esValida = true;

        if (fecha == null || !fecha.matches("\\d{6}")) {
            esValida = false;
        } else {
            esValida = esFechaValida(fecha);
        }

        if (esValida) {
            try {
                int anio = Integer.parseInt(fecha.substring(0, 2));
                int mes = Integer.parseInt(fecha.substring(2, 4));
                int dia = Integer.parseInt(fecha.substring(4, 6));

                int anioActual = LocalDate.now().getYear() % 100;
                int siglo = (anio > anioActual) ? 1900 : 2000;
                int anioCompleto = siglo + anio;

                LocalDate fechaNacimiento = LocalDate.of(anioCompleto, mes, dia);
                LocalDate hoy = LocalDate.now();
                anios = Period.between(fechaNacimiento, hoy).getYears();
            } catch (Exception e) {
            	anios = -1;
            }
        }

        return anios;
    }
}


