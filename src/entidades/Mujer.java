package entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Mujer extends Paciente{
    private Date fechaUltimaRevision;
    private boolean embarazada;

    public Mujer(int historiaClinicaID, String nombre, int edad, ArrayList<String> vacucnacion, Date fechaUltimaRevision, boolean embarazada) {
        super(historiaClinicaID, nombre, edad, vacucnacion);
        setFechaUltimaRevision(fechaUltimaRevision);
        setEmbarazada(embarazada);
    }

    public Date getFechaUltimaRevision() {
        return fechaUltimaRevision;
    }

    public void setFechaUltimaRevision(Date fechaUltimaRevision) {
        this.fechaUltimaRevision = fechaUltimaRevision;
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        this.embarazada = embarazada;
    }

    public boolean estaEnRiesgo() {
        boolean riesgo = false;
        if (fechaUltimaRevision == null) {
            riesgo = true;
        }
        Calendar fechaRevision = Calendar.getInstance();
        fechaRevision.setTime(fechaUltimaRevision);

        Calendar hoy = Calendar.getInstance();
        fechaRevision.add(Calendar.MONTH, 3);

        if(hoy.after(fechaRevision)){
            riesgo = true;
        }
        return riesgo;
    }
}
