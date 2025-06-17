package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoriaClinica {
    private int id;
    private ArrayList<Analisis> analisis;
    private ArrayList<Visita> visitas;

    // Constructores

    public HistoriaClinica(int id, ArrayList<Analisis> resultadosDeAnalisis, ArrayList<Visita> visitas) {
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

    public ArrayList<Analisis> getAnalisis() {
        return analisis;
    }

    public void setResultadosDeAnalisis(ArrayList<Analisis> resultados) {
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

    public void setVisitas(ArrayList<Visita> visitas) {
        Objects.requireNonNull(visitas, "La lista de registros no puede ser nula");

        for (Visita visita : visitas) {
            Objects.requireNonNull(visita, "Los registros de visita no pueden ser nulos");
        }

        this.visitas = visitas;
    }

    public void agregarVisita(Visita v) {
        this.visitas.add(v);
    }

    public void agregarRegistroVisita(Visita visita) {
        Objects.requireNonNull(visita, "El registro de visita no puede ser nulo");
        visitas.add(visita);
    }
}