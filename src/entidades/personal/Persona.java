package entidades.personal;

import excepciones.CIInvalidoException;
import service.ValidacionesComunes;
import util.CIUtil;

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

    // Carne de identidad
    public String getCI() {
        return ci;
    }

    public void setCI(String carnet) {
        if (CIUtil.esCIValido(carnet.trim())) {
            this.ci = carnet.trim();
        } else {
            throw new CIInvalidoException("Carnet de identidad inválido: " + carnet);
        }
    }

    
    // Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        ValidacionesComunes.validarNombre(nombre); // lanza excepción si inválido
        this.nombre = nombre.trim();
    }

    // Primer apellido
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        ValidacionesComunes.validarPrimerApellido(primerApellido); // lanza excepción si inválido
        this.primerApellido = primerApellido.trim();
    }

    // Segundo apellido
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        if (segundoApellido == null || segundoApellido.trim().isEmpty()) {
            this.segundoApellido = "";
        } else {
            ValidacionesComunes.validarSegundoApellido(segundoApellido); // lanza excepción si inválido
            this.segundoApellido = segundoApellido.trim();
        }
    }

    public String getNombreYApellidos() {
        return nombre + " " + primerApellido + " " + segundoApellido;
    }
}
