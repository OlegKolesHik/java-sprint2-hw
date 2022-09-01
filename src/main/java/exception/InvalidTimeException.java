package exception;

public class InvalidTimeException extends RuntimeException {
    InvalidTimeException(String message) {
        super(message);
    }
}
