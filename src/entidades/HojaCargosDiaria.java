package entidades;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Objects;

public class HojaCargosDiaria {
    private String fecha;
    private ArrayList<RegistroHojaCargos> registros;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public HojaCargosDiaria(String fecha) {
        setFecha(fecha);
        this.registros = new ArrayList<>();
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        Objects.requireNonNull(fecha);
        try {
            dateFormat.parse(fecha);
            this.fecha = fecha;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de fecha inv�lido, use dd/MM/yyyy");
        }
    }

    public ArrayList<RegistroHojaCargos> getRegistros() {
        return registros;
    }

    public void agregarRegistro(RegistroHojaCargos registro) {
        Objects.requireNonNull(registro);
        this.registros.add(registro);
    }
}