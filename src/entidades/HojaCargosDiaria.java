package entidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HojaCargosDiaria {
    private LocalDate fecha;
    private List<Visita> visitas;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public HojaCargosDiaria(LocalDate fecha) {
        setFecha(fecha);
        visitas = new ArrayList<>();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        Objects.requireNonNull(fecha, "La fecha no puede ser nula");

        // Validar que no sea una fecha futura
        LocalDate hoy = LocalDate.now();
        if (fecha.isAfter(hoy)) {
            throw new IllegalArgumentException("La fecha no puede ser futura");
        }

        this.fecha = fecha;
    }

    public String getFechaFormateada() {
        return fecha.format(dateFormat);
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void agregarVisita(Visita visita) {
        Objects.requireNonNull(visita, "El registro no puede ser nulo");
        visitas.add(visita);
    }
}