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
	}
}
