package services;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import database.AuthCredentials;

public class AuthService {
	public boolean authUser(String user, String password) {
	    boolean response = false;
	 
	    ObjectMapper mapper = new ObjectMapper();
	    try {
            AuthCredentials persona = mapper.readValue(new File("database/credentials.json"), AuthCredentials.class);
            if(user.equals(persona.getUsername()) && password.equals(persona.getPassword())){
    			response = true;
    		}
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
		return response;
	}
}
