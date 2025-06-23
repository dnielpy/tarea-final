package entidades.registros;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoriaClinica {
    private int id;
    private List<Visita> visitas;

    // Constructores

    public HistoriaClinica(int id, List<Visita> visitas) {
        setId(id);
        setVisitas(visitas);
    }

    public HistoriaClinica(int id) {
        setId(id);      
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
		List<Analisis> todosAnalisis = new ArrayList<>();
		if (visitas != null) {
			for (Visita visita : visitas) {
				if (visita.getAnalisis() != null) {
					todosAnalisis.addAll(visita.getAnalisis());
				}
			}
		}
		return todosAnalisis;
	}

    // Visitas
    
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

    public void agregarVisita(Visita v) {
        this.visitas.add(v);
    }

    public void agregarRegistroVisita(Visita visita) {
        Objects.requireNonNull(visita, "El registro de visita no puede ser nulo");
        visitas.add(visita);
    }
}