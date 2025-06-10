package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoriaClinica {
    private int id;
    private List<ResultadoAnalisis> resultadosDeAnalisis;
    private List<Visita> visitas;

    public HistoriaClinica(int id, List<ResultadoAnalisis> resultadosDeAnalisis, List<Visita> visitas) {
    	setId(id);
    	setResultadosDeAnalisis(resultadosDeAnalisis);
        setVisitas(visitas);
    }

    public HistoriaClinica(int id) {
       setId(id);
       resultadosDeAnalisis = new ArrayList<ResultadoAnalisis>();
       visitas = new ArrayList<Visita>();
    }
    
    public List<Visita> getRegistroVisitas() {
        return visitas;
    }

    public int getId() {
        return id;
    }

    public List<ResultadoAnalisis> getResultadosDeAnalisis() {
        return resultadosDeAnalisis;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        this.id = id;
    }

    public void setVisitas(List<Visita> visitas) {
        Objects.requireNonNull(visitas, "La lista de registros no puede ser nula");
        
        for (Visita visita : visitas) {
            Objects.requireNonNull(visita, "Los registros de visita no pueden ser nulos");
        }
        
        this.visitas = visitas;
    }
    
    public void setResultadosDeAnalisis(List<ResultadoAnalisis> resultados) {
        Objects.requireNonNull(resultados, "La lista de resultados no puede ser nula");
        
        for (ResultadoAnalisis resultado : resultados) {
            Objects.requireNonNull(resultado, "Los resultados de análisis no pueden ser nulos");
        }
        
        this.resultadosDeAnalisis = resultados;
    }


    public void agregarResultadoAnalisis(ResultadoAnalisis resultado) {
        Objects.requireNonNull(resultado, "El resultado no puede ser nulo");
        resultadosDeAnalisis.add(resultado);
    }

    public void agregarRegistroVisita(Visita visita) {
        Objects.requireNonNull(visita, "El registro de visita no puede ser nulo");
        visitas.add(visita);
    }
}