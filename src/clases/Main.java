package clases;

import interfaz.Login;

public class Main {
	public static void main(String[] args) {
		Login login = new Login();
		//login.setUndecorated(true);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		login.setSize(1000, 500);
	}
}
