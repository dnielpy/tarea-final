package entidades;

public class ResultadoAnalisis {
	private String tipoAnalisis;
    private String resultados;

    public ResultadoAnalisis(String tipoAnalisis, String resultados) {
        setResultados(resultados);
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

	public String getTipoAnalisis() {
		return tipoAnalisis;
	}

	public void setTipoAnalisis(String tipoAnalisis) {
		if (tipoAnalisis == null || tipoAnalisis.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de análisis no pueden ser nulos o vacíos");
        }
		this.tipoAnalisis = tipoAnalisis;
	}
}