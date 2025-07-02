package excepciones;

public class CIInvalidoException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public CIInvalidoException(String mensaje) {
        super(mensaje);
    }

    public CIInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
