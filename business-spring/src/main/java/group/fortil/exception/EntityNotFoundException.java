package group.fortil.exception;

public class EntityNotFoundException extends RuntimeException {

    String message = "Entity not found";

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super();
        this.message = message;
    }
}
