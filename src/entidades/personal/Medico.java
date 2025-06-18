package entidades.personal;

import runner.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Medico extends PersonalSanitario {
    private int numRegistro;
    private LocalDate fechaInscripcion;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Medico(String nombre, String primerApellido, String segundoApellido, int numRegistro, String ci,
                  LocalDate fecha, String email, String password) {
        super(nombre, primerApellido, segundoApellido, ci, email, password, Usuario.TipoDeRol.MÉDICO);
        setNumRegistro(numRegistro);
        setFechaInscripcion(fecha);
    }

    public int getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(int numRegistro) {
        if (numRegistro <= 0) {
            throw new IllegalArgumentException("Número de registro debe ser positivo");
        }
        this.numRegistro = numRegistro;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public String getFechaInscripcionFormateada() {
        if (fechaInscripcion == null) return null;
        return fechaInscripcion.format(dateFormat);
    }

    public void setFechaInscripcion(LocalDate fecha) {
        this.fechaInscripcion = fecha;
    }
}
