public class UpdaterException extends RuntimeException {
    public UpdaterException(String message) {
        super(message);
    }

    public UpdaterException(Throwable cause) {
        super(cause);
    }

    public UpdaterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdaterException(Throwable cause, String format, Object... args) {
        super(String.format(format, args), cause);
    }
}
