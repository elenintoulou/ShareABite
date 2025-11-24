package gr.shareabite.app.core.exception;

public class NotExistingEntityException extends Exception {

    public NotExistingEntityException(String entity, String message) {
        super(entity + " does not exist " + message);
    }

}
