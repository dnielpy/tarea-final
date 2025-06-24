package entidades.personal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import runner.Usuario;

public abstract class PersonalSanitario extends Persona {

    protected Usuario user;
    private int identificador;
    private LocalDate fechaInicioCMF;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));

    // Constructor
    public PersonalSanitario(String nombre, String primerApellido, String segundoApellido, int identificador, String ci,
            LocalDate fechaInicio, String userName, String password, Usuario.TipoDeRol rol) {
    	super(nombre, primerApellido, segundoApellido, ci);
    	setIdentificador(identificador);
    	setFechaInicioCMF(fechaInicio);
    	user = new Usuario(userName, password, rol);
    }
    
    // Usuario
    public Usuario getUser() {
    	return user;
    }
    
    public void setUser(Usuario user) {
    	this.user = user;
    }
    
    // Identificador medico
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        if (identificador <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo");
        }
        this.identificador = identificador;
    }

    // Fecha en la que empezo a trabajar en el CMF
    public LocalDate getFechaInicioCMF() {
        return fechaInicioCMF;
    }

    public String getFechaInicioCMFFormateada() { 
        return fechaInicioCMF == null ? null : fechaInicioCMF.format(dateFormat);
    }
  
    public void setFechaInicioCMF(LocalDate fecha) {
        Objects.requireNonNull(fecha, "La fecha de inicio no puede ser nula");
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura");
        }
        this.fechaInicioCMF = fecha;
    }
}
