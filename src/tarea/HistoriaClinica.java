package tarea;

import java.util.ArrayList;

public class HistoriaClinica {
    private int id;
    private ArrayList<Visita> visitas;
    private ArrayList<String> resultadosDeAnalisis;

    public HistoriaClinica(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(Visita visita) {
        this.visitas.add(visita);
    }

    public ArrayList<String> getResultadosDeAnalisis() {
        return resultadosDeAnalisis;
    }

    public void setResultadosDeAnalisis(ArrayList<String> resultadosDeAnalisis) {
        this.resultadosDeAnalisis = resultadosDeAnalisis;
    }
}
