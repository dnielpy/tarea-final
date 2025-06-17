package runner;

import entidades.CMF;
import frontend.Login;
import frontend.Principal;

public class Runner {	
    public static void login() {
        Login login = new Login(null);
        login.setVisible(true);

        // Si se autentic� correctamente, abrir ventana principal
        if (login.authenticado()) {
            Principal principal = new Principal(); // Tu �nica clase principal
            principal.setVisible(true);
        } else {
            login.setVisible(true); // Salir si no se autentic�
        }
    }

    public static void logout() {
    	CMF cmf = CMF.getInstance();
    	cmf.setUsuario(null);
        Login login = new Login(null);
        login.setVisible(true);

        // Si se autentic� correctamente, abrir ventana principal
        if (login.authenticado()) {
            Principal principal = new Principal(); // Tu �nica clase principal
            principal.setVisible(true);
        } else {
            login.setVisible(true); // Salir si no se autentic�
        }
    }
}
