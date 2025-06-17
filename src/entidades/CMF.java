package entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import runner.Usuario;
import service.Validations;

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

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Paciente

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public boolean agregarPaciente(String nombre, String primerApellido, String segundoApellido,
			List<String> enfermedadesCronicas, List<String> vacunacion,
			String CI, boolean estaEmbarazada, Date fechaUltimaRevision, String direccion) {
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
		return this.medico;
	}

	public void crearMedico(String nombre, String primerApellido, String segundoApellido, int numRegistro, String ci,
			Date fecha, String email, String password) {
		medico = new Medico(nombre, primerApellido, segundoApellido, numRegistro, ci, fecha, email, password);
	}

	// Enfermera

	public Enfermera getEnfermera() {
		return this.enfermera;
	}

	public void crearEnfermera(String nombre, String primerApellido, String segundoApellido, int id, String ci,
			boolean licenciatura, int experiencia, Date fecha, String email, String password) {
		Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
		if (this.enfermera != null) {
			throw new IllegalStateException("Ya existe una enfermera asignada a este CMF");
		}
		this.enfermera = new Enfermera(nombre, primerApellido, segundoApellido, id, ci, licenciatura, experiencia,
				fecha, email, password);
	}

	public void actualizarEnfermera(String nombre, boolean licenciatura, int experiencia, Date fecha) {
		if (this.enfermera == null) {
			throw new IllegalStateException("No hay enfermera asignada para actualizar");
		}
		this.enfermera.setNombre(nombre);
		this.enfermera.setLicenciatura(licenciatura);
		this.enfermera.setExperiencia(experiencia);
		this.enfermera.setFechaInicio(fecha);
	}

	// Visitas

	public List<Visita> obtenerListaVisitas() {
		List<Visita> listaVisitas = new ArrayList<>();

		if (visitas != null) {
			listaVisitas = visitas;
		}

		return listaVisitas;
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
		for (Paciente x : pacientes) {
			if (x.getHistoriaClinica().getId() == visita.getPacienteHistoriaClinicaID()) {
				x.getHistoriaClinica().agregarVisita(visita);
			}
		}
		HojaCargosDiaria hojaDeCargo = obtenerHojaDeCargosPorFecha(visita.getFecha());
		if (hojaDeCargo != null) {
			hojaDeCargo.agregarVisita(visita);
		} else {
			agregarHojaCargoDiaria(visita.getFecha());
			hojaDeCargo = obtenerHojaDeCargosPorFecha(visita.getFecha());
			hojaDeCargo.agregarVisita(visita);
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

		for (int i = 0; i < visitas.size(); i++) {
			if (visitas.get(i).getId() == id) {
				visitas.remove(i);
				break; // Salir del bucle después de eliminar la visita
			}
		}
	}

	// Hojas de cargo

	public void agregarHojaCargoDiaria(Date fecha) {
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

		for (Paciente paciente : pacientes) {
			if (paciente.getHistoriaClinica().getId() == id) {
				pac = paciente;
			}
		}
		return pac;
	}

	public boolean isCiRepited(String ci) {
		boolean response = false;

		if (ci == null || ci.trim().isEmpty()) {
			throw new IllegalArgumentException("El CI no puede ser nulo o vac\u00edo");
		}
		for (Paciente paciente : pacientes) {
			if (paciente.getCI().equals(ci)) {
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
			for (Paciente paciente : pacientes) {
				if (paciente.getHistoriaClinica().getId() == id) {
					encontrado = true;
				}
			}
			if (encontrado) {
				id++;
			}
		}
		return id;
	}

	public HojaCargosDiaria obtenerHojaDeCargosPorFecha(Date fecha) {
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
		crearMedico("Alfonso", "Rodr\u00EDguez", "Camela", 11321, "75060212345", generarFechaRandom(),
				"medico@example.com", "12345678");
		crearEnfermera("Maria", "Hernandez", "Rodriguez", 43, "98121296831", true, 4, generarFechaRandom(),
				"enfermera@example.com", "12345678");

		crearPacienteConDatos("Armando", "L\u00F3pez", "Toro", "78041312345", false, null, generarDireccionCuba());
		crearPacienteConDatos("Amanda", "L\u00F3pez", "Garc\u00EDa", "03021178136", false, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("Carlos", "Garces", "Fern\u00E1ndez", "89041312325", false, null, generarDireccionCuba());
		crearPacienteConDatos("Daniela", "Su\u00E1rez", "Molina", "95011022314", false, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("Esteban", "P\u00E9rez", "L\u00F3pez", "72051512365", false, null,
				generarDireccionCuba());
		crearPacienteConDatos("Fernando", "G\u00F3mez", "Rivas", "85091212385", false, null, generarDireccionCuba());
		crearPacienteConDatos("Gabriela", "Torres", "Mart\u00EDnez", "94040322374", true, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("H\u00E9ctor", "S\u00E1nchez", "L\u00F3pez", "96021512365", false, null,
				generarDireccionCuba());
		crearPacienteConDatos("Isabel", "Fern\u00E1ndez", "Cruz", "03050322394", true, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("Javier", "Morales", "Castillo", "75060212345", false, null, generarDireccionCuba());
		crearPacienteConDatos("Karla", "Ruiz", "Dom\u00EDnguez", "98062122314", true, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("Luis", "Herrera", "P\u00E9rez", "92011412365", false, null, generarDireccionCuba());
		crearPacienteConDatos("Mar\u00EDa Jos\u00E9", "Salazar", "Garc\u00EDa", "80031722354", true,
				generarFechaRandom(), generarDireccionCuba());
		crearPacienteConDatos("Nicol\u00E1s", "Vega", "Ortega", "84120512385", false, null, generarDireccionCuba());
		crearPacienteConDatos("Olga", "D\u00EDaz", "Garc\u00EDa", "82082222334", false, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("Pablo", "Mart\u00EDnez", "S\u00E1nchez", "91051912365", false, null,
				generarDireccionCuba());
		crearPacienteConDatos("Quetzal", "Rojas", "Castillo", "95071312365", false, null, generarDireccionCuba());
		crearPacienteConDatos("Ra\u00FAl", "L\u00F3pez", "Fern\u00E1ndez", "81033012325", false, null,
				generarDireccionCuba());
		crearPacienteConDatos("Sof\u00EDa", "Medina", "Ramos", "97090922394", false, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("Tom\u00E1s", "Aguilar", "Herrera", "87020112345", false, null, generarDireccionCuba());
		crearPacienteConDatos("\u00DArsula", "Vargas", "Delgado", "92112322334", true, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("V\u00EDctor", "Salinas", "Mora", "77062912385", false, null, generarDireccionCuba());
		crearPacienteConDatos("Wendy", "Cruz", "L\u00F3pez", "89080722374", true, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("Ximena", "Flores", "Castillo", "95041422334", true, generarFechaRandom(),
				generarDireccionCuba());
		crearPacienteConDatos("Yahir", "Castillo", "G\u00F3mez", "80100612365", false, null, generarDireccionCuba());
		crearPacienteConDatos("Zulema", "Navarro", "Ruiz", "88101822374", true, generarFechaRandom(),
				generarDireccionCuba());

		agregarVisita(new Visita(1, 1, generarFechaRandom(), "Diagnóstico: Resfriado común",
				"Tratamiento: Paracetamol", new Analisis("Sangre", null), "Medicina General",
				"Calle 23 #123, Plaza, La Habana"));
		agregarVisita(new Visita(2, 2, generarFechaRandom(), "Diagnóstico: Dolor de cabeza",
				"Tratamiento: Ibuprofeno", new Analisis("Orina", null), "Neurología",
				"Avenida Boyeros #456, Centro Habana, La Habana"));
		agregarVisita(new Visita(3, 3, generarFechaRandom(), "Diagnóstico: Tos persistente",
				"Tratamiento: Jarabe expectorante", new Analisis("Radiografía", null), "Neumología",
				"Calle Línea #789, Marianao, La Habana"));
		agregarVisita(new Visita(4, 4, generarFechaRandom(), "Diagnóstico: Dolor abdominal",
				"Tratamiento: Omeprazol", new Analisis("Otro", null), "Gastroenterología",
				"Calle Monte #321, Cerro, La Habana"));
		agregarVisita(new Visita(5, 5, generarFechaRandom(), "Diagnóstico: Presión alta",
				"Tratamiento: Enalapril", new Analisis("Sangre", null), "Cardiología",
				"Avenida 5ta #654, Vedado, La Habana"));
		agregarVisita(new Visita(6, 6, generarFechaRandom(), "Diagnóstico: Dolor muscular",
				"Tratamiento: Diclofenaco", new Analisis("Radiografía", null), "Ortopedia",
				"Calle Obispo #987, Guanabacoa, La Habana"));
		agregarVisita(new Visita(7, 7, generarFechaRandom(), "Diagnóstico: Control prenatal",
				"Tratamiento: Suplementos vitamínicos", new Analisis("Otro", null), "Obstetricia",
				"Calle Enramadas #456, Habana del Este, La Habana"));
		agregarVisita(new Visita(8, 8, generarFechaRandom(), "Diagnóstico: Alergia estacional",
				"Tratamiento: Antihistamínicos", new Analisis("Sangre", null), "Alergología",
				"Calle 23 #789, Plaza, La Habana"));
		agregarVisita(new Visita(9, 9, generarFechaRandom(), "Diagnóstico: Control prenatal",
				"Tratamiento: Suplementos vitamínicos", new Analisis("Orina", null), "Obstetricia",
				"Avenida Boyeros #123, Centro Habana, La Habana"));
		agregarVisita(new Visita(10, 10, generarFechaRandom(), "Diagnóstico: Dolor lumbar",
				"Tratamiento: Fisioterapia", null, "Rehabilitación",
				"Calle Línea #456, Marianao, La Habana"));

	}

	private void crearPacienteConDatos(String nombre, String primerApellido, String segundoApellido, String ci,
			boolean esMujer, Date fecha, String direccion) {
		List<String> enfermedades = generarEnfermedadesCronicasAleatorias();
		List<String> vacunas = generarVacunasAleatorias();
		agregarPaciente(nombre, primerApellido, segundoApellido, enfermedades, vacunas, ci, esMujer, fecha, direccion);
	}

	// Metodos para randomizar datos

	public static Date generarFechaRandom() {
		Calendar calendar = Calendar.getInstance();

		// Fecha inicio fija
		calendar.set(1950, 0, 1);
		long startMillis = calendar.getTimeInMillis();

		// Fecha fin: fecha actual
		long endMillis = Calendar.getInstance().getTimeInMillis();

		// Generar milisegundos random entre las dos fechas
		long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

		return new Date(randomMillisSinceEpoch);
	}

	private static final Random rand = new Random();

	private static String generarDireccionCuba() {
		String[] calles = { "Calle 23", "Avenida Boyeros", "Calle L\u00ednea", "Calle Monte", "Avenida 5ta",
				"Calle Obispo", "Calle Enramadas" };
		String[] municipios = { "Plaza", "Centro Habana", "Marianao", "Cerro", "Vedado", "Guanabacoa",
				"Habana del Este" };
		String[] provincias = { "La Habana", "Santiago de Cuba", "Camag\u00fcey", "Holgu\u00edn", "Villa Clara",
				"Cienfuegos", "Pinar del Rio" };

		String calle = calles[rand.nextInt(calles.length)] + " #" + (100 + rand.nextInt(900));
		String municipio = municipios[rand.nextInt(municipios.length)];
		String provincia = provincias[rand.nextInt(provincias.length)];

		return calle + ", " + municipio + ", " + provincia;
	}

	private Random random = new Random();

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

		Collections.shuffle(posiblesEnfermedades);

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