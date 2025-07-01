package util;

import java.text.Normalizer;

public final class UtilString {

    private UtilString() {
        // Clase utilitaria: no instanciable
    }

    /** Convierte de forma segura un valor primitivo (o wrapper) a String. */
    public static String toString(Object valor) {
        String resultado = "";
        if (valor instanceof Number || valor instanceof Boolean || valor instanceof Character) {
            resultado = String.valueOf(valor);
        }
        return resultado;
    }

    /** Capitaliza la primera letra (mayúscula), minuscula el resto. */
    public static String capitalizar(String texto) {
        String resultado = "";
        if (texto != null && !texto.isEmpty()) {
            String primera = texto.substring(0, 1).toUpperCase();
            String resto = texto.substring(1).toLowerCase();
            resultado = primera + resto;
        }
        return resultado;
    }
    
    /** Capitaliza nombres compuestos */
    public static String capitalizarNombre(String texto) {
        String resultado = "";
        if (texto != null && !texto.trim().isEmpty()) {
            String[] palabras = texto.trim().toLowerCase().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String palabra : palabras) {
                if (!palabra.isEmpty()) {
                    sb.append(capitalizar(palabra)).append(" ");
                }
            }
            resultado = sb.toString().trim();
        }
        return resultado;
    }


    /** Elimina espacios duplicados entre palabras. */
    public static String limpiarEspacios(String texto) {
        String resultado = "";
        if (texto != null) {
            resultado = texto.trim().replaceAll("\\s{2,}", " ");
        }
        return resultado;
    }

    /** Trunca el texto y añade "..." si excede la longitud dada. */
    public static String truncar(String texto, int max) {
        String resultado = "";
        if (texto != null) {
            if (texto.length() <= max) {
                resultado = texto;
            } else if (max > 3) {
                resultado = texto.substring(0, max - 3) + "...";
            } else {
                resultado = texto.substring(0, max);
            }
        }
        return resultado;
    }

    /** Normaliza el texto: sin acentos y en minúsculas. */
    public static String normalizar(String texto) {
        String resultado = "";
        if (texto != null) {
            String sinAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD);
            resultado = sinAcentos.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        }
        return resultado;
    }

    /** Compara ignorando mayúsculas y acentos. */
    public static boolean igualesIgnorandoAcentos(String a, String b) {
        boolean iguales = false;
        if (a != null && b != null) {
            iguales = normalizar(a).equals(normalizar(b));
        }
        return iguales;
    }

    /** Devuelve true si el texto solo contiene letras y espacios. */
    public static boolean soloLetras(String texto) {
        boolean resultado = false;
        if (texto != null && !texto.trim().isEmpty()) {
            resultado = texto.matches("[\\p{L}\\s]+");
        }
        return resultado;
    }

    /** Elimina caracteres no alfabéticos ni espacios. */
    public static String limpiarSoloLetras(String texto) {
        String resultado = "";
        if (texto != null) {
            resultado = texto.replaceAll("[^\\p{L}\\s]", "");
        }
        return resultado;
    }
}

