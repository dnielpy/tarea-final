package entidades;

import java.util.ArrayList;
import java.util.Objects;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Enfermera extends Persona {
    private int id;
    private boolean licenciatura;
    private int experiencia;
    private String fechaInicio;
    private ArrayList<ResultadoAnalisis> resultadoAnalisis;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Enfermera(String nombre, String apellidos, int id, boolean licenciatura, int experiencia, String fecha) {
        setNombre(nombre);
        setApellidos(apellidos);
        setId(id);
        setLicenciatura(licenciatura);
        setExperiencia(experiencia);
        setFechaInicio(fecha);
        this.resultadoAnalisis = new ArrayList<ResultadoAnalisis>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        this.id = id;
    }
    
    public boolean getLicenciatura() {
        return licenciatura;
    }
    
    public void setLicenciatura(boolean licenciatura) {
        this.licenciatura = licenciatura;
    }
    
    public int getExperiencia() {
        return experiencia;
    }
    
    public void setExperiencia(int experiencia) {
        if (experiencia < 0) {
            throw new IllegalArgumentException("La experiencia no puede ser negativa");
        }
        if (experiencia > 50) {
            throw new IllegalArgumentException("La experiencia no puede ser mayor a 50 años");
        }
        this.experiencia = experiencia;
    }
    
    public String getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(String fecha) {
        Objects.requireNonNull(fecha, "La fecha de inicio no puede ser nula");
        
        try {
            LocalDate fechaDate = LocalDate.parse(fecha, DATE_FORMATTER);
            LocalDate hoy = LocalDate.now();
            
            if (fechaDate.isAfter(hoy)) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser futura");
            }
            this.fechaInicio = fecha;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use dd/MM/yyyy");
        }
    }
    
    public ArrayList<ResultadoAnalisis> getResultadoAnalisis() {
        return this.resultadoAnalisis;
    }
    
    public void agregarResultadoAnalisis(ResultadoAnalisis resultado) {
        Objects.requireNonNull(resultado, "El resultado de análisis no puede ser nulo");
        this.resultadoAnalisis.add(resultado);
    }
}