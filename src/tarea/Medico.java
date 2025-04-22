package tarea;

import java.util.Date;

public class Medico {
	private String nombre;
	private int numRegistro;
	private String carnet;
	private Date fechaInscripcion;

	Medico(String nombre, int numRegistro, String carnet, Date fecha) {
		setNombre(nombre);
		setNumRegistro(numRegistro);
		setCarnet(carnet);
		setFechaInscripcion(fecha);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumRegistro() {
		return numRegistro;
	}

	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fecha) {
		fechaInscripcion = fecha;
	}
}
