package tarea;

public class ResultadoAnalisis {
    private Paciente paciente;
    private int numeroHistClinica;
    private String resultados;

    public ResultadoAnalisis(Paciente paciente, int numeroHistClinica, String resultados) {
        setPaciente(paciente);
        setNumeroHistClinica(numeroHistClinica);
        setResultados(resultados);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getNumeroHistClinica() {
        return numeroHistClinica;
    }

    public void setNumeroHistClinica(int numeroHistClinica) {
        this.numeroHistClinica = numeroHistClinica;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }
}
