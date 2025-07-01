package util;

import java.time.LocalDate;
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
}

