package runner;

import frontend.Login;
import frontend.Principal;

public class Runner {

    public void run() {
        // Mostrar login modal
        Login login = new Login(null);
        login.setVisible(true);

        // Si se autenticó correctamente, abrir ventana principal
        if (login.authenticado()) {
            Principal principal = new Principal(); // Tu única clase principal
            principal.setVisible(true);
        } else {
            System.exit(0); // Salir si no se autenticó
        }
    }
}


