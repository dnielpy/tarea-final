package tarea;

import java.util.Date;

public class Mujer extends Paciente{
    private Date fechaUltimaRevision;
    private boolean embarazada;

    public Mujer(int historiaClinicaID, String nombre, int edad, boolean sexo, boolean vacunado) {
        super(historiaClinicaID, nombre, edad, sexo, vacunado);
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
}
