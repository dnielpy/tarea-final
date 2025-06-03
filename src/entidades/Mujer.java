package entidades;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Mujer extends Paciente {
    private String fechaUltimaRevision;
    private boolean embarazada;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Mujer(int historiaClinicaID, String nombre, String apellido, String id, String fechaUltimaRevision, boolean embarazada) {
        super(historiaClinicaID, nombre, apellido, id);
        setFechaUltimaRevision(fechaUltimaRevision);
        setEmbarazada(embarazada);
    }

    public String getFechaUltimaRevision() {
        return fechaUltimaRevision;
    }

    public void setFechaUltimaRevision(String fechaUltimaRevision) {
        if (fechaUltimaRevision != null && !fechaUltimaRevision.isEmpty()) {
            try {
                Date fecha = dateFormat.parse(fechaUltimaRevision);
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(fecha);
                if (cal.get(Calendar.YEAR) < 1900) {
                    throw new IllegalArgumentException("Fecha de revisión no puede ser anterior a 1900");
                }
                
                Calendar hoy = Calendar.getInstance();
                if (fecha.after(hoy.getTime())) {
                    throw new IllegalArgumentException("Fecha de revisión no puede ser futura");
                }
                
                this.fechaUltimaRevision = fechaUltimaRevision;
            } catch (ParseException e) {
                throw new IllegalArgumentException("Formato de fecha inválido. Use dd/MM/yyyy");
            }
        } else {
            this.fechaUltimaRevision = null;
        }
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        if (this.getEdad() < 12 && embarazada) {
            throw new IllegalArgumentException("Paciente menor de 12 años no puede estar embarazada");
        }
        if (this.getEdad() > 55 && embarazada) {
            throw new IllegalArgumentException("Paciente mayor de 55 años no puede estar embarazada");
        }
        this.embarazada = embarazada;
    }

    public boolean estaEnRiesgo() {
        boolean riesgo = false;
        
        if (fechaUltimaRevision == null || fechaUltimaRevision.isEmpty()) {
            riesgo = true;
        } else {
            try {
                Date fecha = dateFormat.parse(fechaUltimaRevision);
                Calendar fechaRevision = Calendar.getInstance();
                fechaRevision.setTime(fecha);
                fechaRevision.add(Calendar.MONTH, 3);

                Calendar hoy = Calendar.getInstance();
                if (hoy.after(fechaRevision)) {
                    riesgo = true;
                }
            } catch (ParseException e) {
                riesgo = true;
            }
        }
        
        return riesgo;
    }

    @Override
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        super.setNombre(nombre.trim());
    }
}