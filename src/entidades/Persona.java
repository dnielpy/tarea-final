package entidades;

import java.util.Objects;

public abstract class Persona {

    protected String nombre;
    
    protected String apellidos;
    
    public String getNombreYApellidos() {
        return nombre + " " + apellidos;
    }

	public void setNombre(String nombre) {
		Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
        this.nombre = nombre.trim();
	}
	
	public void setApellidos(String apellido) {
		Objects.requireNonNull(nombre, "El apellido no puede ser nulo");
        if (apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (apellido.length() > 100) {
            throw new IllegalArgumentException("El apellido no puede exceder 100 caracteres");
        }
        this.apellidos = apellido.trim();
	}
}
