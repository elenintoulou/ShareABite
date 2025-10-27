package gr.shareabite.app.exceptions;

public class NotExistingEntityException extends Exception {
    private static final String DEFAULT_CODE = "Does not exist";

    public NotExistingEntityException(String code, String message) {
        super(code + DEFAULT_CODE + message);
    }

}
