package runner;

public class Auth {
    private static final String DEFAULT_DOCTOR_EMAIL = "jose@medicoscmf.com";
    private static final String DEFAULT_DOCTOR_PASSWORD = "12345678";

    private static final String DEFAULT_ENF_EMAIL = "yamila@enfermerascmf.com";
    private static final String DEFAULT_ENF_PASSWORD = "12345678";

    public boolean authUser(String userEmail, String userPassword) throws AuthenticationException {
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

        if (userEmail.equals(DEFAULT_DOCTOR_EMAIL) && userPassword.equals(DEFAULT_DOCTOR_PASSWORD)) {
            Usuario.setEmail(DEFAULT_DOCTOR_EMAIL);
            Usuario.setPassword("");
            Usuario.setRole("M\u00C9DICO");
        } else if (userEmail.equals(DEFAULT_ENF_EMAIL) && userPassword.equals(DEFAULT_ENF_PASSWORD)) {
            Usuario.setEmail(DEFAULT_ENF_EMAIL);
            Usuario.setPassword("");
            Usuario.setRole("ENFERMERA");
        } else {
            throw new AuthenticationException("Credenciales incorrectas");
        }
        return true;
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