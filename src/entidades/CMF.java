package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import service.Validations;

public class CMF {
	private static CMF instance; // Instancia �nica de CMF

	private int id;
	private String nombre;
	private String nombreDirector;
	private List<HojaCargosDiaria> hojasCargoDiaria;
	private List<Paciente> pacientes;
	private Medico medico;
	private RegistroGeneral registroGeneral;
	private RegistroHistorico registroHistorico;
	private Enfermera enfermera;

	// Constructor privado para evitar instanciaci�n directa
	private CMF(int id, String nombre, String nombreDirector) {
		setId(id);
		setNombre(nombre);
		setNombreDirector(nombreDirector);
		this.hojasCargoDiaria = new ArrayList<>();
		this.pacientes = new ArrayList<>();
	}

	public static CMF getInstance() {
		if (instance == null) {
			instance = new CMF(1, "Policl�nico Alberro Cotorro", "Esteban Marrero Berm�dez");
			instance.cargarDatos(); 
		}
		return instance;
	}
	
	public List<Paciente> getPacientes() {
		return pacientes;
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

	public Enfermera getEnfermera() {
		return this.enfermera;
	}

	public Medico getMedico() {
		return this.medico;
	}

	public Paciente getPacientePorId(int id) {
		Paciente pac = null;
		
		for (Paciente paciente : pacientes) {
			if (paciente.getHistoriaClinica().getId() == id) {
				pac = paciente;
			}
		}
		return pac; // Return null if no patient is found with the given ID
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

	public void crearMedico(String nombre, String primerApellido, String segundoApellido, int numRegistro, String ci, Date fecha, String email, String password) {
		medico = new Medico(nombre, primerApellido, segundoApellido, numRegistro, ci, fecha, email, password);
	}

	public void crearRegistroGeneral() {
		this.registroGeneral = new RegistroGeneral();
	}

	public void crearRegistroHistorico() {
		this.registroHistorico = new RegistroHistorico();
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
	
	public boolean agregarPaciente(String nombre, String primerApellido, String segundoApellido,
			List<String> enfermedadesCronicas, List<String> vacunacion,
			String CI, boolean estaEmbarazada, Date fechaUltimaRevision, String direccion) {
		if (!Validations.isValidCI(CI)) {
			throw new IllegalArgumentException("CI inv�lido: " + CI);
		}

		int nuevoID = obtenerNuevoHistoriaClinicaID();

		boolean esMujer = Validations.isFemale(CI);
		Paciente newPaciente;

		if (esMujer) {
			newPaciente = new Mujer(nuevoID, nombre, primerApellido, segundoApellido, CI, direccion, fechaUltimaRevision, estaEmbarazada);
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

	public void crearEnfermera(String nombre, String primerApellido, String segundoApellido, int id, String ci,boolean licenciatura, int experiencia, Date fecha, String email, String password) {
		Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
		if (this.enfermera != null) {
			throw new IllegalStateException("Ya existe una enfermera asignada a este CMF");
		}
		this.enfermera = new Enfermera(nombre, primerApellido, segundoApellido, id, ci, licenciatura, experiencia, fecha, email, password);
	}

	public void agregarHojaCargoDiaria(Date fecha) {
		HojaCargosDiaria hoja = new HojaCargosDiaria(fecha);
		this.hojasCargoDiaria.add(hoja);
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

	public boolean isCiRepited(String ci) {
		boolean response = false;

		if (ci == null || ci.trim().isEmpty()) {
			throw new IllegalArgumentException("El CI no puede ser nulo o vac�o");
		}
		for (Paciente paciente : pacientes) {
			if (paciente.getCI().equals(ci)) {
				response = true;
			}
		}
		return response;
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
		if (pacientes == null) throw new IllegalArgumentException("Lista de pacientes no puede ser nula");
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
	    if (pacientes == null) throw new IllegalArgumentException("La lista de pacientes no puede ser nula");    
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
		if (pacientes == null) throw new IllegalArgumentException("Lista de pacientes no puede ser nula");
		int[] rangoDeEdad = new int[10];
		for (Paciente paciente : pacientes) {
			int edad = paciente.getEdad();
			if (edad <= 10) rangoDeEdad[0]++;
            else if (edad <= 20) rangoDeEdad[1]++;
            else if (edad <= 30) rangoDeEdad[2]++;
            else if (edad <= 40) rangoDeEdad[3]++;
            else if (edad <= 50) rangoDeEdad[4]++;
            else if (edad <= 60) rangoDeEdad[5]++;
            else if (edad <= 70) rangoDeEdad[6]++;
            else if (edad <= 80) rangoDeEdad[7]++;
            else if (edad <= 90) rangoDeEdad[8]++;
            else rangoDeEdad[9]++;
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
			throw new IllegalArgumentException("La fecha no puede ser nula o vac�a");
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
	    String[] calles = {"Calle 23", "Avenida Boyeros", "Calle L�nea", "Calle Monte", "Avenida 5ta", "Calle Obispo", "Calle Enramadas"};
	    String[] municipios = {"Plaza", "Centro Habana", "Marianao", "Cerro", "Vedado", "Guanabacoa", "Habana del Este"};
	    String[] provincias = {"La Habana", "Santiago de Cuba", "Camag�ey", "Holgu�n", "Villa Clara", "Cienfuegos", "Pinar del R�o"};

	    String calle = calles[rand.nextInt(calles.length)] + " #" + (100 + rand.nextInt(900));
	    String municipio = municipios[rand.nextInt(municipios.length)];
	    String provincia = provincias[rand.nextInt(provincias.length)];

	    return calle + ", " + municipio + ", " + provincia;
	}
	
	public void cargarDatos() {
	    crearMedico("Alfonso", "Rodr\u00EDguez", "Camela", 11321, "75060212345", generarFechaRandom(), "admin@example.com", "12345678");

	    List<String> enfermedades1 = new ArrayList<>();
	    enfermedades1.add("Diabetes");
	    List<String> vacunas1 = new ArrayList<>();
	    vacunas1.add("Antipolio: 13/4/2009");
	    agregarPaciente("Armando", "L\u00F3pez", "Toro", enfermedades1, vacunas1, "78041312345", false, null, generarDireccionCuba());

	    List<String> enfermedades2 = new ArrayList<>();
	    enfermedades2.add("Hipertensi\u00F3n");
	    List<String> vacunas2 = new ArrayList<>();
	    vacunas2.add("Antipolio: 13/4/2020");
	    agregarPaciente("Amanda", "L\u00F3pez", "Garc\u00EDa", enfermedades2, vacunas2, "03021178187", false, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades3 = new ArrayList<>();
	    enfermedades3.add("Asma");
	    List<String> vacunas3 = new ArrayList<>();
	    vacunas3.add("Antipolio: 13/4/2019");
	    agregarPaciente("Carlos", "Garces", "Fern\u00E1ndez", enfermedades3, vacunas3, "89041312345", false, null, generarDireccionCuba());

	    List<String> enfermedades4 = new ArrayList<>();
	    enfermedades4.add("Obesidad");
	    List<String> vacunas4 = new ArrayList<>();
	    vacunas4.add("Antipolio: 10/1/2022");
	    vacunas4.add("Antitet\u00E1nica: 5/3/2023");
	    agregarPaciente("Daniela", "Su\u00E1rez", "Molina", enfermedades4, vacunas4, "95011022345", false, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades5 = new ArrayList<>();
	    List<String> vacunas5 = new ArrayList<>();
	    vacunas5.add("Antipolio: 15/5/2021");
	    vacunas5.add("Antigripal: 20/10/2022");
	    agregarPaciente("Esteban", "P\u00E9rez", "L\u00F3pez", enfermedades5, vacunas5, "72051512345", false, null, generarDireccionCuba());

	    List<String> enfermedades6 = new ArrayList<>();
	    List<String> vacunas6 = new ArrayList<>();
	    vacunas6.add("Antigripal: 12/9/2022");
	    agregarPaciente("Fernando", "G\u00F3mez", "Rivas", enfermedades6, vacunas6, "85091212345", false, null, generarDireccionCuba());

	    List<String> enfermedades7 = new ArrayList<>();
	    List<String> vacunas7 = new ArrayList<>();
	    vacunas7.add("Antitet\u00E1nica: 3/4/2021");
	    agregarPaciente("Gabriela", "Torres", "Mart\u00EDnez", enfermedades7, vacunas7, "94040322345", false, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades8 = new ArrayList<>();
	    List<String> vacunas8 = new ArrayList<>();
	    vacunas8.add("Antipolio: 15/2/2018");
	    agregarPaciente("H\u00E9ctor", "S\u00E1nchez", "L\u00F3pez", enfermedades8, vacunas8, "96021512345", false, null, generarDireccionCuba());

	    List<String> enfermedades9 = new ArrayList<>();
	    List<String> vacunas9 = new ArrayList<>();
	    vacunas9.add("Antigripal: 11/11/2020");
	    vacunas9.add("Antitet\u00E1nica: 20/7/2022");
	    agregarPaciente("Isabel", "Fern\u00E1ndez", "Cruz", enfermedades9, vacunas9, "03050322345", true, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades10 = new ArrayList<>();
	    List<String> vacunas10 = new ArrayList<>();
	    agregarPaciente("Javier", "Morales", "Castillo", enfermedades10, vacunas10, "75060212345", false, null, generarDireccionCuba());

	    List<String> enfermedades11 = new ArrayList<>();
	    List<String> vacunas11 = new ArrayList<>();
	    agregarPaciente("Karla", "Ruiz", "Dom\u00EDnguez", enfermedades11, vacunas11, "98062122345", true, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades12 = new ArrayList<>();
	    List<String> vacunas12 = new ArrayList<>();
	    agregarPaciente("Luis", "Herrera", "P\u00E9rez", enfermedades12, vacunas12, "92011412345", false, null, generarDireccionCuba());

	    List<String> enfermedades13 = new ArrayList<>();
	    List<String> vacunas13 = new ArrayList<>();
	    agregarPaciente("Mar\u00EDa Jos\u00E9", "Salazar", "Garc\u00EDa", enfermedades13, vacunas13, "80031722345", true, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades14 = new ArrayList<>();
	    List<String> vacunas14 = new ArrayList<>();
	    agregarPaciente("Nicol\u00E1s", "Vega", "Ortega", enfermedades14, vacunas14, "84120512345", false, null, generarDireccionCuba());

	    List<String> enfermedades15 = new ArrayList<>();
	    List<String> vacunas15 = new ArrayList<>();
	    agregarPaciente("Olga", "D\u00EDaz", "Garc\u00EDa", enfermedades15, vacunas15, "82082222345", false, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades16 = new ArrayList<>();
	    List<String> vacunas16 = new ArrayList<>();
	    agregarPaciente("Pablo", "Mart\u00EDnez", "S\u00E1nchez", enfermedades16, vacunas16, "91051912345", false, null, generarDireccionCuba());

	    List<String> enfermedades17 = new ArrayList<>();
	    List<String> vacunas17 = new ArrayList<>();
	    agregarPaciente("Quetzal", "Rojas", "Castillo", enfermedades17, vacunas17, "95071312345", false, null, generarDireccionCuba());

	    List<String> enfermedades18 = new ArrayList<>();
	    List<String> vacunas18 = new ArrayList<>();
	    agregarPaciente("Ra\u00FAl", "L\u00F3pez", "Fern\u00E1ndez", enfermedades18, vacunas18, "81033012345", false, null, generarDireccionCuba());

	    List<String> enfermedades19 = new ArrayList<>();
	    List<String> vacunas19 = new ArrayList<>();
	    agregarPaciente("Sof\u00EDa", "Medina", "Ramos", enfermedades19, vacunas19, "97090922345", false, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades20 = new ArrayList<>();
	    List<String> vacunas20 = new ArrayList<>();
	    agregarPaciente("Tom\u00E1s", "Aguilar", "Herrera", enfermedades20, vacunas20, "87020112345", false, null, generarDireccionCuba());

	    List<String> enfermedades21 = new ArrayList<>();
	    List<String> vacunas21 = new ArrayList<>();
	    agregarPaciente("\u00DArsula", "Vargas", "Delgado", enfermedades21, vacunas21, "92112322345", true, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades22 = new ArrayList<>();
	    List<String> vacunas22 = new ArrayList<>();
	    agregarPaciente("V\u00EDctor", "Salinas", "Mora", enfermedades22, vacunas22, "77062912345", false, null, generarDireccionCuba());

	    List<String> enfermedades23 = new ArrayList<>();
	    List<String> vacunas23 = new ArrayList<>();
	    agregarPaciente("Wendy", "Cruz", "L\u00F3pez", enfermedades23, vacunas23, "89080722345", true, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades24 = new ArrayList<>();
	    List<String> vacunas24 = new ArrayList<>();
	    agregarPaciente("Ximena", "Flores", "Castillo", enfermedades24, vacunas24, "95041422345", true, generarFechaRandom(), generarDireccionCuba());

	    List<String> enfermedades25 = new ArrayList<>();
	    List<String> vacunas25 = new ArrayList<>();
	    agregarPaciente("Yahir", "Castillo", "G\u00F3mez", enfermedades25, vacunas25, "80100612345", false, null, generarDireccionCuba());

	    List<String> enfermedades26 = new ArrayList<>();
	    List<String> vacunas26 = new ArrayList<>();
	    agregarPaciente("Zulema", "Navarro", "Ruiz", enfermedades26, vacunas26, "88101822345", true, generarFechaRandom(), generarDireccionCuba());
	}

}