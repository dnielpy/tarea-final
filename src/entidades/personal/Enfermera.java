package entidades.personal;

import java.util.Objects;
import java.time.LocalDate;

import runner.Usuario;

public class Enfermera extends PersonalSanitario {
    private int id;
    private boolean licenciatura;
    private int experiencia;
    private LocalDate fechaInicio;

    public Enfermera(String nombre, String primerApellido, String segundoApellido, int id, String ci,
                     boolean licenciatura, int experiencia, LocalDate fecha, String email, String password) {
        super(nombre, primerApellido, segundoApellido, ci, email, password, Usuario.TipoDeRol.ENFERMERA);
        setId(id);
        setLicenciatura(licenciatura);
        setExperiencia(experiencia);
        setFechaInicio(fecha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un n\u00FAmero positivo");
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
            throw new IllegalArgumentException("La experiencia no puede ser mayor a 50 a\u00F1os");
        }
        this.experiencia = experiencia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fecha) {
        Objects.requireNonNull(fecha, "La fecha de inicio no puede ser nula");
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura");
        }
        this.fechaInicio = fecha;
    }
}
