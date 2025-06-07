package runner;

import frontend.Login;

public class Runner {
	
	public void run(){
		Login login = new Login();
		login.setVisible(true);
		while (login.isDisplayable()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
