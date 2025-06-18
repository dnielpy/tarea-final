package entidades.personal;

import runner.Usuario;

public abstract class PersonalSanitario extends Persona {

    protected Usuario user;

    public PersonalSanitario(String nombre, String primerApellido, 
    		String segundoApellido, String ci, String userName, String password, Usuario.TipoDeRol rol) {
    	super(nombre, primerApellido, segundoApellido, ci);
    	
    	user = new Usuario(userName, password, rol);
    }
    
    public Usuario getUser() {
    	return user;
    }
    
    public void setUser(Usuario user) {
    	this.user = user;
    }

}
