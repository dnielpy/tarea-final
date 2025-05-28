package entidades;

import java.util.ArrayList;
import java.util.Date;

public class CMF {
    private int id;
    private String nombre;
    private String nombreDirector;

    private ArrayList<HistoriaClinica> historiasClinicas;
    private ArrayList<HojaCargosDiaria> hojasCargoDiaria;
    private ArrayList<Paciente> pacientes;
    private Medico medico;
    private RegistroGeneral registroGeneral;
    private RegistroHistorico registroHistorico;


    public CMF(int id, String nombre, String nombreDirector) {
        setId(id);
        setNombre(nombre);
        setNombreDirector(nombreDirector);
        this.historiasClinicas = new ArrayList<HistoriaClinica>();
        this.hojasCargoDiaria = new ArrayList<HojaCargosDiaria>();
        this.pacientes = new ArrayList<Paciente>();
    }

    //Metodos para agregar
    public void crearMedico(String nombre, int numRegistro, String ci, Date fecha) {
        this.medico = new Medico(nombre, numRegistro, ci, fecha);
    }

    public void crearRegistroGeneral() {
        this.registroGeneral = new RegistroGeneral();
    }

    public void crearRegistroHistorico() {
        this.registroHistorico = new RegistroHistorico();
    }

    public void agregarPaciente(int historiaClinicaID, String nombre, int edad, ArrayList<String> vacunacion){
        Paciente newPaciente = new Paciente(historiaClinicaID, nombre, edad, vacunacion);
        this.pacientes.add(newPaciente);
    }

    public void agregarPaciente(int historiaClinicaID, String nombre, int edad, ArrayList<String> vacunacion, Date fechaUltimaRevision, boolean embarazada){
        Mujer newMujer = new Mujer(historiaClinicaID, nombre, edad, vacunacion, fechaUltimaRevision, embarazada);
        this.pacientes.add(newMujer);
    }

    public void agregarHojaCargoDiaria(Date fecha){
        HojaCargosDiaria hoja = new HojaCargosDiaria(fecha);
        this.hojasCargoDiaria.add(hoja);
    }

    public void agregarHistoriaClinica(int id, ArrayList<String> resultadosDeAnalisis, ArrayList<RegistroVisita> registroVisitas){
        HistoriaClinica historia = new HistoriaClinica(id, resultadosDeAnalisis, registroVisitas);
        this.historiasClinicas.add(historia);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }
    
    public float obtenerEmbarazadasEnRiesgo(ArrayList<Paciente> pacientes){
		float embarazadasEnRiesgo = 0;

		for (Paciente paciente: pacientes){
			if(paciente instanceof Mujer){
				if( ((Mujer) paciente).isEmbarazada()){
					if((int) paciente.getEnfermedadesCronicas().size() >= 3){	
						embarazadasEnRiesgo ++;
					}
				}
				return embarazadasEnRiesgo;
			}
		}		
		return embarazadasEnRiesgo;
	}
	
	public int[] obtenerRangosDeEdad(ArrayList<Paciente> pacientes){
		 int[] rangoDeEdad = new int[10];	//Contiene la cantidad de pacientes en el rango. posicion 0 = 1 a 10 annos, 2 = 11 a 20 annos etc
		 for (Paciente paciente: pacientes){
			 if(paciente.getEdad() < 11){
				 rangoDeEdad[0]++;
			 }
			 else if(paciente.getEdad() < 21){
				 rangoDeEdad[1]++;	 
			 }
			 else if(paciente.getEdad() < 31){
				 rangoDeEdad[2]++;	 
			 }
			 else if(paciente.getEdad() < 41){
				 rangoDeEdad[3]++;	 
			 }
			 else if(paciente.getEdad() < 51){
				 rangoDeEdad[4]++;	 
			 }
			 else if(paciente.getEdad() < 61){
				 rangoDeEdad[5]++;	 
			 }
			 else if(paciente.getEdad() < 71){
				 rangoDeEdad[6]++;	 
			 }
			 else if(paciente.getEdad() < 81){
				 rangoDeEdad[7]++;	 
			 }
			 else if(paciente.getEdad() < 91){
				 rangoDeEdad[8]++;	 
			 }
		 }
		 return rangoDeEdad;
	}
}
