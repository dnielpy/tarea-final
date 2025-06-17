package entidades;

import excepciones.Excepciones.IllegalDateException;

import java.time.LocalDate;
import java.time.Period;

public class Mujer extends Paciente {
    private LocalDate fechaUltimaRevision;
    private boolean embarazada;

    public Mujer(int historiaClinicaID, String nombre, String primerApellido, String segundoApellido, String id,
                 String direccion, LocalDate fechaUltimaRevision, boolean embarazada) {
        super(historiaClinicaID, nombre, primerApellido, segundoApellido, id, direccion);
        setFechaUltimaRevision(fechaUltimaRevision);
        setEmbarazada(embarazada);
    }

    public LocalDate getFechaUltimaRevision() {
        return fechaUltimaRevision;
    }

    public void setFechaUltimaRevision(LocalDate fechaUltimaRevision) {
        if (fechaUltimaRevision != null) {
            if (fechaUltimaRevision.isAfter(LocalDate.now())) {
                throw new IllegalDateException("Fecha de revisi\u00F3n no puede ser futura");
            }

            if (fechaUltimaRevision.getYear() < 1900) {
                throw new IllegalDateException("Fecha de revisi\u00F3n no puede ser anterior a 1900");
            }
        }
        this.fechaUltimaRevision = fechaUltimaRevision;
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        int edad = this.getEdad();

        if (edad < 12 && embarazada) {
            throw new IllegalArgumentException("Paciente menor de 12 a\u00F1os no puede estar embarazada");
        }

        if (edad > 55 && embarazada) {
            throw new IllegalArgumentException("Paciente mayor de 55 a\u00F1os no puede estar embarazada");
        }

        this.embarazada = embarazada;
    }

    public boolean estaEnRiesgo() {
    	boolean enRiesgo;
    	if (fechaUltimaRevision == null) {
    		enRiesgo = true;
    	} else {
    		Period periodo = Period.between(fechaUltimaRevision, LocalDate.now());
    		enRiesgo = periodo.getYears() > 3;
    	}
    	return enRiesgo;
    }
}
