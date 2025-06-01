package entidades;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Objects;

public class Medico extends Persona {
    private int numRegistro;
    private String ci;
    private String fechaInscripcion;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Medico(String nombre, int numRegistro, String ci, String fecha) {
        setNombre(nombre);
        setNumRegistro(numRegistro);
        setCarnet(ci);
        setFechaInscripcion(fecha);
    }

    @Override
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vac�o");
        }
        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
        super.nombreYApellidos = nombre.trim();
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

    public String getCarnet() {
        return ci;
    }

    public void setCarnet(String carnet) {
        Objects.requireNonNull(carnet, "El CI no puede ser nulo");
        String ciTrimmed = carnet.trim();
        
        if (!ciTrimmed.matches("^[0-9]{11}$")) {
            throw new IllegalArgumentException("El CI debe contener exactamente 11 d�gitos num�ricos");
        }
        
        String fechaNacimientoStr = ciTrimmed.substring(0, 6);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            sdf.setLenient(false);
            sdf.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Los primeros 6 d�gitos del CI deben ser una fecha v�lida (AAMMDD)");
        }
        
        char digitoSiglo = ciTrimmed.charAt(6);
        if (digitoSiglo != '9' && (digitoSiglo < '0' || digitoSiglo > '8')) {
            throw new IllegalArgumentException("D�gito de siglo en CI inv�lido (debe ser 0-9)");
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
            throw new IllegalArgumentException("Formato de fecha inv�lido, use dd/MM/yyyy");
        }
    }
}