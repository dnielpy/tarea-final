package clases;

import java.util.ArrayList;
import java.util.Date;

public class Enfermera extends Persona{
	private int Id;
	private boolean licenciatura;
	private int experiencia;
	private Date fechaInicio;
	private ArrayList<ResultadoAnalisis> resultadoAnalisis;
	
	Enfermera(String nombre, int Id, boolean licenciatura, int experiencia, Date fecha) {
		setNombre(nombre);
		setId(Id);
		setLicenciatura(licenciatura);
		setExperiencia(experiencia);
		setFechaInicio(fecha);
		this.resultadoAnalisis = new ArrayList<ResultadoAnalisis>();
	}

	public void setNombre(String nombre) {
		super.nombreYApellidos = nombre;
	}
	
	public int getId() {
		return Id;
	}
	
	public void setId(int Id) {
		this.Id = Id;
	}
	
	public boolean getLicenciatura() {
		return licenciatura;
	}
	
	public void setLicenciatura(boolean licenciatura) {
		this.licenciatura = licenciatura;
	}
	
	public int getExperiencia() {
		return experiencia;
	}
	
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fecha) {
		fechaInicio = fecha;
	}
}
