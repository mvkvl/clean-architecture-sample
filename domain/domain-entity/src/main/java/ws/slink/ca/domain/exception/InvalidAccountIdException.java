package ws.slink.ca.domain.exception;

public class InvalidAccountIdException extends DomainException {

    private static final String MESSAGE = "account id should be above zero";

    public InvalidAccountIdException() {
        super(MESSAGE);
    }

}
