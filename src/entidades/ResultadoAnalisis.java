package entidades;

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
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no puede ser nulo");
        }
        this.paciente = paciente;
    }

    public int getNumeroHistClinica() {
        return numeroHistClinica;
    }

    public void setNumeroHistClinica(int numeroHistClinica) {
        if (numeroHistClinica <= 0) {
            throw new IllegalArgumentException("Número de historia clínica debe ser positivo");
        }
        this.numeroHistClinica = numeroHistClinica;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        if (resultados == null || resultados.trim().isEmpty()) {
            throw new IllegalArgumentException("Resultados no pueden ser nulos o vacíos");
        }
        this.resultados = resultados;
    }
}