package tarea;

import java.util.ArrayList;

public class Paciente {
    private int historiaClinicaID;
    private String nombre;
    private int edad;
    private boolean sexo;
    private ArrayList<String> enfermedadesCronicas;
    private boolean vacunado;

    public Paciente(int historiaClinicaID, String nombre, int edad, boolean sexo, boolean vacunado) {
        this.historiaClinicaID = historiaClinicaID;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.vacunado = vacunado;
    }

    public int getHistoriaClinicaID() {
        return historiaClinicaID;
    }

    public void setHistoriaClinicaID(int historiaClinicaID) {
        this.historiaClinicaID = historiaClinicaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public ArrayList<String> getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public void setEnfermedadesCronicas(String enfermedadesCronicas) {
        this.enfermedadesCronicas.add(enfermedadesCronicas);
    }

    public boolean isVacunado() {
        return vacunado;
    }

    public void setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
    }
}
