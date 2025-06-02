package runner;

public class Auth {
	private static String email = "1";
	private static String password = "1";
	
	
	
	public boolean authUser(String userEmail, String userPassword) {
	    boolean response = false;
	 
	    if(userEmail.equals(email) && userPassword.equals(password)){
	    	response = true;
	    }
	    
	    Usuario.setEmail(email);
	    Usuario.setPassword(password);
	    Usuario.setRole("ADMIN");
	    
		return response;
	}
}
