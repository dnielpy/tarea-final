package entidades;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Objects;

import service.Validations;

public class Medico extends Persona {
    private int numRegistro;
    private String ci;
    private String fechaInscripcion;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Medico(String nombre, String primerApellido, String segundoApellido, int numRegistro, String ci, String fecha) {
        setNombre(nombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
        setNumRegistro(numRegistro);
        setCarnet(ci);
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

    public String getCarnet() {
        return ci;
    }

    public void setCarnet(String carnet) {
        if(Validations.isValidCI(carnet)){
            this.ci = carnet;
        }
        else{
            throw new IllegalArgumentException("Carnet de identidad inválido");
        }
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fecha) {
        Objects.requireNonNull(fecha, "La fecha no puede ser nula");
        try {
            dateFormat.parse(fecha);
            this.fechaInscripcion = fecha;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido, use dd/MM/yyyy");
        }
    }
}