package util;

import java.time.LocalDate;

import entidades.CMF;
import entidades.registros.Visita;

public class MockDataGenerator {

	public static void mockData() {
		CMF cmf = CMF.getInstance();

		LocalDate fechaBase = LocalDate.of(1960, 1, 1);

		cmf.crearMedico("Alfonso", "Rodr\u00EDguez", "Camela", 11321, "75060212345",
				CMF.generarFechaRandomDesde(fechaBase),
				"medico@example.com", "12345678");

		cmf.crearEnfermera("Maria", "Hernandez", "Rodriguez", 43, "98121296831", true, 4,
				CMF.generarFechaRandomDesde(fechaBase), "enfermera@example.com", "12345678");

		cmf.crearPacienteConDatos("Armando", "L\u00F3pez", "Toro", "78041312345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Amanda", "L\u00F3pez", "Garc\u00EDa", "03021178136", false,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Carlos", "Garces", "Fern\u00E1ndez", "89041312325", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Daniela", "Su\u00E1rez", "Molina", "95011022314", false,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Esteban", "P\u00E9rez", "L\u00F3pez", "72051512365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Fernando", "G\u00F3mez", "Rivas", "85091212385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Gabriela", "Torres", "Mart\u00EDnez", "94040322374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("H\u00E9ctor", "S\u00E1nchez", "L\u00F3pez", "96021512365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Isabel", "Fern\u00E1ndez", "Cruz", "03050322394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Javier", "Morales", "Castillo", "75060212345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Karla", "Ruiz", "Dom\u00EDnguez", "98062122314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Luis", "Herrera", "P\u00E9rez", "92011412365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Mar\u00EDa Jos\u00E9", "Salazar", "Garc\u00EDa", "80031722354", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Nicol\u00E1s", "Vega", "Ortega", "84120512385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Olga", "D\u00EDaz", "Garc\u00EDa", "82082222334", false,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Pablo", "Mart\u00EDnez", "S\u00E1nchez", "91051912365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Quetzal", "Rojas", "Castillo", "95071312365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Ra\u00FAl", "L\u00F3pez", "Fern\u00E1ndez", "81033012325", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Sof\u00EDa", "Medina", "Ramos", "97090922394", false,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Tom\u00E1s", "Aguilar", "Herrera", "87020112345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("\u00DArsula", "Vargas", "Delgado", "92112322334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("V\u00EDctor", "Salinas", "Mora", "77062912385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Wendy", "Cruz", "L\u00F3pez", "89080722374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Ximena", "Flores", "Castillo", "95041422334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Yahir", "Castillo", "G\u00F3mez", "80100612365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Zulema", "Navarro", "Ruiz", "88101822374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Alejandro", "Pérez", "Gómez", "85010112345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Beatriz", "López", "Martínez", "92030522314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Camila", "Rodríguez", "Hernández", "98071222374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("David", "García", "Fernández", "75092312385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Elena", "Martínez", "Ruiz", "94021522334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Francisco", "Hernández", "Pérez", "88010112365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Gabriela", "Vega", "Ortega", "96050322394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Hugo", "Díaz", "Castillo", "81071512325", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Isabela", "Navarro", "Salazar", "99062122314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Jorge", "Ramos", "Medina", "87011012345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Karla", "Aguilar", "Delgado", "95041422334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Luis", "Salinas", "Mora", "82082212385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("María", "Cruz", "López", "97090922374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Nicolás", "Flores", "Castillo", "89080712365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Olivia", "Castillo", "Gómez", "92011422334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Pablo", "Navarro", "Ruiz", "84120512385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Quintín", "Rojas", "Fernández", "78041312345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Rosa", "López", "García", "03021122394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Santiago", "Gómez", "Rivas", "85091212365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Teresa", "Torres", "Martínez", "94040322374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Adriana", "Gómez", "Pérez", "91010122345", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Bruno", "Fernández", "López", "85021512365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Carla", "Martínez", "Ramos", "94031222374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Diego", "Hernández", "García", "80072912385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Elisa", "Rodríguez", "Cruz", "97051422334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Felipe", "Navarro", "Ortega", "89010112325", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Gabriela", "Salazar", "Vega", "95062122394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Héctor", "Rojas", "Castillo", "82071512345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Irene", "Aguilar", "Molina", "99030522314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Javier", "Cruz", "Delgado", "87011012385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Karen", "Pérez", "Ruiz", "96041422334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Leonardo", "Gómez", "Salinas", "81082212365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Mariana", "López", "Torres", "98090922374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Nicolás", "Flores", "Castro", "89080712345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Olga", "Castillo", "Gómez", "92011422394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Pablo", "Navarro", "Ramos", "84120512385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Quintín", "Rojas", "Fernández", "78041312325", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Rosa", "López", "García", "03021122374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Santiago", "Gómez", "Rivas", "85091212345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Teresa", "Torres", "Martínez", "94040322314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Andrea", "Martínez", "Gómez", "91021522345", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Bárbara", "Hernández", "López", "92031812365", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Carlos", "Pérez", "Rodríguez", "85072912385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Diana", "García", "Fernández", "98041222374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Eduardo", "Navarro", "Ruiz", "80010112325", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Fernanda", "Salazar", "Ortega", "95062122394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Gustavo", "Rojas", "Castillo", "82071512345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Helena", "Aguilar", "Molina", "99030522314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Ignacio", "Cruz", "Delgado", "87011012385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Julia", "Pérez", "Ruiz", "96041422334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Kevin", "Gómez", "Salinas", "81082212365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Laura", "López", "Torres", "98090922374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Manuel", "Flores", "Castro", "89080712345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Natalia", "Castillo", "Gómez", "92011422394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Oscar", "Navarro", "Ramos", "84120512385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Patricia", "Rojas", "Fernández", "78041312325", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Ricardo", "López", "García", "03021122374", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Silvia", "Gómez", "Rivas", "85091212345", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Tomás", "Torres", "Martínez", "94040322314", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Valeria", "Martínez", "Pérez", "97051422334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Alina", "Rodríguez", "Martínez", "91031522345", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Benjamín", "Hernández", "Pérez", "85081212365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Claudia", "Pérez", "Gómez", "94072922374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Daniel", "García", "López", "80021512385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Eliana", "Navarro", "Fernández", "97062122334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Fernando", "Salazar", "Ortega", "89010112325", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Gabriel", "Rojas", "Castillo", "82091212345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Hilda", "Aguilar", "Molina", "99011022314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Iván", "Cruz", "Delgado", "87021512385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Jimena", "Pérez", "Ruiz", "96031822334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Katherine", "Gómez", "Salinas", "81072912365", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Leonel", "López", "Torres", "98051212345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Mónica", "Flores", "Castro", "89091422374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Nadia", "Castillo", "Gómez", "92031822394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Orlando", "Navarro", "Ramos", "84101212385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Paola", "Rojas", "Fernández", "78062122325", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Raúl", "López", "García", "03041512374", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Sofía", "Gómez", "Rivas", "85031812345", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Tomás", "Torres", "Martínez", "94072922314", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Valentina", "Martínez", "Pérez", "97081222334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Álvaro", "Pérez", "Hernández", "81010112345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Beatriz", "Gómez", "Martínez", "92021522314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("César", "Rodríguez", "López", "85031212365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Delfina", "Navarro", "Fernández", "97072922374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Emilio", "Salazar", "Ortega", "89021512385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Fabiola", "Rojas", "Castillo", "82031822334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Gonzalo", "Aguilar", "Molina", "99062112325", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Héctor", "Cruz", "Delgado", "87091412385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Isabel", "Pérez", "Ruiz", "96051222334", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Joaquín", "Gómez", "Salinas", "81031812365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Karla", "López", "Torres", "98072922345", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Luis", "Flores", "Castro", "89062112385", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("María", "Castillo", "Gómez", "92091422394", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Olga", "Rojas", "Fernández", "78091422374", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Pablo", "López", "García", "03062112345", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Quintín", "Gómez", "Rivas", "85072912365", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Rosa", "Torres", "Martínez", "94091422314", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Santiago", "Martínez", "Pérez", "97062122334", false, null,
				CMF.generarDireccionCuba());
		cmf.crearPacienteConDatos("Teresa", "Rodríguez", "Hernández", "91031822345", true,
				CMF.generarFechaRandomDesde(fechaBase), CMF.generarDireccionCuba());

		// Visitas
		fechaBase = LocalDate.now().minusDays(3);
		LocalDate fecha;

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		int idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 1, fecha, "Resfriado común",
				"Paracetamol", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 1),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 2, fecha, "Dolor de cabeza",
				"Ibuprofeno", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 2),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 3, fecha, "Tos persistente",
				"Jarabe expectorante", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 3),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 4, fecha, "Dolor abdominal",
				"Omeprazol", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 4),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 5, fecha, "Presión alta",
				"Enalapril", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 5),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 6, fecha, "Dolor muscular",
				"Diclofenaco", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 6),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 7, fecha, "Control prenatal",
				"Suplementos vitamínicos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 7),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 8, fecha, "Alergia estacional",
				"Antihistamínicos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 8),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 9, fecha, "Control prenatal",
				"Suplementos vitamínicos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 9),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 10, fecha, "Dolor lumbar",
				"Fisioterapia", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 10),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));
		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 11, fecha, "Fiebre alta",
				"Acetaminofén", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 11),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 12, fecha, "Infección urinaria",
				"Antibióticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 12),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 13, fecha, "Migraña",
				"Sumatriptán", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 13),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 14, fecha, "Gripe",
				"Antigripales", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 14),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 15, fecha, "Dolor de garganta",
				"Antisépticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 15),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 16, fecha, "Fractura de brazo",
				"Inmovilización", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 16),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 17, fecha, "Infección respiratoria",
				"Antibióticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 17),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 18, fecha, "Hipertensión",
				"Losartán", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 18),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 19, fecha, "Diabetes",
				"Insulina", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 19),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 20, fecha, "Ansiedad",
				"Ansiolíticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 20),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 21, fecha, "Depresión",
				"Antidepresivos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 21),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 22, fecha, "Otitis",
				"Antibióticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 22),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 23, fecha, "Conjuntivitis",
				"Colirios", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 23),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 24, fecha, "Asma",
				"Broncodilatadores", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 24),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 25, fecha, "Gastritis",
				"Antiácidos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 25),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 26, fecha, "Apendicitis",
				"Cirugía", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 26),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 27, fecha, "Bronquitis",
				"Antibióticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 27),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 28, fecha, "Neumonía",
				"Antibióticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 28),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 29, fecha, "Cálculos renales",
				"Analgésicos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 29),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 30, fecha, "Artritis",
				"Antiinflamatorios", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 30),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));
		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 31, fecha, "Dolor de espalda",
				"Fisioterapia", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 31),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 32, fecha, "Infección de oído",
				"Antibióticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 32),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 33, fecha, "Resfriado severo",
				"Antigripales", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 33),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 34, fecha, "Dolor de muelas",
				"Analgésicos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 34),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 35, fecha, "Esguince de tobillo",
				"Inmovilización", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 35),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 36, fecha, "Gastritis crónica",
				"Antiácidos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 36),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 37, fecha, "Hipotiroidismo",
				"Levotiroxina", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 37),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 38, fecha, "Hiperglucemia",
				"Insulina", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 38),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 39, fecha, "Infección cutánea",
				"Antibióticos tópicos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 39),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 40, fecha, "Alergia alimentaria",
				"Antihistamínicos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 40),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 41, fecha, "Cefalea tensional",
				"Ibuprofeno", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 41),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 42, fecha, "Dermatitis",
				"Cremas tópicas", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 42),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 43, fecha, "Anemia",
				"Suplementos de hierro", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 43),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 44, fecha, "Dolor articular",
				"Antiinflamatorios", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 44),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 45, fecha, "Hipertensión arterial",
				"Losartán", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 45),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 46, fecha, "Infección estomacal",
				"Antibióticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 46),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 47, fecha, "Dolor de cuello",
				"Fisioterapia", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 47),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 48, fecha, "Otitis media",
				"Antibióticos", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 48),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 49, fecha, "Sinusitis",
				"Descongestionantes", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 49),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));

		fecha = CMF.generarFechaRandomDesde(fechaBase);
		idVisita = cmf.obtenerNuevoVisitaID();
		cmf.agregarVisita(new Visita(idVisita, 50, fecha, "Fatiga crónica",
				"Vitaminas", cmf.generarAnalisisParaVisitaConIdVisita(fecha, idVisita, 50),
				cmf.generarEspecialidadesAleatorias(),
				CMF.generarDireccionCuba()));
	}
}
