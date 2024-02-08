package group.fortil.exception;

public class EmailAlreadyUseException extends RuntimeException {
    String message = "Email already exist for another user";

    public EmailAlreadyUseException() {
        super();
    }

    public EmailAlreadyUseException(String message) {
        super();
        this.message = message;
    }
}
