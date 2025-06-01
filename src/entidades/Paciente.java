package entidades;

import java.util.ArrayList;
import java.util.Objects;

public class Paciente extends Persona {
    protected int historiaClinicaID;
    protected int edad;
    protected ArrayList<String> enfermedadesCronicas;
    protected ArrayList<String> vacunacion;
    protected HistoriaClinica historiaClinica;

    public Paciente(int historiaClinicaID, String nombre, int edad, ArrayList<String> vacunacion) {
        setHistoriaClinicaID(historiaClinicaID);
        setNombre(nombre);
        setEdad(edad);
        setVacunacion(vacunacion);
        this.enfermedadesCronicas = new ArrayList<>();
        this.historiaClinica = new HistoriaClinica(historiaClinicaID);
    }

    public int getHistoriaClinicaID() {
        return historiaClinicaID;
    }

    public void setHistoriaClinicaID(int historiaClinicaID) {
        if (historiaClinicaID <= 0) {
            throw new IllegalArgumentException("ID de historia clínica debe ser positivo");
        }
        this.historiaClinicaID = historiaClinicaID;
    }

    @Override
    public String getNombreYApellidos() {
        return super.nombreYApellidos;
    }

    @Override
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
        super.nombreYApellidos = nombre.trim();
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

    public ArrayList<String> getEnfermedadesCronicas() {
        return new ArrayList<>(enfermedadesCronicas);
    }

    public void agregarEnfermedadCronica(String enfermedad) {
        Objects.requireNonNull(enfermedad, "La enfermedad no puede ser nula");
        if (enfermedad.trim().isEmpty()) {
            throw new IllegalArgumentException("La enfermedad no puede estar vacía");
        }
        if (enfermedad.length() > 200) {
            throw new IllegalArgumentException("El nombre de la enfermedad no puede exceder 200 caracteres");
        }
        this.enfermedadesCronicas.add(enfermedad.trim());
    }

    public ArrayList<String> getVacunacion() {
        return new ArrayList<>(vacunacion);
    }

    public void setVacunacion(ArrayList<String> vacunacion) {
        Objects.requireNonNull(vacunacion, "La lista de vacunación no puede ser nula");
        this.vacunacion = new ArrayList<>();
        for (String vacuna : vacunacion) {
            agregarVacuna(vacuna);
        }
    }

    public void agregarVacuna(String vacuna) {
        Objects.requireNonNull(vacuna, "La vacuna no puede ser nula");
        if (vacuna.trim().isEmpty()) {
            throw new IllegalArgumentException("La vacuna no puede estar vacía");
        }
        if (vacuna.length() > 100) {
            throw new IllegalArgumentException("El nombre de la vacuna no puede exceder 100 caracteres");
        }
        this.vacunacion.add(vacuna.trim());
    }

    public boolean estaEnRiesgo() {
        boolean enRiesgo = false;
        if (enfermedadesCronicas != null && enfermedadesCronicas.size() >= 3) {
            enRiesgo = true;
        }
        return enRiesgo;
    }
}