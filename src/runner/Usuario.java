package runner;

public class Usuario {
	private static String email;
	private static String password;
	private static String role;
	
	public static void setEmail(String userEmail){
		email = userEmail;
	}
	
	public static void setPassword(String userPassword){
		password = userPassword;
	}
	public static void setRole(String userRole){
		role = userRole;
	}
	
	public static String getEmail(){
		return email;
	}
	public static String getPassword(){
		return password;
	}
	public static String getRole(){
		return role;
	}
}
