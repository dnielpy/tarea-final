package entidades;

import java.text.SimpleDateFormat;
import service.Validations;

import java.util.Date;

public class Medico extends PersonalSanitario {
    private int numRegistro;
    private Date fechaInscripcion;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Medico(String nombre, String primerApellido, String segundoApellido, int numRegistro, String ci, Date fecha, String email, String password) {
        setEmail(email);
        setPassword(password);
        setNombre(nombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
        setNumRegistro(numRegistro);
        setCI(ci);
        setFechaInscripcion(fecha);
    }

    public int getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(int numRegistro) {
        if (numRegistro <= 0) {
            throw new IllegalArgumentException("N�mero de registro debe ser positivo");
        }
        this.numRegistro = numRegistro;
    }

    // Getter que devuelve el objeto Date
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    // Getter que devuelve la fecha formateada como String
    public String getFechaInscripcionFormateada() {
        if (fechaInscripcion == null) return null;
        return dateFormat.format(fechaInscripcion);
    }

    // Setter que recibe directamente un Date (opcional)
    public void setFechaInscripcion(Date fecha) {
        this.fechaInscripcion = fecha;
    }
}
