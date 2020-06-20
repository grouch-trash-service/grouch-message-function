package message.error;

public class FailedToStartException extends RuntimeException {
    public FailedToStartException(final String message, final Throwable e) {
        super(message, e);
    }
}
