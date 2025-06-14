package runner;

import entidades.CMF;

public class Auth {
    
    CMF cmf = CMF.getInstance();

    public boolean authUser(String userEmail, String userPassword) throws AuthenticationException {
        boolean response = false;
        
        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new AuthenticationException("El email no puede estar vac\u00EDo");
        }

        if (userPassword == null || userPassword.trim().isEmpty()) {
            throw new AuthenticationException("La contrase\u00F1a no puede estar vac\u00EDa");
        }

        if (!isValidEmail(userEmail)) {
            throw new AuthenticationException("Formato de email inv\u00E1lido");
        }

        if (userPassword.length() < 8) {
            throw new AuthenticationException("La contrase\u00F1a debe tener al menos 8 caracteres");
        }

        // Check doctor credentials
        if (cmf.getMedico() != null && userEmail.equals(cmf.getMedico().getEmail()) && userPassword.equals(cmf.getMedico().getPassword())) {
            Usuario.setEmail(cmf.getMedico().getEmail());
            Usuario.setPassword("");
            Usuario.setRole("M\u00C9DICO");
            response = true;
        }

        // Check nurse credentials
        if (cmf.getEnfermera() != null && userEmail.equals(cmf.getEnfermera().getEmail()) && userPassword.equals(cmf.getEnfermera().getPassword()) && response == false) {
            Usuario.setEmail(cmf.getEnfermera().getEmail());
            Usuario.setPassword("");
            Usuario.setRole("ENFERMERA");
            response = true;
        }

        if(response){
            return response;
        }
        else{
            throw new AuthenticationException("Credenciales incorrectas");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }
}