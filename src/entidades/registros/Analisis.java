package entidades.registros;

import java.time.LocalDate;

import util.ConstantesAnalisis;

public class Analisis {
    private int id;
    private LocalDate fechaOrientado;
    private LocalDate fechaResultado;
    private String tipoDeAnalisis;
    private String resultados;
    private int visitaId;
    private int historiaClinicaId; 

    public Analisis(int id, String tipoDeAnalisis, LocalDate fechaOrientado, int visitaId, int historiaClinicaId) {
        setId(id);
        setTipoDeAnalisis(tipoDeAnalisis);    
        setFechaOrientado(fechaOrientado);
        setVisitaId(visitaId);
        setHistoriaClinicaId(historiaClinicaId);
    }

    // Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID del an�lisis no puede ser negativo");
        }
        this.id = id;
    }

    // Fecha de orientado
    public LocalDate getFechaOrientado() {
        return fechaOrientado;
    }

    public void setFechaOrientado(LocalDate fechaOrientado) {
        if (fechaOrientado == null) {
            throw new IllegalArgumentException("La fecha de orientaci�n no puede ser nula");
        }
        this.fechaOrientado = fechaOrientado;
    }

    // Fecha del resultado
    public LocalDate getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDate fechaResultado) {
        if (fechaResultado == null) {
            throw new IllegalArgumentException("La fecha de resultado no puede ser nula");
        }
        if (fechaOrientado != null && fechaResultado.isBefore(fechaOrientado)) {
            throw new IllegalArgumentException("La fecha de resultado no puede ser anterior a la fecha de orientaci�n");
        }
        this.fechaResultado = fechaResultado;
    }

    // Tipo de an�lisis
    public String getTipoDeAnalisis() {
        return tipoDeAnalisis;
    }

    public void setTipoDeAnalisis(String tipoDeAnalisis) {
        if (tipoDeAnalisis == null) {
            throw new IllegalArgumentException("El tipo de an�lisis no puede ser nulo");
        }
        String tipoTrim = tipoDeAnalisis.trim();
        if (tipoTrim.isEmpty()) {
            throw new IllegalArgumentException("El tipo de an�lisis no puede ser vac�o");
        }
        if (!ConstantesAnalisis.TIPOS_ANALISIS.contains(tipoTrim)) {
            throw new IllegalArgumentException("Tipo de an�lisis no v�lido");
        }
        this.tipoDeAnalisis = tipoTrim;
    }

    // Resultados
    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        if (resultados == null || resultados.trim().isEmpty()) {
            throw new IllegalArgumentException("Los resultados no pueden ser nulos o vac�os");
        }
        this.resultados = resultados.trim();
    }

    // VisitaId
    public int getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(int visitaId) {
        if (visitaId < 0) {
            throw new IllegalArgumentException("El ID de la visita no puede ser negativo");
        }
        this.visitaId = visitaId;
    }

    // HistoriaClinicaId
    public int getHistoriaClinicaId() {
        return historiaClinicaId;
    }

    public void setHistoriaClinicaId(int historiaClinicaId) {
        if (historiaClinicaId < 0) {
            throw new IllegalArgumentException("El ID de la historia cl�nica no puede ser negativo");
        }
        this.historiaClinicaId = historiaClinicaId;
    }

    // Estado pendiente
    public boolean estaPendienteDeResultado() {
        return (this.fechaResultado == null) || 
               (this.resultados == null || this.resultados.trim().isEmpty());
    }
}
