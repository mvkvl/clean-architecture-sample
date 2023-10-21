package ws.slink.ca.domain.exception;

public class NegativeAmountException extends DomainException {

    private static final String MESSAGE = "amount should not be negative";

    public NegativeAmountException() {
        super(MESSAGE);
    }

}
