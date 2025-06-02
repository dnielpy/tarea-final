package runner;

public class Auth {
    private static final String DEFAULT_EMAIL = "admin@system.com";
    private static final String DEFAULT_PASSWORD = "12345678";

    public boolean authUser(String userEmail, String userPassword) throws AuthenticationException {
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

        boolean credentialsMatch = userEmail.equals(DEFAULT_EMAIL) && 
                                   userPassword.equals(DEFAULT_PASSWORD);

        if (!credentialsMatch) {
            throw new AuthenticationException("Credenciales incorrectas");
        }

        Usuario.setEmail(DEFAULT_EMAIL);
        Usuario.setPassword("");
        Usuario.setRole("ADMIN");

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