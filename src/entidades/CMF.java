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

	    ArrayList<String> vacunacion = new ArrayList<String>();
	    vacunacion.add("Antiplio: 13/4/2009");
	    agregarPaciente(1, "Armando Lopez Del Toro", 45, vacunacion);

	    ArrayList<String> vacunacion2 = new ArrayList<String>();
	    vacunacion2.add("Antiplio: 13/4/2020");
	    agregarPaciente(2, "Amanda Lopez Garcia", 25, vacunacion2);

	    ArrayList<String> vacunacion3 = new ArrayList<String>();
	    vacunacion3.add("Antiplio: 13/4/2019");
	    agregarPaciente(3, "Carlos Garces Fernandez", 34, vacunacion3);

	    ArrayList<String> vacunacion4 = new ArrayList<String>();
	    vacunacion4.add("Antiplio: 10/1/2022");
	    vacunacion4.add("Antitetánica: 5/3/2023");
	    agregarPaciente(4, "Daniela Suarez Molina", 29, vacunacion4);

	    ArrayList<String> vacunacion5 = new ArrayList<String>();
	    vacunacion5.add("Antiplio: 15/5/2021");
	    vacunacion5.add("Antigripal: 20/10/2022");
	    agregarPaciente(5, "Esteban Pérez López", 52, vacunacion5);

	    agregarPaciente(6, "Fernando Gómez Rivas", 40, new ArrayList<>(java.util.Arrays.asList("Antigripal: 12/9/2022")));
	    agregarPaciente(7, "Gabriela Torres Martínez", 31, new ArrayList<>(java.util.Arrays.asList("Antitetánica: 3/4/2021")));
	    agregarPaciente(8, "Héctor Sánchez López", 28, new ArrayList<>(java.util.Arrays.asList("Antiplio: 15/2/2018")));
	    agregarPaciente(9, "Isabel Fernández Cruz", 37, new ArrayList<>(java.util.Arrays.asList("Antigripal: 11/11/2020", "Antitetánica: 20/7/2022")));
	    agregarPaciente(10, "Javier Morales Castillo", 50, new ArrayList<>(java.util.Arrays.asList("Antiplio: 2/6/2015")));
	    agregarPaciente(11, "Karla Ruiz Domínguez", 26, new ArrayList<>(java.util.Arrays.asList("Antigripal: 8/10/2019")));
	    agregarPaciente(12, "Luis Herrera Pérez", 33, new ArrayList<>(java.util.Arrays.asList("Antitetánica: 14/1/2020")));
	    agregarPaciente(13, "María José Salazar", 45, new ArrayList<>(java.util.Arrays.asList("Antigripal: 17/3/2021")));
	    agregarPaciente(14, "Nicolás Vega Ortega", 39, new ArrayList<>(java.util.Arrays.asList("Antiplio: 5/12/2017")));
	    agregarPaciente(15, "Olga Díaz García", 41, new ArrayList<>(java.util.Arrays.asList("Antigripal: 22/8/2020")));
	    agregarPaciente(16, "Pablo Martínez Sánchez", 34, new ArrayList<>(java.util.Arrays.asList("Antitetánica: 19/5/2019")));
	    agregarPaciente(17, "Quetzal Rojas Castillo", 29, new ArrayList<>(java.util.Arrays.asList("Antigripal: 13/7/2021")));
	    agregarPaciente(18, "Raúl López Fernández", 43, new ArrayList<>(java.util.Arrays.asList("Antiplio: 30/3/2016")));
	    agregarPaciente(19, "Sofía Medina Ramos", 27, new ArrayList<>(java.util.Arrays.asList("Antigripal: 9/9/2022")));
	    agregarPaciente(20, "Tomás Aguilar Herrera", 38, new ArrayList<>(java.util.Arrays.asList("Antitetánica: 1/2/2020")));
	    agregarPaciente(21, "Úrsula Vargas Delgado", 32, new ArrayList<>(java.util.Arrays.asList("Antiplio: 23/11/2018")));
	    agregarPaciente(22, "Víctor Salinas Mora", 47, new ArrayList<>(java.util.Arrays.asList("Antigripal: 29/6/2019")));
	    agregarPaciente(23, "Wendy Cruz López", 35, new ArrayList<>(java.util.Arrays.asList("Antitetánica: 7/8/2021")));
	    agregarPaciente(24, "Ximena Flores Castillo", 30, new ArrayList<>(java.util.Arrays.asList("Antigripal: 14/4/2022")));
	    agregarPaciente(25, "Yahir Castillo Gómez", 44, new ArrayList<>(java.util.Arrays.asList("Antiplio: 6/10/2014")));
	    agregarPaciente(26, "Zulema Navarro Ruiz", 36, new ArrayList<>(java.util.Arrays.asList("Antigripal: 18/1/2020")));
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