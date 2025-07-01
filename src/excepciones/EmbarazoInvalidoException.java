package excepciones;

public class EmbarazoInvalidoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmbarazoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
