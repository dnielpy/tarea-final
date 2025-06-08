package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import service.Validations;

public class Paciente extends Persona {
    protected int historiaClinicaID;
    protected ArrayList<String> enfermedadesCronicas;
    protected ArrayList<String> vacunacion;
    protected HistoriaClinica historiaClinica;
    protected String ci;
    protected int edad;

    public Paciente(int historiaClinicaID, String nombre, String primerApellido, String segundoApellido, String ci) {
        setHistoriaClinicaID(historiaClinicaID);
        setNombre(nombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
        setCI(ci);
        setEdad();
        this.vacunacion = new ArrayList<>();
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

    public String getCi() {
        return ci;
    }

    public void setCI(String carnet) {
        if(Validations.isValidCI(carnet.trim())) {
            this.ci = carnet.trim();
        } else {
            throw new IllegalArgumentException("Carnet de identidad inválido" + carnet);
        }
    }

    public void setEdad() {
        this.edad = Validations.getAgeFromCI(ci);
    }

    public int getEdad() {
        return this.edad;
    }

    public String getGenero() {
        int septimoDigito = Character.getNumericValue(ci.charAt(6));
        return (septimoDigito % 2 == 0) ? "Femenino" : "Masculino";
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
        return enfermedadesCronicas != null && enfermedadesCronicas.size() >= 3;
    }
}
