package backend;

import frontend.Login;
import frontend.Principal;

public class Main {
	public static void main(String[] args) {
		Login login = new Login();
		//login.setUndecorated(true);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
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