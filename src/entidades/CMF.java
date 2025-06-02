package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CMF {
	private int id;
	private String nombre;
	private String nombreDirector;
	private ArrayList<HojaCargosDiaria> hojasCargoDiaria;
	private ArrayList<Paciente> pacientes;
	private Medico medico;
	private RegistroGeneral registroGeneral;
	private RegistroHistorico registroHistorico;
	private Enfermera enfermera;

	public CMF(int id, String nombre, String nombreDirector) {
		setId(id);
		setNombre(nombre);
		setNombreDirector(nombreDirector);
		this.hojasCargoDiaria = new ArrayList<>();
		this.pacientes = new ArrayList<>();
	}

	public ArrayList<Paciente> getPacientes() {
		return new ArrayList<>(pacientes);
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreDirector() {
		return nombreDirector;
	}

	public Enfermera getEnfermera(){
		return this.enfermera;
	}

	public Medico getMedico(){
		return this.medico;
	}

	public void setId(int id) {
		if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo");
		this.id = id;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("Nombre no puede ser nulo o vacío");
		if (nombre.length() > 100) throw new IllegalArgumentException("Nombre no puede exceder 100 caracteres");
		this.nombre = nombre.trim();
	}

	public void setNombreDirector(String nombreDirector) {
		if (nombreDirector == null || nombreDirector.trim().isEmpty()) throw new IllegalArgumentException("Nombre del director no puede ser nulo o vacío");
		if (nombreDirector.length() > 100) throw new IllegalArgumentException("Nombre del director no puede exceder 100 caracteres");
		this.nombreDirector = nombreDirector.trim();
	}

	public void crearMedico(String nombre, int numRegistro, String ci, String fecha) {
		this.medico = new Medico(nombre, numRegistro, ci, fecha);
	}

	public void crearRegistroGeneral() {
		this.registroGeneral = new RegistroGeneral();
	}

	public void crearRegistroHistorico() {
		this.registroHistorico = new RegistroHistorico();
	}

	public void agregarPaciente(int historiaClinicaID, String nombre, int edad, ArrayList<String> vacunacion) {
		Paciente newPaciente = new Paciente(historiaClinicaID, nombre, edad, vacunacion);
		this.pacientes.add(newPaciente);
	}

	public void agregarPaciente(int historiaClinicaID, String nombre, int edad, ArrayList<String> vacunacion, String fechaUltimaRevision, boolean embarazada) {
		Mujer newMujer = new Mujer(historiaClinicaID, nombre, edad, vacunacion, fechaUltimaRevision, embarazada);
		this.pacientes.add(newMujer);
	}

	public void crearEnfermera(String nombre, int id, boolean licenciatura, int experiencia, String fecha) {
		Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
		if (this.enfermera != null) {
			throw new IllegalStateException("Ya existe una enfermera asignada a este CMF");
		}
		this.enfermera = new Enfermera(nombre, id, licenciatura, experiencia, fecha);
	}

	public void agregarHojaCargoDiaria(String fecha) {
		HojaCargosDiaria hoja = new HojaCargosDiaria(fecha);
		this.hojasCargoDiaria.add(hoja);
	}

	public void actualizarEnfermera(String nombre, boolean licenciatura, int experiencia, String fecha) {
		if (this.enfermera == null) {
			throw new IllegalStateException("No hay enfermera asignada para actualizar");
		}
		this.enfermera.setNombre(nombre);
		this.enfermera.setLicenciatura(licenciatura);
		this.enfermera.setExperiencia(experiencia);
		this.enfermera.setFechaInicio(fecha);
	}

	public float obtenerPorcientoEmbarazadasEnRiesgo() {
		if (pacientes == null) throw new IllegalArgumentException("Lista de pacientes no puede ser nula");
		float embarazadasEnRiesgo = 0;
		for (Paciente paciente : pacientes) {
			if (paciente instanceof Mujer && ((Mujer) paciente).isEmbarazada() && paciente.estaEnRiesgo()) {
				embarazadasEnRiesgo++;
			}
		}
		return embarazadasEnRiesgo;
	}

	public int[] obtenerRangosDeEdad() {
		if (pacientes == null) throw new IllegalArgumentException("Lista de pacientes no puede ser nula");
		int[] rangoDeEdad = new int[10];
		for (Paciente paciente : pacientes) {
			int edad = paciente.getEdad();
			if (edad < 11) rangoDeEdad[0]++;
			else if (edad < 21) rangoDeEdad[1]++;
			else if (edad < 31) rangoDeEdad[2]++;
			else if (edad < 41) rangoDeEdad[3]++;
			else if (edad < 51) rangoDeEdad[4]++;
			else if (edad < 61) rangoDeEdad[5]++;
			else if (edad < 71) rangoDeEdad[6]++;
			else if (edad < 81) rangoDeEdad[7]++;
			else if (edad < 91) rangoDeEdad[8]++;
		}
		return rangoDeEdad;
	}

	public void cargarDatos() {
		crearMedico("Alfonso Rodriguez Camela", 11321, "98020378176", "12/3/1998");

		ArrayList<String> vacunacion1 = new ArrayList<>();
		vacunacion1.add("Antiplio: 13/4/2009");
		agregarPaciente(1, "Armando Lopez Del Toro", 45, vacunacion1);

		ArrayList<String> vacunacion2 = new ArrayList<>();
		vacunacion2.add("Antiplio: 13/4/2020");
		agregarPaciente(2, "Amanda Gutierres Garcia", 25, vacunacion2);

		ArrayList<String> vacunacion3 = new ArrayList<>();
		vacunacion3.add("Antiplio: 13/4/2019");
		agregarPaciente(3, "Carlos Garces Fernandez", 34, vacunacion3);
	}

	public int obtenerTotalPacientes(){
		return this.pacientes.size();
	}

	public int obtenerCantidadMujeres(){
		int contador = 0;

		for(Paciente paciente: this.pacientes){
			if(paciente instanceof Mujer){
				contador++;
			}
		}

		return contador;
	}

	public int obtenerCantidadDeEmbarazadas(){
		int contador = 0;

		for(Paciente paciente: this.pacientes){
			if(paciente instanceof Mujer){
				if(((Mujer)paciente).isEmbarazada()){
					contador++;
				}
			}
		}

		return contador;
	}

	public double obtenerPorcentajeEmbarazadasRespectoAMujeres(){
		return (obtenerCantidadDeEmbarazadas() * 100)/ obtenerCantidadMujeres();
	}

	public int obtenerPacientesEnRiesgo(){
		int pacientesEnRiesgo = 0;
		for(Paciente paciente: this.pacientes){
			if(paciente.estaEnRiesgo()){
				pacientesEnRiesgo++;
			}
		}
		return pacientesEnRiesgo;
	}
	
	public int obtenerCantidadPacientesPorFecha(String fecha) {
	    if (fecha == null || fecha.trim().isEmpty()) {
	        throw new IllegalArgumentException("La fecha no puede ser nula o vacía");
	    }

	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false); // Desactiva la tolerancia a errores como 32/01/2025

	    try {
	        dateFormat.parse(fecha);
	    } catch (ParseException e) {
	        throw new IllegalArgumentException("Formato de fecha inválido. Use dd/MM/yyyy");
	    }

	    for (HojaCargosDiaria hoja : hojasCargoDiaria) {
	        if (hoja.getFecha().equals(fecha)) {
	            return hoja.getRegistros().size();
	        }
	    }
	    return 0;
	}


}