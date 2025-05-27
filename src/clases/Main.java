package clases;

import interfaz.Login;
import interfaz.Principal;

public class Main {
	public static void main(String[] args) {
		Login login = new Login();
		//login.setUndecorated(true);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		login.setSize(1000, 500);
		while (login.isDisplayable()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (login.getAuth()) {
			Principal principal = new Principal();
			principal.setVisible(true);
		} else {
			System.exit(0);
		}
	}
}
