package runner;

public class Auth {
	public boolean authUser(String user, String password) {
	    boolean response = false;
	 
	    if(user.equals("1") && password.equals("1")){
	    	response = true;
	    }
		return response;
	}
}
