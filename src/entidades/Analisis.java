package entidades;

public class Analisis {
    private String tipoDeAnalisis;
    private String resultados;

    public Analisis(String tipoDeAnalisis, String resultados) {
        setIndicaciones(tipoDeAnalisis);
        setResultados(resultados);
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public String getTipoDeAnalisis() {
        return tipoDeAnalisis;
    }

    public void setIndicaciones(String tipoDeAnalisis) {
        if (tipoDeAnalisis == null || tipoDeAnalisis.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de análisis no puede ser nulo o vacío");
        }
        this.tipoDeAnalisis = tipoDeAnalisis;
    }
}