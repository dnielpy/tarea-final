package entidades.personal;

import excepciones.Excepciones.IllegalDateException;

import java.time.LocalDate;
import java.time.Period;

public class Mujer extends Paciente {
    private LocalDate fechaUltimaRevision;
    private boolean embarazada;

    // Constructor
    public Mujer(int historiaClinicaID, String nombre, String primerApellido, String segundoApellido, String id,
                 String direccion, LocalDate fechaUltimaRevision, boolean embarazada) {
        super(historiaClinicaID, nombre, primerApellido, segundoApellido, id, direccion);
        setFechaUltimaRevision(fechaUltimaRevision);
        setEmbarazada(embarazada);
    }

    // Fecha de la ultima prueba citologica
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

    // Embarazada
    
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

    // En riesgo
    
    @Override
    public boolean estaEnRiesgo() {
        boolean muchasEnfermedades = super.estaEnRiesgo();
        
        boolean citologiaVencida = false;
        if (fechaUltimaRevision != null) {
            LocalDate ahora = LocalDate.now();
            citologiaVencida = fechaUltimaRevision.plusYears(3).isBefore(ahora);
        } else {
            citologiaVencida = true; // Nunca se la ha hecho - en riesgo
        }

        return muchasEnfermedades || citologiaVencida;
    }
}
