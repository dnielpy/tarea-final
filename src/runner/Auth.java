package runner;

import entidades.CMF;

public class Auth {

    CMF cmf = CMF.getInstance();

    public Usuario authUser(String userEmail, String userPassword) throws AuthenticationException {
        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new AuthenticationException("El email no puede estar vacío");
        }
    
        if (userPassword == null || userPassword.trim().isEmpty()) {
            throw new AuthenticationException("La contraseña no puede estar vacía");
        }
    
        if (!isValidEmail(userEmail)) {
            throw new AuthenticationException("Formato de email inválido");
        }
    
        if (userPassword.length() < 8) {
            throw new AuthenticationException("La contraseña debe tener al menos 8 caracteres");
        }
    
        Usuario usuario = null;
    
        // Médico
        if (cmf.getMedico() != null &&
            userEmail.equals(cmf.getMedico().getUser().getUserName()) &&
            userPassword.equals(cmf.getMedico().getUser().getPassword())) {
    
            usuario = new Usuario(userEmail, userPassword, Usuario.TipoDeRol.MÉDICO);
        }
    
        // Enfermera
        else if (cmf.getEnfermera() != null &&
                 userEmail.equals(cmf.getEnfermera().getUser().getUserName()) &&
                 userPassword.equals(cmf.getEnfermera().getUser().getPassword())) {
    
            usuario = new Usuario(userEmail, userPassword, Usuario.TipoDeRol.ENFERMERA);
        }
    
        if (usuario != null) {
            cmf.setUsuario(usuario);
        } else {
            throw new AuthenticationException("Credenciales incorrectas");
        }
    
        return usuario;
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
