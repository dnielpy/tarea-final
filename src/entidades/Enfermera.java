package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.time.LocalDate;
import java.time.ZoneId;

import runner.Usuario;

public class Enfermera extends PersonalSanitario {
    private int id;
    private boolean licenciatura;
    private int experiencia;
    private Date fechaInicio;
    private ArrayList<Analisis> analisis;

    public Enfermera(String nombre, String primerApellido, String segundoApellido, int id, String ci, boolean licenciatura, int experiencia, Date fecha, String email, String password) {
        super(nombre, primerApellido, segundoApellido, ci, email, password, Usuario.TipoDeRol.ENFERMERA);
        setId(id);
        setLicenciatura(licenciatura);
        setExperiencia(experiencia);
        setFechaInicio(fecha);    
        this.analisis = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un n�mero positivo");
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
            throw new IllegalArgumentException("La experiencia no puede ser mayor a 50 a�os");
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

    public ArrayList<Analisis> getResultadoAnalisis() {
        return analisis;
    }

    public void agregarResultadoAnalisis(Analisis analisis) {
        Objects.requireNonNull(analisis, "El resultado de an�lisis no puede ser nulo");
        this.analisis.add(analisis);
    }
}
