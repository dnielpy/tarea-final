package entidades;

import java.util.ArrayList;

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
        this.id = id;
    }

    public void setRegistroVisitas(ArrayList<RegistroVisita> registroVisitas) {
        this.registroVisitas = registroVisitas;
    }

    public void setResultadosDeAnalisis(ArrayList<String> resultadosDeAnalisis) {
        this.resultadosDeAnalisis = resultadosDeAnalisis;
    }
}
