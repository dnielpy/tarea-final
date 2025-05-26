package clases;

public class Analisis {
    private String nombrePaciente;
    private int historiaClinicaId;
    private String resultados;

    public Analisis(String nombrePaciente, int historiaClinicaId, String resultados) {
        setNombrePaciente(nombrePaciente);
        setResultados(resultados);
        setHistoriaClinicaId(historiaClinicaId);
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public int getHistoriaClinicaId() {
        return historiaClinicaId;
    }

    public void setHistoriaClinicaId(int historiaClinicaId) {
        this.historiaClinicaId = historiaClinicaId;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }
}
