package entidades;

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

import entidades.personal.Enfermera;
import entidades.personal.Medico;
import entidades.personal.Mujer;
import entidades.personal.Paciente;
import entidades.personal.PersonalSanitario;
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
import util.MockDataGenerator;

public class CMF {
	// Instancia única de CMF
	private static CMF instance;

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

	// Constructor privado para evitar instanciación directa
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

	public PersonalSanitario getEntitytyUsuario() {
		PersonalSanitario personAuthenticated = null;

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

	public void editarPaciente(Paciente paciente,
			String nuevoNombre,
			String nuevoPrimerApellido,
			String nuevoSegundoApellido,
			String nuevaDireccion,
			Boolean nuevoEstadoEmbarazo,
			LocalDate nuevaFechaCitologia,
			List<String> nuevasEnfermedadesCronicas,
			List<String> nuevasVacunas) {

		if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
			paciente.setNombre(nuevoNombre);
		}

		if (nuevoPrimerApellido != null && !nuevoPrimerApellido.isEmpty()) {
			paciente.setPrimerApellido(nuevoPrimerApellido);
		}

		if (nuevoSegundoApellido != null && !nuevoSegundoApellido.isEmpty()) {
			paciente.setSegundoApellido(nuevoSegundoApellido);
		}

		if (nuevaDireccion != null && !nuevaDireccion.isEmpty()) {
			paciente.setDireccion(nuevaDireccion);
		}

		// Si es una mujer, se permite editar embarazo y fecha de revisión
		if (paciente instanceof Mujer) {
			if (nuevoEstadoEmbarazo != null) {
				((Mujer)paciente).setEmbarazada(nuevoEstadoEmbarazo);
			}

			if (nuevaFechaCitologia != null) {
				((Mujer)paciente).setFechaUltimaRevision(nuevaFechaCitologia);
			}
		}

		// Reemplazar enfermedades crónicas si la lista no es nula
		if (nuevasEnfermedadesCronicas != null) {
			paciente.setEnfermedadesCronicas(nuevasEnfermedadesCronicas);
		}

		// Reemplazar lista de vacunas
		if (nuevasVacunas != null) {
			paciente.setVacunacion(new ArrayList<>(nuevasVacunas)); // Usa tu método con validación
		}
	}


	public boolean eliminarPaciente(int id) {
		boolean response = false;
		Paciente pacienteAEliminar = getPacientePorId(id);
		if (pacienteAEliminar != null) {
			limpiarRastroPaciente(id);
			pacientes.remove(getPacientePorId(id));
			response = true;
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
		enfermera.setFechaInicioCMF(fecha);
	}

	// Visitas

	public List<Visita> obtenerListaVisitas() {
		List<Visita> listaVisitas = new ArrayList<>();

		if (visitas != null) {
			listaVisitas = visitas;
		}

		return listaVisitas;
	}

	// Filtra visitas por una fecha específica
	public List<Visita> obtenerVisitasDeUnDia(LocalDate fecha) {
		List<Visita> listaVisitas = new ArrayList<>();

		for (Visita visita : visitas) {
			if (visita.getFecha().equals(fecha)) {
				listaVisitas.add(visita);
			}
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

	public boolean editarVisita(int id, LocalDate fecha, String diagnostico, String tratamiento,
			List<Analisis> analisis, List<String> especialidades, String direccion) {

		boolean response = false;
		if (visitas == null) {
			throw new IllegalStateException("No hay visitas registradas");
		}

		Visita visitaEncontrada = obtenerVisitaPorId(id);

		if (visitaEncontrada != null) {
			visitaEncontrada.setFecha(fecha);
			visitaEncontrada.setDiagnostico(diagnostico);
			visitaEncontrada.setTratamiento(tratamiento);
			visitaEncontrada.setAnalisis(analisis);
			visitaEncontrada.setEspecialidadesRemitidas(especialidades);
			visitaEncontrada.setDireccion(direccion);
			response = true;
		} else {
			throw new IllegalArgumentException("Visita con ID " + id + " no encontrada");
		}

		actualizarVisitaHistoriaClinica(visitaEncontrada);
		return response;
	}

	// Agrega una nueva visita y actualiza registros relacionados
	public void agregarVisita(Visita visita) {
		Objects.requireNonNull(visita, "La visita no puede ser nula");
		for (int i = 0; i < visitas.size(); i++) {
			if (visitas.get(i).getId() == visita.getId()) {
				visitas.set(i, visita);
				throw new IllegalArgumentException(
						"Ya existe una visita con el ID " + visita.getId() + ". Por favor, use un ID diferente.");
			}
		}

		visitas.add(visita);

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

	// Actualiza la historia clínica con una visita
	public void actualizarVisitaHistoriaClinica(Visita visita) {
		boolean encontrado = false;
		for (int i = 0; i < getPacientes().size() && !encontrado; i++) {
			if (getPacientes().get(i).getHistoriaClinica().getId() == visita.getPacienteHistoriaClinicaID()) {
				HistoriaClinica hc = getPacientes().get(i).getHistoriaClinica();

				// Buscar si ya existe la visita en la historia clínica
				boolean visitaEncontrada = false;
				List<Visita> visitasHC = hc.getRegistroVisitas();
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

	public void eliminarVisita(int id) {
		if (visitas == null) {
			throw new IllegalStateException("No hay visitas registradas");
		}

		boolean eliminada = false;
		for (int i = 0; i < visitas.size() && !eliminada; i++) {
			if (visitas.get(i).getId() == id) {
				visitas.remove(i);
				eliminada = true;
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

	// Editar Analisis
	
	public void editarResultadosDeAnalisis(Analisis analisis, String nuevosResultados) {
	    String anteriores = analisis.getResultados() != null ? analisis.getResultados().trim() : "";
	    nuevosResultados = nuevosResultados.trim();

	    if (!nuevosResultados.equals(anteriores)) {
	        analisis.setResultados(nuevosResultados);
	        analisis.setFechaResultado(LocalDate.now());
	    }
	}

	
	// Hojas de cargo

	public void agregarHojaCargoDiaria(LocalDate fecha) {
		HojaCargosDiaria hoja = new HojaCargosDiaria(fecha);
		hojasCargoDiaria.add(hoja);
	}

	public List<LocalDate> obtenerFechasDeHojasDeCargo() {
		List<LocalDate> fechas = new ArrayList<>();
		if (hojasCargoDiaria != null) {
			for (HojaCargosDiaria hoja : hojasCargoDiaria) {
				if (hoja != null && hoja.getFecha() != null && !fechas.contains(hoja.getFecha())) {
					fechas.add(hoja.getFecha());
				}
			}
		}
		return fechas;
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

	public Paciente getPacientePorCI(String ci) {
		Paciente pac = null;
		boolean encontrado = false;

		for (int i = 0; i < pacientes.size() && !encontrado; i++) {
			if (pacientes.get(i).getCI().equals(ci)) {
				pac = pacientes.get(i);
				encontrado = true;
			}
		}

		return pac;
	}

	// Elimina todas las referencias de un paciente eliminado
	public void limpiarRastroPaciente(int id) {

		Paciente paciente = getPacientePorId(id);
		if (paciente == null) {
			throw new IllegalArgumentException("No se encontró un paciente con el ID proporcionado");
		}

		// Eliminar visitas relacionadas con el paciente
		for (int i = 0; i < visitas.size(); i++) {
			if (visitas.get(i).getPacienteHistoriaClinicaID() == id) {
				visitas.remove(i);
				i--; // Ajustar índice tras eliminación
			}
		}

		// Eliminar referencias en hojas de cargos diarias
		for (int i = 0; i < hojasCargoDiaria.size(); i++) {
			HojaCargosDiaria hoja = hojasCargoDiaria.get(i);
			List<Visita> visitasHoja = hoja.getVisitas();
			for (int j = 0; j < visitasHoja.size(); j++) {
				if (visitasHoja.get(j).getPacienteHistoriaClinicaID() == id) {
					visitasHoja.remove(j);
					j--; // Ajustar índice tras eliminación
				}
			}
		}

		// Eliminar visitas y análisis de la historia clínica
		HistoriaClinica historiaClinica = paciente.getHistoriaClinica();
		if (historiaClinica != null) {
			List<Visita> registroVisitas = historiaClinica.getRegistroVisitas();
			for (int i = 0; i < registroVisitas.size(); i++) {
				if (registroVisitas.get(i).getPacienteHistoriaClinicaID() == id) {
					registroVisitas.remove(i);
					i--; // Ajustar índice tras eliminación
				}
			}

			List<Analisis> listaAnalisis = historiaClinica.getAnalisis();
			for (int i = 0; i < listaAnalisis.size(); i++) {
				if (listaAnalisis.get(i).getHistoriaClinicaId() == id) {
					listaAnalisis.remove(i);
					i--; // Ajustar índice tras eliminación
				}
			}
		}
	}

	public boolean isCiRepited(String ci) {
		if (ci == null || ci.trim().isEmpty()) {
			throw new IllegalArgumentException("El CI no puede ser nulo o vacío");
		}

		boolean response = false;

		if (pacientes != null && !pacientes.isEmpty()) {
			for (int i = 0; i < pacientes.size() && !response; i++) {
				if (pacientes.get(i).getCI().equals(ci)) {
					response = true;
				}
			}

		} else {
			response = false; // No hay pacientes, no puede haber repetidos
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

	// Genera un ID único para un nuevo análisis
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
		boolean encontrado = false;
		for (int i = 0; i < hojasCargoDiaria.size() && !encontrado; i++) {
			if (hojasCargoDiaria.get(i).getFecha().equals(fecha)) {
				hojaDeCargo = hojasCargoDiaria.get(i);
				encontrado = true;
			}
		}
		return hojaDeCargo;
	}

	public Visita obtenerVisitaPorId(int id) {
		Visita v = null;
		boolean encontrado = false;

		if (visitas == null) {
			throw new IllegalStateException("No hay visitas registradas");
		}
		for (int i = 0; i < visitas.size() && !encontrado; i++) {
			if (visitas.get(i).getId() == id) {
				v = visitas.get(i);
			}
		}
		return v;
	}

	// Cantidades

	public int obtenerCantVisitasDeUnDia(LocalDate fecha) {
		int visitas = 0;
		boolean encontrado = false;

		for (int i = 0; i < hojasCargoDiaria.size() && !encontrado; i++) {
			if (hojasCargoDiaria.get(i).getFecha().equals(fecha)) {
				visitas = hojasCargoDiaria.get(i).cantidadVisitas();
				encontrado = true;
			}
		}

		return visitas;
	}

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

	// Cuenta visitas por día en un mes específico
	public int[] obtenerCantVisitasEnUnMes(int mes, int anio) {
		LocalDate fechaInicial = LocalDate.of(anio, mes, 1);
		int maximoDia = fechaInicial.lengthOfMonth();
		LocalDate fechaFinal = fechaInicial.withDayOfMonth(maximoDia);

		int[] visitasPorDia = new int[maximoDia];

		for (Visita visita : visitas) {
			LocalDate fechaVisita = visita.getFecha();

			if (!fechaVisita.isBefore(fechaInicial) && !fechaVisita.isAfter(fechaFinal)) {
				int dia = fechaVisita.getDayOfMonth(); // va del 1 al maximoDia
				visitasPorDia[dia - 1]++; // el índice es dia - 1
			}
		}

		return visitasPorDia;
	}

	// Datos cableados

	public void cargarDatos() {
		MockDataGenerator.mockData();
	}

}