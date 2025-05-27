package clases;

public class EntradaVisitas {
	private String nombre;
	private int edad;
	private boolean sexo;
	private String direccion;
	private String diagnostico;
	
	EntradaVisitas(String nombre,int edad, boolean sexo, String direccion, String diagnostico) {
		setNombre(nombre);
		setEdad(edad);
		setSexo(sexo);
		setDireccion(direccion);
		setDiagnostico(diagnostico);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
    
    public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
}
