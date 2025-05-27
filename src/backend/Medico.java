package backend;

import java.util.Date;

public class Medico extends Persona{
	private int numRegistro;
	private String ci;
	private Date fechaInscripcion;

	Medico(String nombre, int numRegistro, String ci, Date fecha) {
		setNombre(nombre);
		setNumRegistro(numRegistro);
		setCarnet(ci);
		setFechaInscripcion(fecha);
	}

	public void setNombre(String nombre) {
		super.nombreYApellidos = nombre;
	}

	public int getNumRegistro() {
		return numRegistro;
	}

	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}

	public String getCarnet() {
		return ci;
	}

	public void setCarnet(String carnet) {
		this.ci = carnet;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fecha) {
		fechaInscripcion = fecha;
	}
}
