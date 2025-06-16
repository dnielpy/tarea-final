package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoriaClinica {
    private int id;
    private List<Analisis> analisis;
    private List<Visita> visitas;

    // Constructores
    
    public HistoriaClinica(int id, List<Analisis> resultadosDeAnalisis, List<Visita> visitas) {
    	setId(id);
    	setResultadosDeAnalisis(resultadosDeAnalisis);
        setVisitas(visitas);
    }

    public HistoriaClinica(int id) {
       setId(id);
       analisis = new ArrayList<Analisis>();
       visitas = new ArrayList<Visita>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        this.id = id;
    }

    // Análisis
    
    public List<Analisis> getAnalisis() {
        return analisis;
    }
    
    public void setResultadosDeAnalisis(List<Analisis> resultados) {
        Objects.requireNonNull(resultados, "La lista de resultados no puede ser nula");
        
        for (Analisis resultado : resultados) {
            Objects.requireNonNull(resultado, "Los resultados de análisis no pueden ser nulos");
        }
        
        this.analisis = resultados;
    }
    
    public void agregarResultadoAnalisis(Analisis resultado) {
        Objects.requireNonNull(resultado, "El resultado no puede ser nulo");
        analisis.add(resultado);
    }
    
 
    public List<Visita> getRegistroVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        Objects.requireNonNull(visitas, "La lista de registros no puede ser nula");
        
        for (Visita visita : visitas) {
            Objects.requireNonNull(visita, "Los registros de visita no pueden ser nulos");
        }
        
        this.visitas = visitas;
    }

    public void agregarRegistroVisita(Visita visita) {
        Objects.requireNonNull(visita, "El registro de visita no puede ser nulo");
        visitas.add(visita);
    }
}