package entidades;

public class RegistroHojaCargos {
    private String direccion;
    private String diagnostico;
    private Paciente paciente;

    public RegistroHojaCargos(String direccion, String diagnostico, Paciente paciente) {
        setDireccion(direccion);
        setDiagnostico(diagnostico);
        setPaciente(paciente);
    }

    public String getNombre(){
        return this.paciente.getNombre();
    }

    public int getEdad(){
        return this.paciente.getEdad();
    }

    public String getGenero(){
        String genero = "Hombre";
        if(this.paciente instanceof Mujer){
            genero = "Mujer";
        }
        return genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
