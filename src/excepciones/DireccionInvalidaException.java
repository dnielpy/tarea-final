package excepciones;

public class DireccionInvalidaException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DireccionInvalidaException() {
        super("La direcci�n proporcionada es inv�lida.");
    }

    public DireccionInvalidaException(String mensaje) {
        super(mensaje);
    }

    public DireccionInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

