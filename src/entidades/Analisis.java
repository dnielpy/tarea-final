package entidades;

public class Analisis {
    private String nombrePaciente;
    private int historiaClinicaId;
    private String resultados;

    public Analisis(String nombrePaciente, int historiaClinicaId, String resultados) {
        setNombrePaciente(nombrePaciente);
        setHistoriaClinicaId(historiaClinicaId);
        setResultados(resultados);
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        if (nombrePaciente == null || nombrePaciente.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacío");
        }
        if (nombrePaciente.length() > 100) {
            throw new IllegalArgumentException("El nombre del paciente no puede exceder los 100 caracteres");
        }
        this.nombrePaciente = nombrePaciente.trim();
    }

    public int getHistoriaClinicaId() {
        return historiaClinicaId;
    }

    public void setHistoriaClinicaId(int historiaClinicaId) {
        if (historiaClinicaId <= 0) {
            throw new IllegalArgumentException("El ID de historia clínica debe ser un número positivo");
        }
        this.historiaClinicaId = historiaClinicaId;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        if (resultados == null) {
            throw new IllegalArgumentException("Los resultados no pueden ser nulos");
        }
        if (resultados.length() > 1000) {
            throw new IllegalArgumentException("Los resultados no pueden exceder los 1000 caracteres");
        }
        this.resultados = resultados;
    }
}