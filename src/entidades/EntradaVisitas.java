package entidades;

import java.util.Objects;

public class EntradaVisitas {
    private String nombre;
    private int edad;
    private boolean sexo; 
    private String direccion;
    private String diagnostico;
    
    public EntradaVisitas(String nombre, int edad, boolean sexo, String direccion, String diagnostico) {
        setNombre(nombre);
        setEdad(edad);
        setSexo(sexo);
        setDireccion(direccion);
        setDiagnostico(diagnostico);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        String nombreTrimmed = nombre.trim();
        if (nombreTrimmed.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombreTrimmed.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder los 100 caracteres");
        }
        if (!nombreTrimmed.matches("^[\\p{L} .'-]+$")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios");
        }
        this.nombre = nombreTrimmed;
    }
    
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        if (edad > 120) {
            throw new IllegalArgumentException("La edad no puede ser mayor a 120 años");
        }
        this.edad = edad;
    }
    
    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo; 
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        Objects.requireNonNull(direccion, "La dirección no puede ser nula");
        String dirTrimmed = direccion.trim();
        if (dirTrimmed.isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
        if (dirTrimmed.length() > 200) {
            throw new IllegalArgumentException("La dirección no puede exceder los 200 caracteres");
        }
        this.direccion = dirTrimmed;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        Objects.requireNonNull(diagnostico, "El diagnóstico no puede ser nulo");
        String diagTrimmed = diagnostico.trim();
        if (diagTrimmed.isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico no puede estar vacío");
        }
        if (diagTrimmed.length() > 500) {
            throw new IllegalArgumentException("El diagnóstico no puede exceder los 500 caracteres");
        }
        this.diagnostico = diagTrimmed;
    }
}