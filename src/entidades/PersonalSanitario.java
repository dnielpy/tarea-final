package entidades;

public abstract class PersonalSanitario extends Persona{

    protected String email;
    protected String password;


    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email.trim();
        } else {
            throw new IllegalArgumentException("Email inv\u00E1lido: " + email);
        }
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
    }

}
