package entidades;

import java.util.Date;
import java.util.Objects;

public class Visita {
    private Date fecha;
    private String diagnostico;
    private String direccion;
    private String tratamiento;
    private String indicacionesComplementarias;
    private String especialidadRemitida;

    public Visita(Date fecha, String diagnostico, String tratamiento, String indicacionesComplementarias, String especialidadRemitida, String direccion) {
        setFecha(fecha);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
        setIndicacionesComplementarias(indicacionesComplementarias);
        setEspecialidadRemitida(especialidadRemitida);
        setDireccion(direccion);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        Objects.requireNonNull(fecha, "Fecha no puede ser nula");

        Date hoy = new Date();
        if (fecha.after(hoy)) {
            throw new IllegalArgumentException("Fecha no puede ser futura");
        }
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("Diagn\u00F3stico no puede ser nulo o vac\u00EDo");
        }
        this.diagnostico = diagnostico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("Direcci\u00F3n no puede ser nula o vac\u00EDa");
        }
        this.direccion = direccion;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        if (tratamiento == null || tratamiento.trim().isEmpty()) {
            throw new IllegalArgumentException("Tratamiento no puede ser nulo o vac\u00EDo");
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
            throw new IllegalArgumentException("Especialidad remitida no puede ser vac\u00EDa");
        }
        this.especialidadRemitida = especialidadRemitida;
    }
}
