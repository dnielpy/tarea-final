package runner;

import frontend.Login;
import frontend.Principal;

public class Runner {

    public void run() {
        // Mostrar login modal
        Login login = new Login(null);
        login.setVisible(true);

        // Si se autentic� correctamente, abrir ventana principal
        if (login.authenticado()) {
            Principal principal = new Principal(); // Tu �nica clase principal
            principal.setVisible(true);
        } else {
            System.exit(0); // Salir si no se autentic�
        }
    }
}


