package util;

import java.time.LocalDate;
import java.time.Period;

public class CIUtil {

    public static boolean esCIValido(String ci) {
        boolean esValido = true;

        if (ci == null || !ci.matches("\\d{11}")) {
            esValido = false;
        } else {
            try {
                int anio = Integer.parseInt(ci.substring(0, 2));
                int mes = Integer.parseInt(ci.substring(2, 4));
                int dia = Integer.parseInt(ci.substring(4, 6));

                int anioActual = LocalDate.now().getYear() % 100;
                int siglo = (anio > anioActual) ? 1900 : 2000;
                int anioCompleto = siglo + anio;

                LocalDate.of(anioCompleto, mes, dia);  // Lanzará excepción si fecha inválida
            } catch (Exception e) {
                esValido = false;
            }
        }

        return esValido;
    }

    public static int obtenerEdadDesdeCI(String ci) {
        int edad = -1;
        boolean valido = true;

        if (ci == null || !ci.matches("\\d{11}")) {
            valido = false;
        } else {
            valido = esCIValido(ci);
        }

        if (valido) {
            try {
                int anio = Integer.parseInt(ci.substring(0, 2));
                int mes = Integer.parseInt(ci.substring(2, 4));
                int dia = Integer.parseInt(ci.substring(4, 6));

                int anioActual = LocalDate.now().getYear() % 100;
                int siglo = (anio > anioActual) ? 1900 : 2000;
                int anioCompleto = siglo + anio;

                LocalDate fechaNacimiento = LocalDate.of(anioCompleto, mes, dia);
                LocalDate hoy = LocalDate.now();
                edad = Period.between(fechaNacimiento, hoy).getYears();
            } catch (Exception e) {
                edad = -1;
            }
        }

        return edad;
    }
    
    public static boolean esMujer(String ci) {
        boolean esMujer = false;

        if (esCIValido(ci)) {
            try {
                int digitoSexo = Character.getNumericValue(ci.charAt(9));
                // dígito par = hombre, impar = mujer
                esMujer = (digitoSexo % 2 != 0);
            } catch (Exception e) {
                esMujer = false;
            }
        }

        return esMujer;
    }
}

