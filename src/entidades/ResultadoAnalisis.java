package entidades;

public class Analisis 
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
        if (resultados == null || resultados.trim().isEmpty()) {
            throw new IllegalArgumentException("Resultados no pueden ser nulos o vac�os");
        }
        this.resultados = resultados;
    }

    public String getResultados() {
        return resultados;
    }

	public String getTipoDeAnalisis() {
		return tipoDeAnalisis;
	}

	public void setTipoDeAnalisis(String tipoDeAnalisis) {
		if (tipoDeAnalisis == null || tipoDeAnalisis.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de análisis no puede ser nulo o vacío");
        }
		this.tipoDeAnalisis = tipoDeAnalisis;
	}
}