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

	public void crearMedico(String nombre, String apellidos, int numRegistro, String ci, String fecha) {
		this.medico = new Medico(nombre, apellidos, numRegistro, ci, fecha);
	}

	public void crearRegistroGeneral() {
		this.registroGeneral = new RegistroGeneral();
	}

	public void crearRegistroHistorico() {
		this.registroHistorico = new RegistroHistorico();
	}

	public Paciente agregarPaciente(int historiaClinicaID, String nombre, String apellido, String id) {
		Paciente newPaciente = new Paciente(historiaClinicaID, nombre, apellido, id);
		this.pacientes.add(newPaciente);
		return newPaciente;
	}

	public Mujer agregarPaciente(int historiaClinicaID, String nombre, String apellido, String ci, String fechaUltimaRevision, boolean embarazada) {
		Mujer newMujer = new Mujer(historiaClinicaID, nombre, apellido, ci, fechaUltimaRevision, embarazada);
		this.pacientes.add(newMujer);
		return newMujer;
	}


	public boolean eliminarPaciente(int id){
		boolean response = false;
		for(int i = 0; i < obtenerTotalPacientes() && !response; i++){
			if(getPacientes().get(i).getHistoriaClinicaID() == id){
				pacientes.remove(i);
				response = true;
			}
		}
		return response;
	}
	
	public void crearEnfermera(String nombre, String apellidos, int id, boolean licenciatura, int experiencia, String fecha) {
		Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
		if (this.enfermera != null) {
			throw new IllegalStateException("Ya existe una enfermera asignada a este CMF");
		}
		this.enfermera = new Enfermera(nombre, apellidos, id, licenciatura, experiencia, fecha);
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
			if (edad > 10) rangoDeEdad[0]++;
			else if (edad > 20) rangoDeEdad[1]++;
			else if (edad > 30) rangoDeEdad[2]++;
			else if (edad > 40) rangoDeEdad[3]++;
			else if (edad > 50) rangoDeEdad[4]++;
			else if (edad > 60) rangoDeEdad[5]++;
			else if (edad > 70) rangoDeEdad[6]++;
			else if (edad > 80) rangoDeEdad[7]++;
			else if (edad > 90) rangoDeEdad[8]++;
			else if (edad > 110) rangoDeEdad[9]++;
		}
		return rangoDeEdad;
	}

	public void cargarDatos() {
	    crearMedico("Alfonso", "Rodriguez Camela", 11321, "98020378176", "12/3/1998");

	    Paciente p1 = agregarPaciente(1, "Armando", "Lopez Del Toro", "78041312345");
	    p1.agregarVacuna("Antiplio: 13/4/2009");

	    Paciente p2 = agregarPaciente(2, "Amanda", "Lopez Garcia", "00041322345");
	    p2.agregarVacuna("Antiplio: 13/4/2020");

	    Paciente p3 = agregarPaciente(3, "Carlos", "Garces Fernandez", "89041312345");
	    p3.agregarVacuna("Antiplio: 13/4/2019");

	    Paciente p4 = agregarPaciente(4, "Daniela", "Suarez Molina", "95011022345");
	    p4.agregarVacuna("Antiplio: 10/1/2022");
	    p4.agregarVacuna("Antitetánica: 5/3/2023");

	    Paciente p5 = agregarPaciente(5, "Esteban", "Pérez López", "72051512345");
	    p5.agregarVacuna("Antiplio: 15/5/2021");
	    p5.agregarVacuna("Antigripal: 20/10/2022");

	    Paciente p6 = agregarPaciente(6, "Fernando", "Gómez Rivas", "85091212345");
	    p6.agregarVacuna("Antigripal: 12/9/2022");

	    Paciente p7 = agregarPaciente(7, "Gabriela", "Torres Martínez", "94040322345");
	    p7.agregarVacuna("Antitetánica: 3/4/2021");

	    Paciente p8 = agregarPaciente(8, "Héctor", "Sánchez López", "96021512345");
	    p8.agregarVacuna("Antiplio: 15/2/2018");

	    Paciente p9 = agregarPaciente(9, "Isabel", "Fernández Cruz", "87050322345");
	    p9.agregarVacuna("Antigripal: 11/11/2020");
	    p9.agregarVacuna("Antitetánica: 20/7/2022");

	    agregarPaciente(10, "Javier", "Morales Castillo", "75060212345");
	    agregarPaciente(11, "Karla", "Ruiz Domínguez", "98062122345");
	    agregarPaciente(12, "Luis", "Herrera Pérez", "92011412345");
	    agregarPaciente(13, "María José", "Salazar", "80031722345");
	    agregarPaciente(14, "Nicolás", "Vega Ortega", "84120512345");
	    agregarPaciente(15, "Olga", "Díaz García", "82082222345");
	    agregarPaciente(16, "Pablo", "Martínez Sánchez", "91051912345");
	    agregarPaciente(17, "Quetzal", "Rojas Castillo", "95071312345");
	    agregarPaciente(18, "Raúl", "López Fernández", "81033012345");
	    agregarPaciente(19, "Sofía", "Medina Ramos", "97090922345");
	    agregarPaciente(20, "Tomás", "Aguilar Herrera", "87020112345");
	    agregarPaciente(21, "Úrsula", "Vargas Delgado", "92112322345");
	    agregarPaciente(22, "Víctor", "Salinas Mora", "77062912345");
	    agregarPaciente(23, "Wendy", "Cruz López", "89080722345");
	    agregarPaciente(24, "Ximena", "Flores Castillo", "95041422345");
	    agregarPaciente(25, "Yahir", "Castillo Gómez", "80100612345");
	    agregarPaciente(26, "Zulema", "Navarro Ruiz", "88101822345");


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
		double valor = 0;
		int cantidadDeEmbarazadas = obtenerCantidadDeEmbarazadas();
		int cantidadMujeres = obtenerCantidadMujeres();
		
		if (cantidadMujeres > 0 && cantidadMujeres > 0) {
			valor = cantidadDeEmbarazadas * 100.0 / cantidadMujeres;
		}
		return valor;
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