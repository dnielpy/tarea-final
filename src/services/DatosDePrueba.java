package services;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import database.AuthCredentials;
import entidades.CMF;
import entidades.HistoriaClinica;
import entidades.Mujer;
import entidades.Paciente;

public class DatosDePrueba {
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
	public void cargarDatos(){
		CMF cmf = new CMF(1, "Consultorio El Cotorro", "Herbert San Jacobo");
		
		Paciente javier = new Paciente(0, , null, 0, null);
	}
}

