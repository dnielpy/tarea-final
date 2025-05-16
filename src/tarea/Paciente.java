package tarea;

import java.util.ArrayList;

public class Paciente extends Persona {
    protected int historiaClinicaID;
    protected int edad;
    protected ArrayList<String> enfermedadesCronicas;
    protected ArrayList<String> vacunacion;

    public Paciente(int historiaClinicaID, String nombre, int edad, ArrayList<String> vacucnacion) {
        setHistoriaClinicaID(historiaClinicaID);
        setNombre(nombre);
        super.nombreYApellidos = nombre;
        this.vacunacion = new ArrayList<>();
    }

    public int getHistoriaClinicaID() {
        return historiaClinicaID;
    }

    public void setHistoriaClinicaID(int historiaClinicaID) {
        this.historiaClinicaID = historiaClinicaID;
    }

    public String getNombre() {
        return super.nombreYApellidos;
    }

    public void setNombre(String nombre) { super.nombreYApellidos = nombre;}

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<String> getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public void setEnfermedadesCronicas(String enfermedadesCronicas) {
        this.enfermedadesCronicas.add(enfermedadesCronicas);
    }

    public ArrayList<String> getVacucnacion() {
        return vacunacion;
    }

    public void agregarvacuna(String vacuna) {
        this.vacunacion.add(vacuna);
    }

    public boolean estaEnRiesgo(){
        boolean enRiesgo = false;
        if (enfermedadesCronicas.size() >= 3){
            enRiesgo = true;
        }
        return enRiesgo;
    }
}
