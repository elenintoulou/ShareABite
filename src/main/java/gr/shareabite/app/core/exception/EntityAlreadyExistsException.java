package gr.shareabite.app.core.exception;


public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException(String entity, String message) {
        super(entity + " already exists: " + message);
    }
}
