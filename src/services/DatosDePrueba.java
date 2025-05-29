package services;

import java.util.ArrayList;


import entidades.CMF;
import entidades.HistoriaClinica;
import entidades.Mujer;
import entidades.Paciente;

public class DatosDePrueba {
	public boolean authUser(String user, String password) {
	    boolean response = false;
	 
	    if(user.equals("1") && password.equals("1")){
	    	response = true;
	    }
		return response;
	}
	public void cargarDatos(){
		CMF cmf = new CMF(1, "Consultorio El Cotorro", "Herbert San Jacobo");
		
		//Paciente javier = new Paciente(0, null, 0, null);
	}
}

