package excepciones;

public class Excepciones {

    public static class IllegalCiException extends IllegalArgumentException {
        public IllegalCiException(String message) {
            super(message);
        }
    }

    public static class IllegalAddressException extends IllegalArgumentException {
        public IllegalAddressException(String message) {
            super(message);
        }
    }

    public static class IllegalNameException extends IllegalArgumentException {
        public IllegalNameException(String message) {
            super(message);
        }
    }

    public static class IllegalDateException extends IllegalArgumentException {
        public IllegalDateException(String message) {
            super(message);
        }
    }

    public static class IllegalVaccinationException extends IllegalArgumentException {
        public IllegalVaccinationException(String message) {
            super(message);
        }
    }
}
