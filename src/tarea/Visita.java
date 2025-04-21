package tarea;

import java.util.Date;

public class Visita {
    private Date fecha;
    private String diagnostico;
    private String tratamiento;
    private String indicaciones;
    private boolean remitido;

    public Visita(Date fecha, String diagnostico, String tratamiento, String indicaciones, boolean remitido) {
        setFecha(fecha);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
        setIndicaciones(indicaciones);
        setRemitido(remitido);
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public boolean isRemitido() {
        return remitido;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public void setRemitido(boolean remitido) {
        this.remitido = remitido;
    }
}
