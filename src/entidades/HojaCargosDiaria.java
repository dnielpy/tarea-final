package entidades;

import java.util.ArrayList;
import java.util.Date;

public class HojaCargosDiaria {
    private Date fecha;
    private ArrayList<RegistroHojaCargos> registros;

    public HojaCargosDiaria(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<RegistroHojaCargos> getRegistros() {
        return registros;
    }

    public void agregarRegistro(RegistroHojaCargos registro) {
        this.registros.add(registro);
    }
}
