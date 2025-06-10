package entidades;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HojaCargosDiaria {
	private Date fecha;
	private List<Visita> visitas;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public HojaCargosDiaria(Date fecha) {
		setFecha(fecha);
		visitas = new ArrayList<>();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		Objects.requireNonNull(fecha, "La fecha no puede ser nula");

		// Validar que no sea una fecha futura
		Date hoy = new Date();
		if (fecha.after(hoy)) {
			throw new IllegalArgumentException("La fecha no puede ser futura");
		}

		this.fecha = fecha;
	}

	public String getFechaFormateada() {
		return dateFormat.format(fecha);
	}

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void agregarVisita(Visita visita) {
		Objects.requireNonNull(visita, "El registro no puede ser nulo");
		visitas.add(visita);
	}
}
