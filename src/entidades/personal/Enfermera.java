package entidades.personal;

import java.time.LocalDate;

public class Enfermera extends PersonalSanitario {   
    private boolean licenciatura;
    private int experiencia;

    // Constructor
    public Enfermera(String nombre, String primerApellido, String segundoApellido, int id, String ci,
                     boolean licenciatura, int experiencia, LocalDate fecha, String email, String password) {
        super(nombre, primerApellido, segundoApellido, id, ci, fecha, email, password, Usuario.TipoDeRol.ENFERMERA);
        setLicenciatura(licenciatura);
        setExperiencia(experiencia);
    }

    // Licenciatura
    public boolean getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(boolean licenciatura) {
        this.licenciatura = licenciatura;
    }

    // Anios de experiencia
    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        if (experiencia < 0) {
            throw new IllegalArgumentException("Los años de experiencia no puede ser negativa");
        }
        if (experiencia > 60) {
            throw new IllegalArgumentException("Los años de experiencia no puede ser mayor a 60 a\u00F1os");
        }
        this.experiencia = experiencia;
    }   
}
