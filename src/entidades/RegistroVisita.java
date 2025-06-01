package entidades;

public class RegistroVisita {
    private String fecha;
    private String diagnostico;
    private String tratamiento;
    private String indicacionesComplementarias;
    private String especialidadRemitida;

    public RegistroVisita(String fecha, String diagnostico, String tratamiento, String indicacionesComplementarias, String especialidadRemitida) {
        setFecha(fecha);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
        setIndicacionesComplementarias(indicacionesComplementarias);
        setEspecialidadRemitida(especialidadRemitida);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("Fecha no puede ser nula o vac�a");
        }
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("Diagn�stico no puede ser nulo o vac�o");
        }
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        if (tratamiento == null || tratamiento.trim().isEmpty()) {
            throw new IllegalArgumentException("Tratamiento no puede ser nulo o vac�o");
        }
        this.tratamiento = tratamiento;
    }

    public String getIndicacionesComplementarias() {
        return indicacionesComplementarias;
    }

    public void setIndicacionesComplementarias(String indicacionesComplementarias) {
        this.indicacionesComplementarias = indicacionesComplementarias;
    }

    public String getEspecialidadRemitida() {
        return especialidadRemitida;
    }

    public void setEspecialidadRemitida(String especialidadRemitida) {
        if (especialidadRemitida != null && especialidadRemitida.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidad remitida no puede ser vac�a");
        }
        this.especialidadRemitida = especialidadRemitida;
    }
}