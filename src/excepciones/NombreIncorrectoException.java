package excepciones;

public class NombreIncorrectoException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public NombreIncorrectoException(String mensaje) {
        super(mensaje);
    }

    public NombreIncorrectoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

