package entidades;

import service.Validations;

public abstract class Persona {

	protected String nombre;
	protected String primerApellido;
	protected String segundoApellido;

	public String getNombreYApellidos() {
		return nombre + " " + primerApellido + " " + segundoApellido;
	}

	public void setNombre(String nombre) {
		if(Validations.validateName(nombre)){
		    this.nombre = nombre.trim();
        } 
        else{
            throw new IllegalArgumentException("Nombre inv�lido");
        }
	}

	public void setPrimerApellido(String primerApellido) {
		if (Validations.validateName(primerApellido)) {
			this.primerApellido = primerApellido.trim();
		} else {
			throw new IllegalArgumentException("Apellido inválido: " + (primerApellido == null ? "null" : primerApellido));
		}
	}
	
	public void setSegundoApellido(String segundoApellido) {
		if (Validations.validateName(segundoApellido)) {
			this.segundoApellido = segundoApellido.trim();
		} else {
			throw new IllegalArgumentException("Apellido inválido: " + (segundoApellido == null ? "null" : segundoApellido));
		}
	}
}