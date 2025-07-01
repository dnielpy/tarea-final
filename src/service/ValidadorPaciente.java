package service;

import excepciones.*;

import java.time.LocalDate;
import java.util.List;

import util.CIUtil;

public class ValidadorPaciente {

    public static void validarPaciente(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String ci,
        String direccion,
        boolean esMujer,
        Boolean embarazada,
        LocalDate fechaUltimaRevision,
        List<String> enfermedades,
        List<String> vacunas
    ) {
        // Validaciones básicas
        ValidacionesComunes.validarNombre(nombre);
        ValidacionesComunes.validarPrimerApellido(primerApellido);
        ValidacionesComunes.validarSegundoApellido(segundoApellido);
        ValidacionesComunes.validarCI(ci);
        ValidacionesComunes.validarTextoPlano(direccion, "Dirección");

        // Validaciones específicas para mujeres
        if (esMujer) {
            if (embarazada != null && !CIUtil.esMujer(ci) && embarazada) {
                throw new EmbarazoInvalidoException("Un paciente masculino no puede estar embarazado.");
            }

            if (fechaUltimaRevision != null && fechaUltimaRevision.isAfter(LocalDate.now())) {
                throw new FechaInvalidaException("La fecha de la última revisión no puede ser futura.");
            }
        }
    }

    public static void validarEdicionPaciente(
    	    String nombre,
    	    String primerApellido,
    	    String segundoApellido,
    	    String ci,
    	    String direccion,
    	    Boolean embarazada,
    	    LocalDate fechaUltimaRevision
    	) {
    	    if (nombre != null) {
    	        ValidacionesComunes.validarNombre(nombre);
    	    }
    	    if (primerApellido != null) {
    	        ValidacionesComunes.validarPrimerApellido(primerApellido);
    	    }
    	    if (segundoApellido != null) {
    	        ValidacionesComunes.validarSegundoApellido(segundoApellido);
    	    }
    	    if (ci != null) {
    	        ValidacionesComunes.validarCI(ci);
    	    }
    	    if (direccion != null) {
    	        ValidacionesComunes.validarTextoPlano(direccion, "Dirección");
    	    }

    	    // Solo validar embarazo y fecha si es mujer (extrae eso del CI)
    	    if (embarazada != null || fechaUltimaRevision != null) {
    	        boolean esMujer = CIUtil.esMujer(ci);

    	        if (embarazada != null && !esMujer && embarazada) {
    	            throw new EmbarazoInvalidoException("Un paciente masculino no puede estar embarazado.");
    	        }

    	        if (fechaUltimaRevision != null && fechaUltimaRevision.isAfter(LocalDate.now())) {
    	            throw new FechaInvalidaException("La fecha de la última revisión no puede ser futura.");
    	        }
    	    }
    	}

}