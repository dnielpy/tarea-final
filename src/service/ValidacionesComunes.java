package service;

import excepciones.*;

import java.time.LocalDate;
import util.CIUtil;

public class ValidacionesComunes {

    public static void validarNombre(String nombre) throws NombreIncorrectoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreIncorrectoException("El nombre no puede estar vacío.");
        }
        if (!nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$")) {
            throw new NombreIncorrectoException("El nombre contiene caracteres inválidos o está mal escrito.");
        }
    }

    public static void validarPrimerApellido(String apellido) throws ApellidoIncorrectoException {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new ApellidoIncorrectoException("El primer apellido no puede estar vacío.");
        }
        if (!apellido.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$")) {
            throw new ApellidoIncorrectoException("El primer apellido es inválido.");
        }
    }

    public static void validarSegundoApellido(String apellido) throws ApellidoIncorrectoException {
        if (apellido != null && !apellido.trim().isEmpty()) {
            if (!apellido.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$")) {
                throw new ApellidoIncorrectoException("El segundo apellido es inválido.");
            }
        }
        // Si es nulo o vacío, lo consideramos opcional
    }

    public static void validarCI(String ci) throws CIInvalidoException {
        if (ci == null || !ci.matches("^\\d{11}$")) {
            throw new CIInvalidoException("El carné de identidad debe tener exactamente 11 dígitos.");
        } else if (!CIUtil.esCIValido(ci)) {
        	throw new CIInvalidoException("El carné de identidad introducido no es correcto.");
        }
    }

    public static void validarFecha(LocalDate fecha, String campo) throws FechaInvalidaException {
        if (fecha == null) {
            throw new FechaInvalidaException("El campo '" + campo + "' no puede estar vacío.");
        }
        if (fecha.isAfter(LocalDate.now())) {
            throw new FechaInvalidaException("El campo '" + campo + "' no puede ser una fecha futura.");
        }
        if (fecha.isBefore(LocalDate.now().minusYears(150))) {
            throw new FechaInvalidaException("El campo '" + campo + "' es demasiado antiguo para ser válido.");
        }
    }

    public static void validarTextoPlano(String texto, String campo) throws DatoInvalidoException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new DatoInvalidoException("El campo '" + campo + "' no puede estar vacío.");
        }
        if (texto.length() > 200) {
            throw new DatoInvalidoException("El campo '" + campo + "' no debe superar los 200 caracteres.");
        }
    }
    
}


