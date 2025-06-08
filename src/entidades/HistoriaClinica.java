package entidades;

import java.util.ArrayList;
import java.util.Objects;

public class HistoriaClinica {
    private int id;
    private ArrayList<String> resultadosDeAnalisis;
    private ArrayList<RegistroVisita> registroVisitas;

    public HistoriaClinica(int id, ArrayList<String> resultadosDeAnalisis, ArrayList<RegistroVisita> registroVisitas) {
        setId(id);
        setResultadosDeAnalisis(resultadosDeAnalisis);
        setRegistroVisitas(registroVisitas);
    }

    public HistoriaClinica(int id) {
       setId(id);
    }

    public ArrayList<RegistroVisita> getRegistroVisitas() {
        return registroVisitas;
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getResultadosDeAnalisis() {
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

    public void setResultadosDeAnalisis(ArrayList<String> resultadosDeAnalisis) {
        Objects.requireNonNull(resultadosDeAnalisis, "La lista de resultados no puede ser nula");
        
        for (String resultado : resultadosDeAnalisis) {
            if (resultado == null || resultado.trim().isEmpty()) {
                throw new IllegalArgumentException("Los resultados no pueden ser nulos o vacíos");
            }
            if (resultado.length() > 1000) {
                throw new IllegalArgumentException("Cada resultado no puede exceder los 1000 caracteres");
            }
        }
        
        this.resultadosDeAnalisis = resultadosDeAnalisis;
    }

    public void agregarResultadoAnalisis(String resultado) {
        Objects.requireNonNull(resultado, "El resultado no puede ser nulo");
        if (resultado.trim().isEmpty()) {
            throw new IllegalArgumentException("El resultado no puede estar vacío");
        }
        if (resultado.length() > 1000) {
            throw new IllegalArgumentException("El resultado no puede exceder los 1000 caracteres");
        }
        this.resultadosDeAnalisis.add(resultado);
    }

    public void agregarRegistroVisita(RegistroVisita registro) {
        Objects.requireNonNull(registro, "El registro de visita no puede ser nulo");
        this.registroVisitas.add(registro);
    }
}