package gr.shareabite.app.exceptions;


public class EntityAlreadyExistsException extends Exception {
    private static final String DEFAULT_CODE = "Already exists";

    public EntityAlreadyExistsException(String code, String message) {
        super(code + DEFAULT_CODE + message);
    }
}
