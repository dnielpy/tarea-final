package entidades.registros;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import excepciones.Excepciones.IllegalAddressException;
import excepciones.Excepciones.IllegalNameException;

public class Visita {
    private int id;
    private int pacienteHistoriaClinicaID;
    private LocalDate fecha;
    private String diagnostico;
    private String direccion;
    private String tratamiento;
    private Analisis analisis;
    private String especialidadRemitida;

    // Constructor
    
    public Visita(int id, int pacienteHistoriaClinicaID, LocalDate fecha, String diagnostico, 
    		String tratamiento, Analisis analisis,
            String especialidadRemitida, String direccion) {
        setId(id);
        setPacienteHistoriaClinicaID(pacienteHistoriaClinicaID);
        setFecha(fecha);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
        setAnalisis(analisis);
        setEspecialidadRemitida(especialidadRemitida);
        setDireccion(direccion);
    }

    // Id
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID debe ser mayor que cero");
        }
        this.id = id;
    }

    // Id de Historia Clinica del paciente
    
    public int getPacienteHistoriaClinicaID() {
        return pacienteHistoriaClinicaID;
    }

    public void setPacienteHistoriaClinicaID(int pacienteHistoriaClinicaID) {
        this.pacienteHistoriaClinicaID = pacienteHistoriaClinicaID;
    }

    // Fecha de orientado
    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        Objects.requireNonNull(fecha, "Fecha no puede ser nula");

        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha no puede ser futura");
        }
        this.fecha = fecha;
    }
    
    public String getFechaFormateada() {
        if (fecha == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return fecha.format(formatter);
    }
    
    // Diagnostico

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new IllegalNameException("Diagnóstico no puede ser nulo o vacío");
        }
        this.diagnostico = diagnostico;
    }

    // Direccion
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalAddressException("Dirección no puede ser nula o vacía");
        }
        this.direccion = direccion;
    }

    // Tratamiento
    
    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        if (tratamiento == null || tratamiento.trim().isEmpty()) {
            throw new IllegalArgumentException("Tratamiento no puede ser nulo o vac\u00EDo");
        }
        this.tratamiento = tratamiento;
    }

    // Analisis
    
    public Analisis getAnalisis() {
        return analisis;
    }

    public void setAnalisis(Analisis analisis) {
        this.analisis = analisis;
    }

    // Especialidad remitida
    
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
