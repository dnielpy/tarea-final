package util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import entidades.CMF;
import entidades.registros.Analisis;
import entidades.registros.Visita;

public class MockDataGenerator {

	// Metodos para randomizar datos
	
	private static void crearPacienteConDatos(String nombre, String primerApellido, String segundoApellido, String ci,
			boolean esMujer, LocalDate fecha, String direccion) {
		List<String> enfermedades = generarEnfermedadesCronicasAleatorias();
		List<String> vacunas = generarVacunasAleatorias();
		CMF.getInstance().agregarPaciente(nombre, primerApellido, segundoApellido, enfermedades, vacunas, ci, esMujer, fecha, direccion);
	}

	private static List<String> generarEspecialidadesAleatorias() {
		List<String> lista = new ArrayList<>();
		int cantidad = random.nextInt(ConstantesEspecialidades.ESPECIALIDADES_REMITIDAS.size() + 1);
		// Para evitar repeticiones, tomamos una copia y la mezclamos
		List<String> copia = new ArrayList<>(ConstantesEspecialidades.ESPECIALIDADES_REMITIDAS);
		Collections.shuffle(copia, random);

		for (int i = 0; i < cantidad; i++) {
			lista.add(copia.get(i));
		}
		return lista;
	}

	private static Analisis generarAnalisisAleatorioParaVisita(LocalDate fechaVisita, int idVisita, int idHistoriaClinica, List<Analisis> analisisExistentes) {
	    Set<String> tiposExistentes = new HashSet<>();
	    for (Analisis a : analisisExistentes) {
	        tiposExistentes.add(a.getTipoDeAnalisis());
	    }

	    String tipo = null;
	    boolean tipoValido = false;
	    int intentos = 0;
	    final int maxIntentos = 10;

	    while (!tipoValido && intentos < maxIntentos) {
	        tipo = ConstantesAnalisis.TIPOS_ANALISIS.get(random.nextInt(ConstantesAnalisis.TIPOS_ANALISIS.size()));
	        if (!tiposExistentes.contains(tipo)) {
	            tipoValido = true;
	        }
	        intentos++;
	    }

	    Analisis analisis = null;
	    if (tipoValido) {
	        int idAnalisis = CMF.getInstance().obtenerNuevoAnalisisID();

	        analisis = new Analisis(idAnalisis, tipo, fechaVisita, idVisita, idHistoriaClinica);

	        if (random.nextBoolean()) {
	            LocalDate fechaResultado = fechaVisita.plusDays(1 + random.nextInt(10));
	            if (fechaResultado.isAfter(LocalDate.now())) {
	                fechaResultado = LocalDate.now();
	            }
	            analisis.setFechaResultado(fechaResultado);
	            analisis.setResultados("Resultados normales para " + tipo);
	        }
	    }

	    return analisis;
	}

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

	public static String generarDireccionCuba() {
		Map<String, String> calleMunicipio = new HashMap<>();
		calleMunicipio.put("Calle 23", "Plaza de la Revoluci�n");
		calleMunicipio.put("Avenida Boyeros", "Boyeros");
		calleMunicipio.put("Calle L�nea", "Plaza de la Revoluci�n");
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

	private static List<String> generarEnfermedadesCronicasAleatorias() {
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

		Collections.shuffle(posiblesEnfermedades, random);

		for (int i = 0; i < cantidad; i++) {
			enfermedadesAsignadas.add(posiblesEnfermedades.get(i));
		}

		return enfermedadesAsignadas;
	}

	private static List<String> generarVacunasAleatorias() {
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
	
	// Genera datos de prueba para el sistema
	public static void mockData() {
		 CMF cmf = CMF.getInstance();

		    LocalDate fechaBase = LocalDate.of(1960, 1, 1);

		    cmf.crearMedico("Alfonso", "Rodr�guez", "Camela", 11321, "75060212345",
		            generarFechaRandomDesde(fechaBase),
		            "medico@example.com", "12345678");

		    cmf.crearEnfermera("Maria", "Hernandez", "Rodriguez", 43, "98121296831", true, 4,
		            generarFechaRandomDesde(fechaBase), "enfermera@example.com", "12345678");

		    Object[][] pacientes = {
		        {"Armando", "L�pez", "Toro", "78041312345", false, null},
		        {"Amanda", "L�pez", "Garc�a", "03021178136", false, generarFechaRandomDesde(fechaBase)},
		        {"Carlos", "Garces", "Fern�ndez", "89041312325", false, null},
		        {"Daniela", "Su�rez", "Molina", "95011022314", false, generarFechaRandomDesde(fechaBase)},
		        {"Esteban", "P�rez", "L�pez", "72051512365", false, null},
		        {"Fernando", "G�mez", "Rivas", "85091212385", false, null},
		        {"Gabriela", "Torres", "Mart�nez", "94040322374", true, generarFechaRandomDesde(fechaBase)},
		        {"H�ctor", "S�nchez", "L�pez", "96021512365", false, null},
		        {"Isabel", "Fern�ndez", "Cruz", "03050322394", true, generarFechaRandomDesde(fechaBase)},
		        {"Javier", "Morales", "Castillo", "75060212345", false, null},
		        {"Karla", "Ruiz", "Dom�nguez", "98062122314", true, generarFechaRandomDesde(fechaBase)},
		        {"Luis", "Herrera", "P�rez", "92011412365", false, null},
		        {"Mar�a Jos�", "Salazar", "Garc�a", "80031722354", true, generarFechaRandomDesde(fechaBase)},
		        {"Nicol�s", "Vega", "Ortega", "84120512385", false, null},
		        {"Olga", "D�az", "Garc�a", "82082222334", false, generarFechaRandomDesde(fechaBase)},
		        {"Pablo", "Mart�nez", "S�nchez", "91051912365", false, null},
		        {"Quetzal", "Rojas", "Castillo", "95071312365", false, null},
		        {"Ra�l", "L�pez", "Fern�ndez", "81033012325", false, null},
		        {"Sof�a", "Medina", "Ramos", "97090922394", false, generarFechaRandomDesde(fechaBase)},
		        {"Tom�s", "Aguilar", "Herrera", "87020112345", false, null},
		        {"�rsula", "Vargas", "Delgado", "92112322334", true, generarFechaRandomDesde(fechaBase)},
		        {"V�ctor", "Salinas", "Mora", "77062912385", false, null},
		        {"Wendy", "Cruz", "L�pez", "89080722374", true, generarFechaRandomDesde(fechaBase)},
		        {"Ximena", "Flores", "Castillo", "95041422334", true, generarFechaRandomDesde(fechaBase)},
		        {"Yahir", "Castillo", "G�mez", "80100612365", false, null},
		        {"Zulema", "Navarro", "Ruiz", "88101822374", true, generarFechaRandomDesde(fechaBase)},
		        {"Alejandro", "P�rez", "G�mez", "85010112345", false, null},
		        {"Beatriz", "L�pez", "Mart�nez", "92030522314", true, generarFechaRandomDesde(fechaBase)},
		        {"Camila", "Rodr�guez", "Hern�ndez", "98071222374", true, generarFechaRandomDesde(fechaBase)},
		        {"David", "Garc�a", "Fern�ndez", "75092312385", false, null},
		        {"Elena", "Mart�nez", "Ruiz", "94021522334", true, generarFechaRandomDesde(fechaBase)},
		        {"Francisco", "Hern�ndez", "P�rez", "88010112365", false, null},
		        {"Gabriela", "Vega", "Ortega", "96050322394", true, generarFechaRandomDesde(fechaBase)},
		        {"Hugo", "D�az", "Castillo", "81071512325", false, null},
		        {"Isabela", "Navarro", "Salazar", "99062122314", true, generarFechaRandomDesde(fechaBase)},
		        {"Jorge", "Ramos", "Medina", "87011012345", false, null},
		        {"Karla", "Aguilar", "Delgado", "95041422334", true, generarFechaRandomDesde(fechaBase)},
		        {"Luis", "Salinas", "Mora", "82082212385", false, null},
		        {"Mar�a", "Cruz", "L�pez", "97090922374", true, generarFechaRandomDesde(fechaBase)},
		        {"Nicol�s", "Flores", "Castillo", "89080722365", false, null},
		        {"Olivia", "Castillo", "G�mez", "92011422334", true, generarFechaRandomDesde(fechaBase)},
		        {"Pablo", "Navarro", "Ruiz", "84120512385", false, null},
		        {"Quint�n", "Rojas", "Fern�ndez", "78041312325", false, null},
		        {"Rosa", "L�pez", "Garc�a", "03021122374", true, generarFechaRandomDesde(fechaBase)},
		        {"Santiago", "G�mez", "Rivas", "85091212345", false, null},
		        {"Teresa", "Torres", "Mart�nez", "94040322314", true, generarFechaRandomDesde(fechaBase)},
		        {"Andrea", "Mart�nez", "G�mez", "91021522345", true, generarFechaRandomDesde(fechaBase)},
		        {"B�rbara", "Hern�ndez", "L�pez", "92031812365", true, generarFechaRandomDesde(fechaBase)},
		        {"Carlos", "P�rez", "Rodr�guez", "85072912385", false, null},
		        {"Diana", "Garc�a", "Fern�ndez", "98041222374", true, generarFechaRandomDesde(fechaBase)},
		        {"Eduardo", "Navarro", "Ruiz", "80010112325", false, null},
		        {"Fernanda", "Salazar", "Ortega", "95062122394", true, generarFechaRandomDesde(fechaBase)},
		        {"Gustavo", "Rojas", "Castillo", "82071512345", false, null},
		        {"Helena", "Aguilar", "Molina", "99030522314", true, generarFechaRandomDesde(fechaBase)},
		        {"Ignacio", "Cruz", "Delgado", "87011012385", false, null},
		        {"Julia", "P�rez", "Ruiz", "96041422334", true, generarFechaRandomDesde(fechaBase)},
		        {"Kevin", "G�mez", "Salinas", "81082212365", false, null},
		        {"Laura", "L�pez", "Torres", "98090922374", true, generarFechaRandomDesde(fechaBase)},
		        {"Manuel", "Flores", "Castro", "89080712345", false, null},
		        {"Natalia", "Castillo", "G�mez", "92011422394", true, generarFechaRandomDesde(fechaBase)},
		        {"Oscar", "Navarro", "Ramos", "84120512385", false, null},
		        {"Patricia", "Rojas", "Fern�ndez", "78041312325", true, generarFechaRandomDesde(fechaBase)},
		        {"Ricardo", "L�pez", "Garc�a", "03021122374", false, null},
		        {"Silvia", "G�mez", "Rivas", "85091212345", true, generarFechaRandomDesde(fechaBase)},
		        {"Tom�s", "Torres", "Mart�nez", "94040322314", false, null},
		        {"Valeria", "Mart�nez", "P�rez", "97051422334", true, generarFechaRandomDesde(fechaBase)},
		        {"Alina", "Rodr�guez", "Mart�nez", "91031522345", true, generarFechaRandomDesde(fechaBase)},
		        {"Benjam�n", "Hern�ndez", "P�rez", "85081212365", false, null},
		        {"Claudia", "P�rez", "G�mez", "94072922374", true, generarFechaRandomDesde(fechaBase)},
		        {"Daniel", "Garc�a", "L�pez", "80021512385", false, null},
		        {"Eliana", "Navarro", "Fern�ndez", "97062122334", true, generarFechaRandomDesde(fechaBase)},
		        {"Fernando", "Salazar", "Ortega", "89010112325", false, null},
		        {"Gabriel", "Rojas", "Castillo", "82091212345", false, null},
		        {"Hilda", "Aguilar", "Molina", "99011022314", true, generarFechaRandomDesde(fechaBase)},
		        {"Iv�n", "Cruz", "Delgado", "87021512385", false, null},
		        {"Jimena", "P�rez", "Ruiz", "96031822334", true, generarFechaRandomDesde(fechaBase)},
		        {"Katherine", "G�mez", "Salinas", "81072912365", true, generarFechaRandomDesde(fechaBase)},
		        {"Leonel", "L�pez", "Torres", "98051212345", false, null},
		        {"M�nica", "Flores", "Castro", "89091422374", true, generarFechaRandomDesde(fechaBase)},
		        {"Nadia", "Castillo", "G�mez", "92031822394", true, generarFechaRandomDesde(fechaBase)},
		        {"Orlando", "Navarro", "Ramos", "84101212385", false, null},
		        {"Paola", "Rojas", "Fern�ndez", "78062122325", true, generarFechaRandomDesde(fechaBase)},
		        {"Ra�l", "L�pez", "Garc�a", "03041512374", false, null},
		        {"Sof�a", "G�mez", "Rivas", "85031812345", true, generarFechaRandomDesde(fechaBase)},
		        {"Tom�s", "Torres", "Mart�nez", "94072922314", false, null},
		        {"Valentina", "Mart�nez", "P�rez", "97081222334", true, generarFechaRandomDesde(fechaBase)},
		        {"�lvaro", "P�rez", "Hern�ndez", "81010112345", false, null},
		        {"Beatriz", "G�mez", "Mart�nez", "92021522314", true, generarFechaRandomDesde(fechaBase)},
		        {"C�sar", "Rodr�guez", "L�pez", "85031212365", false, null},
		        {"Delfina", "Navarro", "Fern�ndez", "97072922374", true, generarFechaRandomDesde(fechaBase)},
		        {"Emilio", "Salazar", "Ortega", "89021512385", false, null},
		        {"Fabiola", "Rojas", "Castillo", "82031822334", true, generarFechaRandomDesde(fechaBase)},
		        {"Gonzalo", "Aguilar", "Molina", "99062112325", false, null},
		        {"H�ctor", "Cruz", "Delgado", "87091412385", false, null},
		        {"Isabel", "P�rez", "Ruiz", "96051222334", true, generarFechaRandomDesde(fechaBase)},
		        {"Joaqu�n", "G�mez", "Salinas", "81031812365", false, null},
		        {"Karla", "L�pez", "Torres", "98072922345", true, generarFechaRandomDesde(fechaBase)},
		        {"Luis", "Flores", "Castro", "89062112385", false, null},
		        {"Mar�a", "Castillo", "G�mez", "92091422394", true, generarFechaRandomDesde(fechaBase)},
		        {"Olga", "Rojas", "Fern�ndez", "78091422374", true, generarFechaRandomDesde(fechaBase)},
		        {"Pablo", "L�pez", "Garc�a", "03062112345", false, null},
		        {"Quint�n", "G�mez", "Rivas", "85072912365", false, null},
		        {"Rosa", "Torres", "Mart�nez", "94091422314", true, generarFechaRandomDesde(fechaBase)},
		        {"Santiago", "Mart�nez", "P�rez", "97062122334", false, null},
		        {"Teresa", "Rodr�guez", "Hern�ndez", "91031822345", true, generarFechaRandomDesde(fechaBase)}
		    };

		    for (Object[] p : pacientes) {
		        String nombre = (String) p[0];
		        String primerApellido = (String) p[1];
		        String segundoApellido = (String) p[2];
		        String ci = (String) p[3];
		        boolean esMujer = (boolean) p[4];
		        LocalDate fecha = (LocalDate) p[5];

		        crearPacienteConDatos(nombre, primerApellido, segundoApellido, ci, esMujer, fecha,
		                generarDireccionCuba());
		    }

		// Visitas
		    fechaBase = LocalDate.now().minusDays(60);

		    // Datos de visitas: idPaciente, motivo, tratamiento
		    Object[][] visitasDatos = {
		        {1, "Resfriado com�n", "Paracetamol"},
		        {2, "Dolor de cabeza", "Ibuprofeno"},
		        {3, "Tos persistente", "Jarabe expectorante"},
		        {4, "Dolor abdominal", "Omeprazol"},
		        {5, "Presi�n alta", "Enalapril"},
		        {6, "Dolor muscular", "Diclofenaco"},
		        {7, "Control prenatal", "Suplementos vitam�nicos"},
		        {8, "Alergia estacional", "Antihistam�nicos"},
		        {9, "Control prenatal", "Suplementos vitam�nicos"},
		        {10, "Dolor lumbar", "Fisioterapia"},
		        {11, "Fiebre alta", "Acetaminof�n"},
		        {12, "Infecci�n urinaria", "Antibi�ticos"},
		        {13, "Migra�a", "Sumatript�n"},
		        {14, "Gripe", "Antigripales"},
		        {15, "Dolor de garganta", "Antis�pticos"},
		        {16, "Fractura de brazo", "Inmovilizaci�n"},
		        {17, "Infecci�n respiratoria", "Antibi�ticos"},
		        {18, "Hipertensi�n", "Losart�n"},
		        {19, "Diabetes", "Insulina"},
		        {20, "Ansiedad", "Ansiol�ticos"},
		        {21, "Depresi�n", "Antidepresivos"},
		        {22, "Otitis", "Antibi�ticos"},
		        {23, "Conjuntivitis", "Colirios"},
		        {24, "Asma", "Broncodilatadores"},
		        {25, "Gastritis", "Anti�cidos"},
		        {26, "Apendicitis", "Cirug�a"},
		        {27, "Bronquitis", "Antibi�ticos"},
		        {28, "Neumon�a", "Antibi�ticos"},
		        {29, "C�lculos renales", "Analg�sicos"},
		        {30, "Artritis", "Antiinflamatorios"},
		        {31, "Dolor de espalda", "Fisioterapia"},
		        {32, "Infecci�n de o�do", "Antibi�ticos"},
		        {33, "Resfriado severo", "Antigripales"},
		        {34, "Dolor de muelas", "Analg�sicos"},
		        {35, "Esguince de tobillo", "Inmovilizaci�n"},
		        {36, "Gastritis cr�nica", "Anti�cidos"},
		        {37, "Hipotiroidismo", "Levotiroxina"},
		        {38, "Hiperglucemia", "Insulina"},
		        {39, "Infecci�n cut�nea", "Antibi�ticos t�picos"},
		        {40, "Alergia alimentaria", "Antihistam�nicos"},
		        {41, "Cefalea tensional", "Ibuprofeno"},
		        {42, "Dermatitis", "Cremas t�picas"},
		        {43, "Anemia", "Suplementos de hierro"},
		        {44, "Dolor articular", "Antiinflamatorios"},
		        {45, "Hipertensi�n arterial", "Losart�n"},
		        {46, "Infecci�n estomacal", "Antibi�ticos"},
		        {47, "Dolor de cuello", "Fisioterapia"},
		        {48, "Otitis media", "Antibi�ticos"},
		        {49, "Sinusitis", "Descongestionantes"},
		        {50, "Fatiga cr�nica", "Vitaminas"},
		    };

		    for (int i = 0; i < visitasDatos.length; i++) {
		        int idPaciente = (int) visitasDatos[i][0];
		        String motivo = (String) visitasDatos[i][1];
		        String tratamiento = (String) visitasDatos[i][2];

		        LocalDate fechaVisita = generarFechaRandomDesde(fechaBase);
		        int idVisita = cmf.obtenerNuevoVisitaID();

		        List<Analisis> analisisParaVisita = new ArrayList<>();

		        // Decide cu�ntos an�lisis crear (ejemplo: 0 a 3)
		        int cantidadAnalisis = new Random().nextInt(4);

		        for (int j = 0; j < cantidadAnalisis; j++) {
		            Analisis nuevoAnalisis = generarAnalisisAleatorioParaVisita(
		                fechaVisita, idVisita, idPaciente, analisisParaVisita);

		            if (nuevoAnalisis != null) {
		                analisisParaVisita.add(nuevoAnalisis);
		            }
		        }

		        Visita visita = new Visita(
		            idVisita,
		            idPaciente,
		            fechaVisita,
		            motivo,
		            tratamiento,
		            analisisParaVisita,
		            generarEspecialidadesAleatorias(),
		            generarDireccionCuba()
		        );

		        cmf.agregarVisita(visita);
		    }
	}
}
