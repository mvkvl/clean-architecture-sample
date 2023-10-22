package ws.slink.ca.domain.exception;

public class UncheckedException extends RuntimeException {

    public UncheckedException() {
        super();
    }

    public UncheckedException(String message) {
        super(message);
    }

    public UncheckedException(Throwable cause) {
        super(cause);
    }

}
