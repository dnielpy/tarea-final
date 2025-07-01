package excepciones;

public class DireccionInvalidaException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DireccionInvalidaException() {
        super("La dirección proporcionada es inválida.");
    }

    public DireccionInvalidaException(String mensaje) {
        super(mensaje);
    }

    public DireccionInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

