package message.error;

public class FailedToStartException extends RuntimeException{
    public FailedToStartException(String message, Throwable e) {
        super(message, e);
    }
}
