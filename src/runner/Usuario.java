package runner;

public class Usuario {
	private String userName;
	private String password;
	private TipoDeRol role;
	
	public enum TipoDeRol {
		MÉDICO, ENFERMERA
	}
	
	public Usuario(String userName, String password, TipoDeRol rol) {
		setUserName(userName);
		setPassword(password);
		setRole(rol);
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		if (userName != null && userName.contains("@")) {
            this.userName = userName.trim();
        } else {
            throw new IllegalArgumentException("Usuario inv\u00E1lido: " + userName);
        }
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String userPassword){
		 if (userPassword != null && userPassword.length() >= 6) {
	            this.password = userPassword;
	        } else {
	            throw new IllegalArgumentException("La contraseña debe ser de al menos 6 caracteres.");
	        }
	}
	
	public TipoDeRol getRole(){
		return role;
	}
	
	public void setRole(TipoDeRol role){
		this.role = role;
	}
	
}
