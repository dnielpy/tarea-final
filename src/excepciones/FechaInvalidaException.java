package excepciones;

public class FechaInvalidaException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }

    public FechaInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
