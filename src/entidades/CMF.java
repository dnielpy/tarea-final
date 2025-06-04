package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import service.Validations;

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
		if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("Nombre no puede ser nulo o vac�o");
		if (nombre.length() > 100) throw new IllegalArgumentException("Nombre no puede exceder 100 caracteres");
		this.nombre = nombre.trim();
	}

	public void setNombreDirector(String nombreDirector) {
		if (nombreDirector == null || nombreDirector.trim().isEmpty()) throw new IllegalArgumentException("Nombre del director no puede ser nulo o vac�o");
		if (nombreDirector.length() > 100) throw new IllegalArgumentException("Nombre del director no puede exceder 100 caracteres");
		this.nombreDirector = nombreDirector.trim();
	}

	public void crearMedico(String nombre, String primerApellido, String segundoApellido, int numRegistro, String ci, String fecha) {
		this.medico = new Medico(nombre, primerApellido, segundoApellido, numRegistro, ci, fecha);
	}

	public void crearRegistroGeneral() {
		this.registroGeneral = new RegistroGeneral();
	}

	public void crearRegistroHistorico() {
		this.registroHistorico = new RegistroHistorico();
	}

	public boolean agregarPaciente(int historiaClinicaID, String nombre, String primerApellido, String segundoApellido, ArrayList<String> enfermedadesCronicas, ArrayList<String> vacunacion, String CI, boolean estaEmbarazada, String fechaUltimaRevision) {
		if (!Validations.isValidCI(CI)) {
			throw new IllegalArgumentException("CI inválido: " + CI);
		}
	
		boolean esMujer = Validations.isFemale(CI);
		boolean created = false;
		Paciente newPaciente;
	
		if (esMujer) {
			newPaciente = new Mujer(historiaClinicaID, nombre, primerApellido, segundoApellido, CI, fechaUltimaRevision, estaEmbarazada);
			if (enfermedadesCronicas != null) {
				for (String enfermedad : enfermedadesCronicas) {
					newPaciente.agregarEnfermedadCronica(enfermedad);
				}
			}
			if (vacunacion != null) {
				for (String vacuna : vacunacion) {
					newPaciente.agregarVacuna(vacuna);
				}
			}
			this.pacientes.add(newPaciente);
			created = true;
		} else {
			newPaciente = new Paciente(historiaClinicaID, nombre, primerApellido, segundoApellido, CI);
			if (enfermedadesCronicas != null) {
				for (String enfermedad : enfermedadesCronicas) {
					newPaciente.agregarEnfermedadCronica(enfermedad);
				}
			}
			if (vacunacion != null) {
				for (String vacuna : vacunacion) {
					newPaciente.agregarVacuna(vacuna);
				}
			}
			this.pacientes.add(newPaciente);
			created = true;
		}
	
		return created;
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
	
	public void crearEnfermera(String nombre, String primerApellido, String segundoApellido, int id, boolean licenciatura, int experiencia, String fecha) {
		Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
		if (this.enfermera != null) {
			throw new IllegalStateException("Ya existe una enfermera asignada a este CMF");
		}
		this.enfermera = new Enfermera(nombre, primerApellido, segundoApellido, id, licenciatura, experiencia, fecha);
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
		crearMedico("Alfonso", "Rodriguez", "Camela", 11321, "75060212345", "12/3/1998");
	
		ArrayList<String> enfermedades1 = new ArrayList<>();
		enfermedades1.add("Diabetes");
		ArrayList<String> vacunas1 = new ArrayList<>();
		vacunas1.add("Antiplio: 13/4/2009");
		agregarPaciente(1, "Armando", "Lopez", "Toro", enfermedades1, vacunas1, "78041312345", false, null);
	
		ArrayList<String> enfermedades2 = new ArrayList<>();
		enfermedades2.add("Hipertensión");
		ArrayList<String> vacunas2 = new ArrayList<>();
		vacunas2.add("Antiplio: 13/4/2020");
		agregarPaciente(2, "Amanda", "Lopez", "Garcia", enfermedades2, vacunas2, "03021178187", false, "15/03/2023");
	
		ArrayList<String> enfermedades3 = new ArrayList<>();
		enfermedades3.add("Asma");
		ArrayList<String> vacunas3 = new ArrayList<>();
		vacunas3.add("Antiplio: 13/4/2019");
		agregarPaciente(3, "Carlos", "Garces", "Fernandez", enfermedades3, vacunas3, "89041312345", false, null);
	
		ArrayList<String> enfermedades4 = new ArrayList<>();
		enfermedades4.add("Obesidad");
		ArrayList<String> vacunas4 = new ArrayList<>();
		vacunas4.add("Antiplio: 10/1/2022");
		vacunas4.add("Antitetánica: 5/3/2023");
		agregarPaciente(4, "Daniela", "Suarez", "Molina", enfermedades4, vacunas4, "95011022345", false, "20/03/2023");
	
		ArrayList<String> enfermedades5 = new ArrayList<>();
		ArrayList<String> vacunas5 = new ArrayList<>();
		vacunas5.add("Antiplio: 15/5/2021");
		vacunas5.add("Antigripal: 20/10/2022");
		agregarPaciente(5, "Esteban", "Pérez", "López", enfermedades5, vacunas5, "72051512345", false, null);
	
		ArrayList<String> enfermedades6 = new ArrayList<>();
		ArrayList<String> vacunas6 = new ArrayList<>();
		vacunas6.add("Antigripal: 12/9/2022");
		agregarPaciente(6, "Fernando", "Gómez", "Rivas", enfermedades6, vacunas6, "85091212345", false, null);
	
		ArrayList<String> enfermedades7 = new ArrayList<>();
		ArrayList<String> vacunas7 = new ArrayList<>();
		vacunas7.add("Antitetánica: 3/4/2021");
		agregarPaciente(7, "Gabriela", "Torres", "Martínez", enfermedades7, vacunas7, "94040322345", false, "05/04/2023");
	
		ArrayList<String> enfermedades8 = new ArrayList<>();
		ArrayList<String> vacunas8 = new ArrayList<>();
		vacunas8.add("Antiplio: 15/2/2018");
		agregarPaciente(8, "Héctor", "Sánchez", "López", enfermedades8, vacunas8, "96021512345", false, null);
	
		ArrayList<String> enfermedades9 = new ArrayList<>();
		ArrayList<String> vacunas9 = new ArrayList<>();
		vacunas9.add("Antigripal: 11/11/2020");
		vacunas9.add("Antitetánica: 20/7/2022");
		agregarPaciente(9, "Isabel", "Fernández", "Cruz", enfermedades9, vacunas9, "03050322345", true, "15/04/2023");
	
		ArrayList<String> enfermedades10 = new ArrayList<>();
		ArrayList<String> vacunas10 = new ArrayList<>();
		agregarPaciente(10, "Javier", "Morales", "Castillo", enfermedades10, vacunas10, "75060212345", false, null);
	
		ArrayList<String> enfermedades11 = new ArrayList<>();
		ArrayList<String> vacunas11 = new ArrayList<>();
		agregarPaciente(11, "Karla", "Ruiz", "Domínguez", enfermedades11, vacunas11, "98062122345", true, "25/04/2023");
	
		ArrayList<String> enfermedades12 = new ArrayList<>();
		ArrayList<String> vacunas12 = new ArrayList<>();
		agregarPaciente(12, "Luis", "Herrera", "Pérez", enfermedades12, vacunas12, "92011412345", false, null);
	
		ArrayList<String> enfermedades13 = new ArrayList<>();
		ArrayList<String> vacunas13 = new ArrayList<>();
		agregarPaciente(13, "María José", "Salazar", "Garcia", enfermedades13, vacunas13, "80031722345", true, "05/05/2023");
	
		ArrayList<String> enfermedades14 = new ArrayList<>();
		ArrayList<String> vacunas14 = new ArrayList<>();
		agregarPaciente(14, "Nicolás", "Vega", "Ortega", enfermedades14, vacunas14, "84120512345", false, null);
	
		ArrayList<String> enfermedades15 = new ArrayList<>();
		ArrayList<String> vacunas15 = new ArrayList<>();
		agregarPaciente(15, "Olga", "Díaz", "García", enfermedades15, vacunas15, "82082222345", false, "15/05/2023");
	
		ArrayList<String> enfermedades16 = new ArrayList<>();
		ArrayList<String> vacunas16 = new ArrayList<>();
		agregarPaciente(16, "Pablo", "Martínez", "Sánchez", enfermedades16, vacunas16, "91051912345", false, null);
	
		ArrayList<String> enfermedades17 = new ArrayList<>();
		ArrayList<String> vacunas17 = new ArrayList<>();
		agregarPaciente(17, "Quetzal", "Rojas", "Castillo", enfermedades17, vacunas17, "95071312345", false, null);
	
		ArrayList<String> enfermedades18 = new ArrayList<>();
		ArrayList<String> vacunas18 = new ArrayList<>();
		agregarPaciente(18, "Raúl", "López", "Fernández", enfermedades18, vacunas18, "81033012345", false, null);
	
		ArrayList<String> enfermedades19 = new ArrayList<>();
		ArrayList<String> vacunas19 = new ArrayList<>();
		agregarPaciente(19, "Sofía", "Medina", "Ramos", enfermedades19, vacunas19, "97090922345", false, "05/06/2023");
	
		ArrayList<String> enfermedades20 = new ArrayList<>();
		ArrayList<String> vacunas20 = new ArrayList<>();
		agregarPaciente(20, "Tomás", "Aguilar", "Herrera", enfermedades20, vacunas20, "87020112345", false, null);
	
		ArrayList<String> enfermedades21 = new ArrayList<>();
		ArrayList<String> vacunas21 = new ArrayList<>();
		agregarPaciente(21, "Úrsula", "Vargas", "Delgado", enfermedades21, vacunas21, "92112322345", true, "15/06/2023");
	
		ArrayList<String> enfermedades22 = new ArrayList<>();
		ArrayList<String> vacunas22 = new ArrayList<>();
		agregarPaciente(22, "Víctor", "Salinas", "Mora", enfermedades22, vacunas22, "77062912345", false, null);
	
		ArrayList<String> enfermedades23 = new ArrayList<>();
		ArrayList<String> vacunas23 = new ArrayList<>();
		agregarPaciente(23, "Wendy", "Cruz", "López", enfermedades23, vacunas23, "89080722345", true, "25/06/2023");
	
		ArrayList<String> enfermedades24 = new ArrayList<>();
		ArrayList<String> vacunas24 = new ArrayList<>();
		agregarPaciente(24, "Ximena", "Flores", "Castillo", enfermedades24, vacunas24, "95041422345", true, "30/06/2023");
	
		ArrayList<String> enfermedades25 = new ArrayList<>();
		ArrayList<String> vacunas25 = new ArrayList<>();
		agregarPaciente(25, "Yahir", "Castillo", "Gómez", enfermedades25, vacunas25, "80100612345", false, null);
	
		ArrayList<String> enfermedades26 = new ArrayList<>();
		ArrayList<String> vacunas26 = new ArrayList<>();
		agregarPaciente(26, "Zulema", "Navarro", "Ruiz", enfermedades26, vacunas26, "88101822345", true, "10/07/2023");
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
	        throw new IllegalArgumentException("La fecha no puede ser nula o vac�a");
	    }

	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false); // Desactiva la tolerancia a errores como 32/01/2025

	    try {
	        dateFormat.parse(fecha);
	    } catch (ParseException e) {
	        throw new IllegalArgumentException("Formato de fecha inv�lido. Use dd/MM/yyyy");
	    }

	    for (HojaCargosDiaria hoja : hojasCargoDiaria) {
	        if (hoja.getFecha().equals(fecha)) {
	            return hoja.getRegistros().size();
	        }
	    }
	    return 0;
	}


}