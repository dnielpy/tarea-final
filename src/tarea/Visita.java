package tarea;

import java.util.Date;

public class Visita {

    private Date fecha;
    private String diagnostico;
    private String tratamiento;
    private String indicaciones;
    private boolean remitido;

    public Visita(Date fecha, String diagnostico, String tratamiento, String indicaciones, boolean remitido) {
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.indicaciones = indicaciones;
        this.remitido = remitido;
    }
}
