package entidades.registros;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import util.ConstantesEspecialidades;

public class Visita {
    private int id;
    private int pacienteHistoriaClinicaID;
    private LocalDate fecha;
    private String diagnostico;
    private String direccion;
    private String tratamiento;
    private List<Analisis> analisis;
    private List<String> especialidadesRemitidas;

    // Constructor
    public Visita(int id, int pacienteHistoriaClinicaID, LocalDate fecha, String diagnostico,
            String tratamiento, List<Analisis> analisis, List<String> especialidadesRemitidas, String direccion) {
        setId(id);
        setPacienteHistoriaClinicaID(pacienteHistoriaClinicaID);
        setFecha(fecha);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
        setAnalisis(analisis);
        setEspecialidadesRemitidas(especialidadesRemitidas);
        setDireccion(direccion);
    }

    public Visita(int id, int pacienteHistoriaClinicaID, LocalDate fecha, String diagnostico,
            String tratamiento, String direccion) {
        setId(id);
        setPacienteHistoriaClinicaID(pacienteHistoriaClinicaID);
        setFecha(fecha);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
        setDireccion(direccion);
    }

    // ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID debe ser mayor que cero");
        }
        this.id = id;
    }

    // ID de Historia Clínica
    public int getPacienteHistoriaClinicaID() {
        return pacienteHistoriaClinicaID;
    }

    public void setPacienteHistoriaClinicaID(int pacienteHistoriaClinicaID) {
        if (pacienteHistoriaClinicaID <= 0) {
            throw new IllegalArgumentException("ID de historia cl\u00ednica debe ser mayor que cero");
        }
        this.pacienteHistoriaClinicaID = pacienteHistoriaClinicaID;
    }

    // Fecha
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

    // Diagnóstico
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("Diagn\u00f3stico no puede ser nulo o vac\u00edo");
        }
        this.diagnostico = diagnostico.trim();
    }

    // Dirección
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("Direcci\u00f3n no puede ser nula o vac\u00eda");
        }
        this.direccion = direccion.trim();
    }

    // Tratamiento
    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        if (tratamiento == null || tratamiento.trim().isEmpty()) {
            throw new IllegalArgumentException("Tratamiento no puede ser nulo o vac\u00edo");
        }
        this.tratamiento = tratamiento.trim();
    }

    // Análisis
    public List<Analisis> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(List<Analisis> analisis) {
        if (analisis == null) {
            throw new IllegalArgumentException("La lista de an\u00e1lisis no puede ser nula");
        }
        this.analisis = new ArrayList<>(analisis); // copia defensiva
    }

    public String getResumenAnalisis() {
        Set<String> tipos = new LinkedHashSet<>();

        if (this.analisis == null || this.analisis.isEmpty()) {
            tipos.add("Sin análisis");
        } else {
            for (Analisis a : this.analisis) {
                tipos.add(a.getTipoDeAnalisis());
            }
        }

        String resumen = String.join(", ", tipos);
        return resumen;
    }

    // Especialidad remitida
    public List<String> getEspecialidadesRemitidas() {
        if (especialidadesRemitidas == null) {
            especialidadesRemitidas = new ArrayList<>();
        }
        return especialidadesRemitidas;
    }

    public void setEspecialidadesRemitidas(List<String> especialidadesRemitidas) {
        if (especialidadesRemitidas == null) {
            this.especialidadesRemitidas = new ArrayList<>();
        } else {
            this.especialidadesRemitidas = especialidadesRemitidas;
        }
    }

    public void agregarEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidad no puede ser nula o vacía");
        }
        if (!ConstantesEspecialidades.ESPECIALIDADES_REMITIDAS.contains(especialidad.trim())) {
            throw new IllegalArgumentException("Especialidad remitida no válida: " + especialidad);
        }
        if (especialidadesRemitidas == null) {
            especialidadesRemitidas = new ArrayList<>();
        }
        if (!especialidadesRemitidas.contains(especialidad.trim())) {
            especialidadesRemitidas.add(especialidad.trim());
        }
    }

    public String getResumenEspecialidadesRemitidas() {
        String response;
        if (especialidadesRemitidas != null && !especialidadesRemitidas.isEmpty()) {
            response = String.join(", ", especialidadesRemitidas);
        } else {
            response = "Sin especialidades remitidas";
        }
        return response;
    }
}