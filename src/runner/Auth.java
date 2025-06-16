package runner;

import entidades.CMF;

public class Auth {

    CMF cmf = CMF.getInstance();

    public Usuario authUser(String userEmail, String userPassword) throws AuthenticationException {
        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new AuthenticationException("El email no puede estar vac�o");
        }

        if (userPassword == null || userPassword.trim().isEmpty()) {
            throw new AuthenticationException("La contrase�a no puede estar vac�a");
        }

        if (!isValidEmail(userEmail)) {
            throw new AuthenticationException("Formato de email inv�lido");
        }

        if (userPassword.length() < 8) {
            throw new AuthenticationException("La contrase�a debe tener al menos 8 caracteres");
        }

        // M�dico
        if (cmf.getMedico() != null &&
            userEmail.equals(cmf.getMedico().getUser().getUserName()) &&
            userPassword.equals(cmf.getMedico().getUser().getPassword())) {

            Usuario usuario = new Usuario(userEmail, userPassword, Usuario.TipoDeRol.M�DICO);
            cmf.setUsuario(usuario);
            return usuario;
        }

        // Enfermera
        if (cmf.getEnfermera() != null &&
            userEmail.equals(cmf.getEnfermera().getUser().getUserName()) &&
            userPassword.equals(cmf.getEnfermera().getUser().getPassword())) {

            Usuario usuario = new Usuario(userEmail, userPassword, Usuario.TipoDeRol.ENFERMERA);
            cmf.setUsuario(usuario);
            return usuario;
        }

        throw new AuthenticationException("Credenciales incorrectas");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }
}
