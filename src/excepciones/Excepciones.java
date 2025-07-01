package excepciones;

public class Excepciones {

    public static class IllegalCiException extends IllegalArgumentException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IllegalCiException(String message) {
            super(message);
        }
    }

    public static class IllegalAddressException extends IllegalArgumentException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IllegalAddressException(String message) {
            super(message);
        }
    }

    public static class IllegalNameException extends IllegalArgumentException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IllegalNameException(String message) {
            super(message);
        }
    }

    public static class IllegalDateException extends IllegalArgumentException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IllegalDateException(String message) {
            super(message);
        }
    }

    public static class IllegalVaccinationException extends IllegalArgumentException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IllegalVaccinationException(String message) {
            super(message);
        }
    }
}
