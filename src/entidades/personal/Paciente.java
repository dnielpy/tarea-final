package entidades.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entidades.registros.HistoriaClinica;
import excepciones.Excepciones.IllegalAddressException;
import excepciones.Excepciones.IllegalVaccinationException;
import service.Validations;

public class Paciente extends Persona {

    protected String direccion;
    protected List<String> enfermedadesCronicas;
    protected List<String> vacunacion;
    protected HistoriaClinica historiaClinica;

    // Constructor
    public Paciente(int historiaClinicaID, String nombre, String primerApellido, String segundoApellido, String ci,
            String direccion) {
        super(nombre, primerApellido, segundoApellido, ci);
        setDireccion(direccion);
        historiaClinica = new HistoriaClinica(historiaClinicaID);
        vacunacion = new ArrayList<>();
        enfermedadesCronicas = new ArrayList<>();
    }

    // Direccion

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        Objects.requireNonNull(direccion, "La direcci\u00F3n no puede ser nula");
        if (direccion.trim().isEmpty()) {
            throw new IllegalAddressException("La direcci\u00F3n no puede estar vacia");
        }
        if (direccion.length() > 300) {
            throw new IllegalAddressException("La direcci\u00F3n no puede exceder 300 caracteres");
        }
        this.direccion = direccion.trim();
    }

    // Historia clinica

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    // Edad y genero

    public int getEdad() {
        return Validations.getAgeFromCI(ci);
    }

    public String getGenero() {
        int decimoDigito = Character.getNumericValue(ci.charAt(9));
        return (decimoDigito % 2 == 0) ? "Masculino" : "Femenino";
    }

    // Enfermedades cronicas

    public List<String> getEnfermedadesCronicas() {
        return new ArrayList<>(enfermedadesCronicas);
    }

    public void setEnfermedadesCronicas(List<String> enfermedades) {
        enfermedadesCronicas = new ArrayList<>();

        if (enfermedades != null) {
            for (String enfermedad : enfermedades) {
                agregarEnfermedadCronica(enfermedad);
            }
        }
    }

    public void agregarEnfermedadCronica(String enfermedad) {
        Objects.requireNonNull(enfermedad, "La enfermedad no puede ser nula");
        if (enfermedad.trim().isEmpty()) {
            throw new IllegalArgumentException("La enfermedad no puede estar vac\u00EDa");
        }
        if (enfermedad.length() > 200) {
            throw new IllegalArgumentException("El nombre de la enfermedad no puede exceder 200 caracteres");
        }
        enfermedadesCronicas.add(enfermedad.trim());
    }

    // Vacunacion

    public List<String> getVacunacion() {
        return new ArrayList<>(vacunacion);
    }

    public void setVacunacion(ArrayList<String> vacunacion) {
        Objects.requireNonNull(vacunacion, "La lista de vacunaci\u00F3n no puede ser nula");
        this.vacunacion = new ArrayList<>();
        for (String vacuna : vacunacion) {
            agregarVacuna(vacuna);
        }
    }

    public void agregarVacuna(String vacuna) {
        Objects.requireNonNull(vacuna, "La vacuna no puede ser nula");
        if (vacuna.trim().isEmpty()) {
            throw new IllegalVaccinationException("La vacuna no puede estar vac\u00ED­a");
        }
        if (vacuna.length() > 100) {
            throw new IllegalVaccinationException("El nombre de la vacuna no puede exceder 100 caracteres");
        }
        this.vacunacion.add(vacuna.trim());
    }

    // En riesgo

    public boolean estaEnRiesgo() {
        return enfermedadesCronicas != null && enfermedadesCronicas.size() > 3;
    }
}
