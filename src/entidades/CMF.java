package entidades;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import entidades.personal.Enfermera;
import entidades.personal.Medico;
import entidades.personal.Mujer;
import entidades.personal.Paciente;
import entidades.personal.Persona;
import entidades.registros.Analisis;
import entidades.registros.HistoriaClinica;
import entidades.registros.HojaCargosDiaria;
import entidades.registros.RegistroGeneral;
import entidades.registros.RegistroHistorico;
import entidades.registros.Visita;
import runner.Usuario;
import service.Validations;
import util.ConstantesAnalisis;
import util.ConstantesEspecialidades;

public class CMF {
	private static CMF instance; // Instancia unica de CMF

	private int id;
	private String nombreDelPoliclinico;
	private String nombreDirector;
	private Usuario usuario = null;
	private Medico medico;
	private Enfermera enfermera;
	private List<Paciente> pacientes;
	private List<Visita> visitas;
	private List<HojaCargosDiaria> hojasCargoDiaria;
	private RegistroGeneral registroGeneral;
	private RegistroHistorico registroHistorico;

	// Constructor privado para evitar instanciacion directa
	private CMF(int id, String nombre, String nombreDirector) {
		setId(id);
		setNombrePoliclinico(nombre);
		setNombreDirector(nombreDirector);
		hojasCargoDiaria = new ArrayList<>();
		pacientes = new ArrayList<>();
		visitas = new ArrayList<Visita>();
	}

	public static CMF getInstance() {
		if (instance == null) {
			instance = new CMF(1, "Policl\u00ednico Alberro Cotorro", "Esteban Marrero Berm\u00fadez");
			instance.cargarDatos();
		}
		return instance;
	}

	// Id del consultorio

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id <= 0)
			throw new IllegalArgumentException("ID debe ser positivo");
		this.id = id;
	}

	// Nombre del Policlinico

	public String getNombrePoliclinico() {
		return nombreDelPoliclinico;
	}

	public void setNombrePoliclinico(String nombre) {
		if (nombre == null || nombre.trim().isEmpty())
			throw new IllegalArgumentException("Nombre no puede ser nulo o vac\u00edo");
		if (nombre.length() > 100)
			throw new IllegalArgumentException("Nombre no puede exceder 100 caracteres");
		this.nombreDelPoliclinico = nombre.trim();
	}

	// Director

	public String getNombreDirector() {
		return nombreDirector;
	}

	public void setNombreDirector(String nombreDirector) {
		if (nombreDirector == null || nombreDirector.trim().isEmpty())
			throw new IllegalArgumentException("Nombre del director no puede ser nulo o vac\u00edo");
		if (nombreDirector.length() > 100)
			throw new IllegalArgumentException("Nombre del director no puede exceder 100 caracteres");
		this.nombreDirector = nombreDirector.trim();
	}

	// Usuario actual

	public Usuario getUsuario() {
		return usuario;
	}

	public Persona getEntitytyUsuario() {
		Persona personAuthenticated = null;

		if (medico != null && usuario != null && medico.getUser().getUserName().equals(usuario.getUserName())) {
			personAuthenticated = medico;
		}

		if (enfermera != null && usuario != null && enfermera.getUser().getUserName().equals(usuario.getUserName())) {
			personAuthenticated = enfermera;
		}

		return personAuthenticated;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Paciente

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public boolean agregarPaciente(String nombre, String primerApellido, String segundoApellido,
			List<String> enfermedadesCronicas, List<String> vacunacion,
			String CI, boolean estaEmbarazada, LocalDate fechaUltimaRevision, String direccion) {
		if (!Validations.isValidCI(CI)) {
			throw new IllegalArgumentException("CI inv\u00e1lido: " + CI);
		}

		int nuevoID = obtenerNuevoHistoriaClinicaID();

		boolean esMujer = Validations.isFemale(CI);
		Paciente newPaciente;

		if (esMujer) {
			newPaciente = new Mujer(nuevoID, nombre, primerApellido, segundoApellido, CI, direccion,
					fechaUltimaRevision, estaEmbarazada);
		} else {
			newPaciente = new Paciente(nuevoID, nombre, primerApellido, segundoApellido, CI, direccion);
		}

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

		pacientes.add(newPaciente);
		return true;
	}

	public boolean eliminarPaciente(int id) {
		boolean response = false;
		for (int i = 0; i < obtenerTotalPacientes() && !response; i++) {
			if (getPacientes().get(i).getHistoriaClinica().getId() == id) {
				pacientes.remove(i);
				response = true;
			}
		}
		return response;
	}

	// Medico

	public Medico getMedico() {
		return medico;
	}

	public void crearMedico(String nombre, String primerApellido, String segundoApellido, int numRegistro, String ci,
			LocalDate fecha, String email, String password) {
		medico = new Medico(nombre, primerApellido, segundoApellido, numRegistro, ci, fecha, email, password);
	}

	// Enfermera

	public Enfermera getEnfermera() {
		return this.enfermera;
	}

	public void crearEnfermera(String nombre, String primerApellido, String segundoApellido, int id, String ci,
			boolean licenciatura, int experiencia, LocalDate fecha, String email, String password) {
		Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
		if (this.enfermera != null) {
			throw new IllegalStateException("Ya existe una enfermera asignada a este CMF");
		}
		enfermera = new Enfermera(nombre, primerApellido, segundoApellido, id, ci, licenciatura, experiencia,
				fecha, email, password);
	}

	public void actualizarEnfermera(String nombre, boolean licenciatura, int experiencia, LocalDate fecha) {
		if (enfermera == null) {
			throw new IllegalStateException("No hay enfermera asignada para actualizar");
		}
		enfermera.setNombre(nombre);
		enfermera.setLicenciatura(licenciatura);
		enfermera.setExperiencia(experiencia);
		enfermera.setFechaInicio(fecha);
	}

	// Visitas

	public List<Visita> obtenerListaVisitas() {
		List<Visita> listaVisitas = new ArrayList<>();

		if (visitas != null) {
			listaVisitas = visitas;
		}

		return listaVisitas;
	}

	public int obtenerCantidadVisitasPorFecha(LocalDate fecha) {
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}

		int cantidadVisitas = 0;
		for (Visita visita : visitas) {
			if (visita.getFecha().equals(fecha)) {
				cantidadVisitas++;
			}
		}
		return cantidadVisitas;
	}

	public void agregarVisita(Visita visita) {
		Objects.requireNonNull(visita, "La visita no puede ser nula");
		for (int i = 0; i < visitas.size(); i++) {
			if (visitas.get(i).getId() == visita.getId()) {
				visitas.set(i, visita);
				return;
			}
		}
		visitas.add(visita);

		// agregar la visita a la historia clinica del paciente, y a la hoja de cargos
		// Agregar visita a la Historia Clinica

		actualizarVisitaHistoriaClinica(visita);

		HojaCargosDiaria hojaDeCargo = obtenerHojaDeCargosPorFecha(visita.getFecha());
		if (hojaDeCargo != null) {
			hojaDeCargo.agregarVisita(visita);
		} else {
			agregarHojaCargoDiaria(visita.getFecha());
			hojaDeCargo = obtenerHojaDeCargosPorFecha(visita.getFecha());
			hojaDeCargo.agregarVisita(visita);
		}
	}

	public void actualizarVisitaHistoriaClinica(Visita visita) {
		boolean encontrado = false;
		for (int i = 0; i < getPacientes().size() && !encontrado; i++) {
			if (getPacientes().get(i).getHistoriaClinica().getId() == visita.getPacienteHistoriaClinicaID()) {
				HistoriaClinica hc = getPacientes().get(i).getHistoriaClinica();

				// Buscar si ya existe la visita en la historia clínica
				boolean visitaEncontrada = false;
				List<Visita> visitasHC = hc.getRegistroVisitas(); // Supongo que tienes un getter para visitas en
																	// HistoriaClinica
				for (int j = 0; j < visitasHC.size() && !visitaEncontrada; j++) {
					if (visitasHC.get(j).getId() == visita.getId()) {
						visitasHC.set(j, visita); // Reemplaza la visita editada
						visitaEncontrada = true;
					}
				}

				// Si no se encontró, la agregamos
				if (!visitaEncontrada) {
					hc.agregarVisita(visita);
				}

				encontrado = true;
			}
		}
	}

	public boolean editarVisita(int id, Visita nuevaVisita) {
		boolean response = false;

		Objects.requireNonNull(nuevaVisita, "La nueva visita no puede ser nula");
		if (visitas == null) {
			throw new IllegalStateException("No hay visitas registradas");
		}
		for (int i = 0; i < visitas.size(); i++) {
			if (visitas.get(i).getId() == id) {
				visitas.set(i, nuevaVisita);
				response = true;
			}
		}
		return response; // No se encontro la visita con el ID especificado
	}

	public void eliminarVisita(int id) {
		if (visitas == null) {
			throw new IllegalStateException("No hay visitas registradas");
		}

		boolean eliminada = false;
		for (int i = 0; i < visitas.size() && !eliminada; i++) {
			if (visitas.get(i).getId() == id) {
				visitas.remove(i);
				eliminada = true; // Detener el ciclo
			}
		}
	}
	

	public ArrayList<Integer> obtenerVisitasPorMes() {
		ArrayList<Integer> visitasPorMes = new ArrayList<>(Collections.nCopies(12, 0));

		for (Visita visita : visitas) {
			int mes = visita.getFecha().getMonthValue();
			visitasPorMes.set(mes - 1, visitasPorMes.get(mes - 1) + 1);
		}

		return visitasPorMes;
	}
	// Obtener analisis

	public List<Analisis> obtenerTodosLosAnalisis() {
		List<Analisis> todosAnalisis = new ArrayList<>();
		if (visitas != null) {
			for (Visita visita : visitas) {
				if (visita.getAnalisis() != null) {
					todosAnalisis.addAll(visita.getAnalisis());
				}
			}
		}
		return todosAnalisis;
	}

	public List<Analisis> obtenerAnalisisPendientesDeResultado() {
		List<Analisis> pendientes = new ArrayList<>();
		if (visitas != null) {
			for (Visita visita : visitas) {
				List<Analisis> analisisVisita = visita.getAnalisis();
				if (analisisVisita != null) {
					for (Analisis a : analisisVisita) {
						if (a.getFechaResultado() == null) {
							pendientes.add(a);
						}
					}
				}
			}
		}
		return pendientes;
	}

	// Hojas de cargo

	public void agregarHojaCargoDiaria(LocalDate fecha) {
		HojaCargosDiaria hoja = new HojaCargosDiaria(fecha);
		hojasCargoDiaria.add(hoja);
	}

	// Registro General

	public void crearRegistroGeneral() {
		registroGeneral = new RegistroGeneral();
	}

	// Registro Historico

	public void crearRegistroHistorico() {
		registroHistorico = new RegistroHistorico();
	}

	// Busquedas

	public Paciente getPacientePorId(int id) {
		Paciente pac = null;
		boolean encontrado = false;

		for (int i = 0; i < pacientes.size() && !encontrado; i++) {
			if (pacientes.get(i).getHistoriaClinica().getId() == id) {
				pac = pacientes.get(i);
				encontrado = true;
			}
		}

		return pac;
	}

	public boolean isCiRepited(String ci) {
		if (ci == null || ci.trim().isEmpty()) {
			throw new IllegalArgumentException("El CI no puede ser nulo o vacío");
		}

		boolean response = false;
		for (int i = 0; i < pacientes.size() && !response; i++) {
			if (pacientes.get(i).getCI().equals(ci)) {
				response = true;
			}
		}
		return response;
	}

	public int obtenerNuevoHistoriaClinicaID() {
		int id = 1;
		boolean encontrado = true;

		while (encontrado) {
			encontrado = false;
			for (int i = 0; i < pacientes.size() && !encontrado; i++) {
				if (pacientes.get(i).getHistoriaClinica().getId() == id) {
					encontrado = true;
				}
			}
			if (encontrado) {
				id++;
			}
		}
		return id;
	}

	public int obtenerNuevoVisitaID() {
		int id = 1;
		boolean encontrado = true;

		while (encontrado) {
			encontrado = false;
			for (int i = 0; i < visitas.size(); i++) {
				Visita v = visitas.get(i);
				if (v != null && v.getId() == id) {
					encontrado = true;
				}
			}
			if (encontrado) {
				id++;
			}
		}
		return id;
	}

	public int obtenerNuevoAnalisisID() {
		int id = 1;
		boolean encontrado = true;

		while (encontrado) {
			encontrado = false;
			for (int i = 0; i < visitas.size(); i++) {
				Visita v = visitas.get(i);
				if (v != null) {
					List<Analisis> lista = v.getAnalisis();
					if (lista != null) {
						for (int j = 0; j < lista.size(); j++) {
							Analisis a = lista.get(j);
							if (a != null && a.getId() == id) {
								encontrado = true;
							}
						}
					}
				}
			}
			if (encontrado) {
				id++;
			}
		}
		return id;
	}

	public HojaCargosDiaria obtenerHojaDeCargosPorFecha(LocalDate fecha) {
		HojaCargosDiaria hojaDeCargo = null;
		boolean ciclar = true;
		for (int i = 0; i < hojasCargoDiaria.size() && ciclar; i++) {
			if (hojasCargoDiaria.get(i).getFecha().equals(fecha)) {
				hojaDeCargo = hojasCargoDiaria.get(i);
				ciclar = false;
			}
		}
		return hojaDeCargo;
	}

	public Visita obtenerVisitaPorId(int id) {
		Visita v = null;

		if (visitas == null) {
			throw new IllegalStateException("No hay visitas registradas");
		}
		for (Visita visita : visitas) {
			if (visita.getId() == id) {
				v = visita;
			}
		}
		return v; // No se encontro la visita con el ID especificado
	}

	// Cantidades

	public int obtenerTotalPacientes() {
		return this.pacientes.size();
	}

	public int obtenerCantidadMujeres() {
		int contador = 0;
		for (Paciente paciente : this.pacientes) {
			if (paciente instanceof Mujer) {
				contador++;
			}
		}
		return contador;
	}

	public int obtenerCantidadDeEmbarazadas() {
		int contador = 0;
		for (Paciente paciente : this.pacientes) {
			if (paciente instanceof Mujer) {
				if (((Mujer) paciente).isEmbarazada()) {
					contador++;
				}
			}
		}
		return contador;
	}

	// Porcentajes

	public double porcentajeMujeresRespectoAHombres() {
		double valor = 0;
		int cantidadMujeres = obtenerCantidadMujeres();
		int cantidadDePacientes = obtenerTotalPacientes();
		if (cantidadDePacientes > 0 && cantidadMujeres > 0) {
			valor = cantidadMujeres * 100.0 / cantidadDePacientes;
		}
		return Math.round(valor * 10.0) / 10.0;
	}

	public float obtenerPorcientoEmbarazadasEnRiesgo() {
		if (pacientes == null)
			throw new IllegalArgumentException("Lista de pacientes no puede ser nula");
		float embarazadasEnRiesgo = 0;
		for (Paciente paciente : pacientes) {
			if (paciente instanceof Mujer && ((Mujer) paciente).isEmbarazada() && paciente.estaEnRiesgo()) {
				embarazadasEnRiesgo++;
			}
		}
		return embarazadasEnRiesgo;
	}

	public double porcentajeEmbarazadasRespectoAMujeres() {
		double valor = 0;
		int cantidadDeEmbarazadas = obtenerCantidadDeEmbarazadas();
		int cantidadMujeres = obtenerCantidadMujeres();

		if (cantidadDeEmbarazadas > 0 && cantidadMujeres > 0) {
			valor = cantidadDeEmbarazadas * 100.0 / cantidadMujeres;
		}
		return Math.round(valor * 10.0) / 10.0;
	}

	public double porcentajeEnRiesgoDelTotal() {
		double valor = 0;
		int cantidadEnRiesgo = obtenerPacientesEnRiesgo();
		int cantidadPacientes = obtenerTotalPacientes();

		if (cantidadEnRiesgo > 0 && cantidadPacientes > 0) {
			valor = cantidadEnRiesgo * 100.0 / cantidadPacientes;
		}
		return Math.round(valor * 10.0) / 10.0;
	}

	// Reportes

	public List<Paciente> obtenerEmbarazadasEnRiesgo() {
		if (pacientes == null)
			throw new IllegalArgumentException("La lista de pacientes no puede ser nula");
		List<Paciente> embarazadasEnRiesgo = new ArrayList<>();
		for (Paciente paciente : pacientes) {
			if (paciente instanceof Mujer) {
				Mujer mujer = (Mujer) paciente;
				if (mujer.isEmbarazada() && mujer.estaEnRiesgo()) {
					embarazadasEnRiesgo.add(mujer);
				}
			}
		}
		return embarazadasEnRiesgo;
	}

	public int[] obtenerRangosDeEdad() {
		if (pacientes == null)
			throw new IllegalArgumentException("Lista de pacientes no puede ser nula");
		int[] rangoDeEdad = new int[10];
		for (Paciente paciente : pacientes) {
			int edad = paciente.getEdad();
			if (edad <= 10)
				rangoDeEdad[0]++;
			else if (edad <= 20)
				rangoDeEdad[1]++;
			else if (edad <= 30)
				rangoDeEdad[2]++;
			else if (edad <= 40)
				rangoDeEdad[3]++;
			else if (edad <= 50)
				rangoDeEdad[4]++;
			else if (edad <= 60)
				rangoDeEdad[5]++;
			else if (edad <= 70)
				rangoDeEdad[6]++;
			else if (edad <= 80)
				rangoDeEdad[7]++;
			else if (edad <= 90)
				rangoDeEdad[8]++;
			else
				rangoDeEdad[9]++;
		}
		return rangoDeEdad;
	}

	public int obtenerPacientesEnRiesgo() {
		int pacientesEnRiesgo = 0;
		for (Paciente paciente : this.pacientes) {
			if (paciente.estaEnRiesgo()) {
				pacientesEnRiesgo++;
			}
		}
		return pacientesEnRiesgo;
	}

	public int obtenerCantidadPacientesPorFecha(Date fecha) {
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula o vac\u00eda");
		}

		int visitas = 0;

		boolean ciclar = true;
		for (int i = 0; i < hojasCargoDiaria.size() && ciclar; i++) {
			if (hojasCargoDiaria.get(i).getFecha().equals(fecha)) {
				visitas = hojasCargoDiaria.get(i).getVisitas().size();
				ciclar = false;
			}
		}
		return visitas;
	}

	// Datos cableados

	public void cargarDatos() {
		LocalDate fechaBase = LocalDate.of(1960, 1, 1);

		crearMedico("Alfonso", "Rodr\u00EDguez", "Camela", 11321, "75060212345", generarFechaRandomDesde(fechaBase),
				"medico@example.com", "12345678");
		crearEnfermera("Maria", "Hernandez", "Rodriguez", 43, "98121296831", true, 4,
				generarFechaRandomDesde(fechaBase), "enfermera@example.com", "12345678");

		crearPacienteConDatos("Armando", "L\u00F3pez", "Toro", "78041312345", false, null, generarDireccionCuba());
		crearPacienteConDatos("Amanda", "L\u00F3pez", "Garc\u00EDa", "03021178136", false,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Carlos", "Garces", "Fern\u00E1ndez", "89041312325", false, null, generarDireccionCuba());
		crearPacienteConDatos("Daniela", "Su\u00E1rez", "Molina", "95011022314", false,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Esteban", "P\u00E9rez", "L\u00F3pez", "72051512365", false, null,
				generarDireccionCuba());
		crearPacienteConDatos("Fernando", "G\u00F3mez", "Rivas", "85091212385", false, null, generarDireccionCuba());
		crearPacienteConDatos("Gabriela", "Torres", "Mart\u00EDnez", "94040322374", true,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("H\u00E9ctor", "S\u00E1nchez", "L\u00F3pez", "96021512365", false, null,
				generarDireccionCuba());
		crearPacienteConDatos("Isabel", "Fern\u00E1ndez", "Cruz", "03050322394", true,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Javier", "Morales", "Castillo", "75060212345", false, null, generarDireccionCuba());
		crearPacienteConDatos("Karla", "Ruiz", "Dom\u00EDnguez", "98062122314", true,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Luis", "Herrera", "P\u00E9rez", "92011412365", false, null, generarDireccionCuba());
		crearPacienteConDatos("Mar\u00EDa Jos\u00E9", "Salazar", "Garc\u00EDa", "80031722354", true,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Nicol\u00E1s", "Vega", "Ortega", "84120512385", false, null, generarDireccionCuba());
		crearPacienteConDatos("Olga", "D\u00EDaz", "Garc\u00EDa", "82082222334", false,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Pablo", "Mart\u00EDnez", "S\u00E1nchez", "91051912365", false, null,
				generarDireccionCuba());
		crearPacienteConDatos("Quetzal", "Rojas", "Castillo", "95071312365", false, null, generarDireccionCuba());
		crearPacienteConDatos("Ra\u00FAl", "L\u00F3pez", "Fern\u00E1ndez", "81033012325", false, null,
				generarDireccionCuba());
		crearPacienteConDatos("Sof\u00EDa", "Medina", "Ramos", "97090922394", false,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Tom\u00E1s", "Aguilar", "Herrera", "87020112345", false, null, generarDireccionCuba());
		crearPacienteConDatos("\u00DArsula", "Vargas", "Delgado", "92112322334", true,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("V\u00EDctor", "Salinas", "Mora", "77062912385", false, null, generarDireccionCuba());
		crearPacienteConDatos("Wendy", "Cruz", "L\u00F3pez", "89080722374", true,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Ximena", "Flores", "Castillo", "95041422334", true,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());
		crearPacienteConDatos("Yahir", "Castillo", "G\u00F3mez", "80100612365", false, null, generarDireccionCuba());
		crearPacienteConDatos("Zulema", "Navarro", "Ruiz", "88101822374", true,
				generarFechaRandomDesde(fechaBase), generarDireccionCuba());

		fechaBase = LocalDate.now().minusDays(3);
		LocalDate fecha;

		fecha = generarFechaRandomDesde(fechaBase);
		int idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 1, fecha, "Resfriado común",
				"Paracetamol", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 1),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 2, fecha, "Dolor de cabeza",
				"Ibuprofeno", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 2),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 3, fecha, "Tos persistente",
				"Jarabe expectorante", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 3),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 4, fecha, "Dolor abdominal",
				"Omeprazol", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 4),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 5, fecha, "Presión alta",
				"Enalapril", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 5),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 6, fecha, "Dolor muscular",
				"Diclofenaco", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 6),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 7, fecha, "Control prenatal",
				"Suplementos vitamínicos", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 7),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 8, fecha, "Alergia estacional",
				"Antihistamínicos", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 8),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 9, fecha, "Control prenatal",
				"Suplementos vitamínicos", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 9),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));

		fecha = generarFechaRandomDesde(fechaBase);
		idVisita = obtenerNuevoVisitaID();
		agregarVisita(new Visita(idVisita, 10, fecha, "Dolor lumbar",
				"Fisioterapia", generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 10),
				generarEspecialidadesAleatorias(),
				generarDireccionCuba()));
	}

	private void crearPacienteConDatos(String nombre, String primerApellido, String segundoApellido, String ci,
			boolean esMujer, LocalDate fecha, String direccion) {
		List<String> enfermedades = generarEnfermedadesCronicasAleatorias();
		List<String> vacunas = generarVacunasAleatorias();
		agregarPaciente(nombre, primerApellido, segundoApellido, enfermedades, vacunas, ci, esMujer, fecha, direccion);
	}

	private List<String> generarEspecialidadesAleatorias() {
		List<String> lista = new ArrayList<>();
		int cantidad = random.nextInt(ConstantesEspecialidades.ESPECIALIDADES_REMITIDAS.size() + 1); // de 0 a tamaño
																										// máximo

		// Para evitar repeticiones, tomamos una copia y la mezclamos
		List<String> copia = new ArrayList<>(ConstantesEspecialidades.ESPECIALIDADES_REMITIDAS);
		Collections.shuffle(copia, random);

		for (int i = 0; i < cantidad; i++) {
			lista.add(copia.get(i));
		}
		return lista;
	}

	private List<Analisis> generarAnalisisParaVisitaConIdVisita(LocalDate fechaVisita, int idVisita,
			int IdHistoriaClinica) {
		List<Analisis> listaAnalisis = new ArrayList<>();

		if (random.nextDouble() > 0.4) {
			int cantAnalisis = 1 + random.nextInt(3);
			for (int i = 0; i < cantAnalisis; i++) {
				int idAnalisis = obtenerNuevoAnalisisID();
				String tipo = ConstantesAnalisis.TIPOS_ANALISIS
						.get(random.nextInt(ConstantesAnalisis.TIPOS_ANALISIS.size()));
				LocalDate fechaOrientado = fechaVisita;

				Analisis analisis = new Analisis(idAnalisis, tipo, fechaOrientado, idVisita, IdHistoriaClinica);

				if (random.nextBoolean()) {
					LocalDate fechaResultado = fechaOrientado.plusDays(1 + random.nextInt(10));
					if (fechaResultado.isAfter(LocalDate.now())) {
						fechaResultado = LocalDate.now();
					}
					analisis.setFechaResultado(fechaResultado);
					analisis.setResultados("Resultados normales para " + tipo);
				}
				listaAnalisis.add(analisis);
			}
		}
		return listaAnalisis;
	}

	// Metodos para randomizar datos

	public static LocalDate generarFechaRandomDesde(LocalDate start) {
		Objects.requireNonNull(start, "La fecha inicial no puede ser nula");

		LocalDate end = LocalDate.now();

		if (start.isAfter(end)) {
			throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha actual");
		}

		long daysBetween = ChronoUnit.DAYS.between(start, end);
		long randomDays = ThreadLocalRandom.current().nextLong(daysBetween + 1);

		return start.plusDays(randomDays);
	}

	private static final Random random = new Random();

	private static String generarDireccionCuba() {
		Map<String, String> calleMunicipio = new HashMap<>();
		calleMunicipio.put("Calle 23", "Plaza de la Revolución");
		calleMunicipio.put("Avenida Boyeros", "Boyeros");
		calleMunicipio.put("Calle Línea", "Plaza de la Revolución");
		calleMunicipio.put("Calle Monte", "Centro Habana");
		calleMunicipio.put("Avenida 5ta", "Playa");
		calleMunicipio.put("Calle Obispo", "La Habana Vieja");
		calleMunicipio.put("Calle Enramadas", "Habana del Este");

		List<String> calles = new ArrayList<>(calleMunicipio.keySet());
		String calle = calles.get(random.nextInt(calles.size()));
		String municipio = calleMunicipio.get(calle);
		String provincia = "La Habana";

		String direccion = calle + " #" + (100 + random.nextInt(900));
		return direccion + ", " + municipio + ", " + provincia;
	}

	private List<String> generarEnfermedadesCronicasAleatorias() {
		List<String> posiblesEnfermedades = Arrays.asList(
				"Diabetes",
				"Hipertensi\u00F3n",
				"Asma",
				"Obesidad",
				"Enfermedad card\u00EDaca",
				"Artritis",
				"EPOC");

		List<String> enfermedadesAsignadas = new ArrayList<>();
		int cantidad = random.nextInt(3); // 0, 1 o 2 enfermedades

		Collections.shuffle(posiblesEnfermedades, random); // Mejor usar tu instancia

		for (int i = 0; i < cantidad; i++) {
			enfermedadesAsignadas.add(posiblesEnfermedades.get(i));
		}

		return enfermedadesAsignadas;
	}

	private List<String> generarVacunasAleatorias() {
		List<String> posiblesVacunas = Arrays.asList(
				"Antipolio",
				"Antitet\u00E1nica",
				"Antigripal",
				"Hepatitis B");

		List<String> vacunasAsignadas = new ArrayList<>();
		int cantidad = random.nextInt(3); // 0, 1 o 2 vacunas

		Collections.shuffle(posiblesVacunas);

		for (int i = 0; i < cantidad; i++) {
			String vacuna = posiblesVacunas.get(i);
			int anio = 2015 + random.nextInt(9);
			int mes = 1 + random.nextInt(12);
			int dia = 1 + random.nextInt(28);
			vacunasAsignadas.add(vacuna + ": " + dia + "/" + mes + "/" + anio);
		}

		return vacunasAsignadas;
	}
}