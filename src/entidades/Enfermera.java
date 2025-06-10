package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Enfermera extends Persona {
    private int id;
    private boolean licenciatura;
    private int experiencia;
    private Date fechaInicio;
    private ArrayList<ResultadoAnalisis> resultadoAnalisis;

    public Enfermera(String nombre, String primerApellido, String segundoApellido, int id, boolean licenciatura, int experiencia, Date fecha) {
        setNombre(nombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
        setId(id);
        setLicenciatura(licenciatura);
        setExperiencia(experiencia);
        setFechaInicio(fecha);
        this.resultadoAnalisis = new ArrayList<>();
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fecha) {
        Objects.requireNonNull(fecha, "La fecha de inicio no puede ser nula");

        // Convertir java.util.Date a LocalDate
        LocalDate fechaDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoy = LocalDate.now();

        if (fechaDate.isAfter(hoy)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura");
        }

        this.fechaInicio = fecha;
    }

    public ArrayList<ResultadoAnalisis> getResultadoAnalisis() {
        return resultadoAnalisis;
    }

    public void agregarResultadoAnalisis(ResultadoAnalisis resultado) {
        Objects.requireNonNull(resultado, "El resultado de análisis no puede ser nulo");
        this.resultadoAnalisis.add(resultado);
    }
}
