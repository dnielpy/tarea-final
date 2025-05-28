package entidades;

import java.util.Date;

public class RegistroVisita {
    private Date fecha;
    private String diagnostico;
    private String tratamiento;
    private String indicacionesComplementarias;
    private String especialidadRemitida;

    public RegistroVisita(Date fecha, String diagnostico, String tratamiento, String indicacionesComplementarias, String especialidadRemitida) {
        setFecha(fecha);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
        setIndicacionesComplementarias(indicacionesComplementarias);
        setEspecialidadRemitida(especialidadRemitida);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
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
        this.especialidadRemitida = especialidadRemitida;
    }
}
