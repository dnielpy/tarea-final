package entidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Mujer extends Paciente {
    private Date fechaUltimaRevision;
    private boolean embarazada;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Mujer(int historiaClinicaID, String nombre, String primerApellido, String segundoApellido, String id, Date fechaUltimaRevision, boolean embarazada) {
        super(historiaClinicaID, nombre, primerApellido, segundoApellido, id);
        setFechaUltimaRevision(fechaUltimaRevision);
        setEmbarazada(embarazada);
    }

    public Date getFechaUltimaRevision() {
        return fechaUltimaRevision;
    }

    public void setFechaUltimaRevision(Date fechaUltimaRevision) {
        if (fechaUltimaRevision != null) {
            Calendar calendario = Calendar.getInstance();         
            if (fechaUltimaRevision.after(calendario.getTime())) {
			    throw new IllegalArgumentException("Fecha de revisi\u00F3n no puede ser futura");
			}
            
            calendario.setTime(fechaUltimaRevision);
			if (calendario.get(Calendar.YEAR) < 1900) {
			    throw new IllegalArgumentException("Fecha de revisi\u00F3n no puede ser anterior a 1900");
			}		
        }
        this.fechaUltimaRevision = fechaUltimaRevision;
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        if (this.getEdad() < 12 && embarazada) {
            throw new IllegalArgumentException("Paciente menor de 12 a\u00F1os no puede estar embarazada");
        }
        if (this.getEdad() > 55 && embarazada) {
            throw new IllegalArgumentException("Paciente mayor de 55 a\u00F1os no puede estar embarazada");
        }
        this.embarazada = embarazada;
    }

    public boolean estaEnRiesgo() {
        boolean riesgo = false;
        
        if (fechaUltimaRevision == null) {
        	riesgo = true;
        } else {
        	Date hoy = new Date();
        	long milisegundos = hoy.getTime() - fechaUltimaRevision.getTime();
        	long anios = TimeUnit.MILLISECONDS.toDays(milisegundos) / 365l;

			if (anios > 3) {
			    riesgo = true;
			}
        }
        
        return riesgo;
    }
}