package entidades.personal;

import java.time.LocalDate;

public class Medico extends PersonalSanitario {

    public Medico(String nombre, String primerApellido, String segundoApellido, int identificador, String ci,
                  LocalDate fechaInicio, String email, String password) {
        super(nombre, primerApellido, segundoApellido, identificador, ci, fechaInicio, email, password, Usuario.TipoDeRol.MÉDICO);
    }
}
