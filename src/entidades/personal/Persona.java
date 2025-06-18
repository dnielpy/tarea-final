package entidades.personal;

import service.Validations;

public abstract class Persona {

	protected String nombre;
	protected String primerApellido;
	protected String segundoApellido;
	protected String ci;

	public Persona(String nombre, String primerApellido, String segundoApellido, String ci) {
		setNombre(nombre);
		setPrimerApellido(primerApellido);
		setSegundoApellido(segundoApellido);
		setCI(ci);
	}
	
	public String getCI(){
		return ci;
	}

	public void setCI(String carnet) {
        if(Validations.isValidCI(carnet.trim())) {
            this.ci = carnet.trim();
        } else {
            throw new IllegalArgumentException("Carnet de identidad inv\u00E1lido" + carnet);
        }
    }

	public String getNombre() {
		return nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public String getNombreYApellidos() {
		return nombre + " " + primerApellido + " " + segundoApellido;
	}

	public void setNombre(String nombre) {
		if(Validations.validateName(nombre)){
		    this.nombre = nombre.trim();
        } 
        else{
            throw new IllegalArgumentException("Nombre inv\u00E1lido");
        }
	}

	public void setPrimerApellido(String primerApellido) {
		if (Validations.validateName(primerApellido)) {
			this.primerApellido = primerApellido.trim();
		} else {
			throw new IllegalArgumentException("Apellido inv\u00E1lido: " + (primerApellido == null ? "null" : primerApellido));
		}
	}
	
	public void setSegundoApellido(String segundoApellido) {
		if (Validations.validateName(segundoApellido)) {
			this.segundoApellido = segundoApellido.trim();
		} else {
			throw new IllegalArgumentException("Apellido inv\u00E1lido: " + (segundoApellido == null ? "null" : segundoApellido));
		}
	}
}