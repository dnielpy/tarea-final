package excepciones;

public class ApellidoIncorrectoException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public ApellidoIncorrectoException(String mensaje) {
        super(mensaje);
    }

    public ApellidoIncorrectoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
