package entidades;

import java.util.Objects;

public class RegistroHojaCargos {
    private String direccion;
    private String diagnostico;
    private Paciente paciente;

    public RegistroHojaCargos(String direccion, String diagnostico, Paciente paciente) {
        setDireccion(direccion);
        setDiagnostico(diagnostico);
        setPaciente(paciente);
    }

    public String getNombre() {
        return this.paciente.getNombreYApellidos();
    }

    public int getEdad() {
        return this.paciente.getEdad();
    }

    public String getGenero() {
        String genero = "Hombre";
        if(this.paciente instanceof Mujer) {
            genero = "Mujer";
        }
        return genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        Objects.requireNonNull(direccion, "La direcci�n no puede ser nula");
        if(direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La direcci�n no puede estar vac�a");
        }
        if(direccion.length() > 200) {
            throw new IllegalArgumentException("La direcci�n no puede exceder los 200 caracteres");
        }
        this.direccion = direccion.trim();
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        Objects.requireNonNull(diagnostico, "El diagn�stico no puede ser nulo");
        if(diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("El diagn�stico no puede estar vac�o");
        }
        if(diagnostico.length() > 500) {
            throw new IllegalArgumentException("El diagn�stico no puede exceder los 500 caracteres");
        }
        this.diagnostico = diagnostico.trim();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        Objects.requireNonNull(paciente, "El paciente no puede ser nulo");
        if(paciente.getEdad() < 0) {
            throw new IllegalArgumentException("La edad del paciente no puede ser negativa");
        }
        if(paciente.getNombreYApellidos() == null || paciente.getNombreYApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("El paciente debe tener un nombre v�lido");
        }
        this.paciente = paciente;
    }
}