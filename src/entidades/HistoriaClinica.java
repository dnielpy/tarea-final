package entidades;

import java.util.ArrayList;
import java.util.Objects;

public class HistoriaClinica {
    private int id;
    private ArrayList<ResultadoAnalisis> resultadosDeAnalisis;
    private ArrayList<RegistroVisita> registroVisitas;

    public HistoriaClinica(int id, ArrayList<ResultadoAnalisis> resultadosDeAnalisis, ArrayList<RegistroVisita> registroVisitas) {
        setId(id);
        setRegistroVisitas(registroVisitas);
    }

    public HistoriaClinica(int id) {
       setId(id);
       resultadosDeAnalisis = new ArrayList<ResultadoAnalisis>();
       registroVisitas = new ArrayList<RegistroVisita>();
    }

    public ArrayList<RegistroVisita> getRegistroVisitas() {
        return registroVisitas;
    }

    public int getId() {
        return id;
    }

    public ArrayList<ResultadoAnalisis> getResultadosDeAnalisis() {
        return resultadosDeAnalisis;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        this.id = id;
    }

    public void setRegistroVisitas(ArrayList<RegistroVisita> registroVisitas) {
        Objects.requireNonNull(registroVisitas, "La lista de registros no puede ser nula");
        
        for (RegistroVisita registro : registroVisitas) {
            Objects.requireNonNull(registro, "Los registros de visita no pueden ser nulos");
        }
        
        this.registroVisitas = registroVisitas;
    }

    public void agregarResultadoAnalisis(ResultadoAnalisis resultado) {
        Objects.requireNonNull(resultado, "El resultado no puede ser nulo");
        resultadosDeAnalisis.add(resultado);
    }

    public void agregarRegistroVisita(RegistroVisita registro) {
        Objects.requireNonNull(registro, "El registro de visita no puede ser nulo");
        registroVisitas.add(registro);
    }
}