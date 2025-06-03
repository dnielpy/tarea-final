package entidades;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Objects;

public class Medico extends Persona {
    private int numRegistro;
    private String ci;
    private String fechaInscripcion;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Medico(String nombre, String apellidos, int numRegistro, String ci, String fecha) {
        setNombre(nombre);
        setApellidos(apellidos);
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
        Objects.requireNonNull(carnet, "El CI no puede ser nulo");
        String ciTrimmed = carnet.trim();
        
        if (!ciTrimmed.matches("^[0-9]{11}$")) {
            throw new IllegalArgumentException("El CI debe contener exactamente 11 dígitos numéricos");
        }
        
        String fechaNacimientoStr = ciTrimmed.substring(0, 6);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            sdf.setLenient(false);
            sdf.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Los primeros 6 dígitos del CI deben ser una fecha válida (AAMMDD)");
        }
        
        char digitoSiglo = ciTrimmed.charAt(6);
        if (digitoSiglo != '9' && (digitoSiglo < '0' || digitoSiglo > '8')) {
            throw new IllegalArgumentException("Dígito de siglo en CI inválido (debe ser 0-9)");
        }
        
        int digitoSexo = Integer.parseInt(ciTrimmed.substring(9));
        
        this.ci = ciTrimmed;
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