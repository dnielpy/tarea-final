package runner;

import javax.swing.SwingUtilities;

import entidades.CMF;
import frontend.Login;
import frontend.Principal;
import frontend.Splash;

public class Runner {

    public static void iniciarApp() {
        Splash splash = new Splash(new Runnable() {
            @Override
            public void run() {
                // Ejecutar en EDT para seguridad con Swing
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mostrarLogin();
                    }
                });
            }
        });
        splash.setVisible(true);
    }

    private static void mostrarLogin() {
        Login login = new Login(null);
        login.setVisible(true);

        if (login.authenticado()) {
            Principal principal = new Principal();
            principal.setVisible(true);
        } else {
            // Volver a mostrar login si falla la autenticación
            mostrarLogin();
        }
    }

    public static void logout() {
        CMF cmf = CMF.getInstance();
        cmf.setUsuario(null);
        mostrarLogin();
    }
}
