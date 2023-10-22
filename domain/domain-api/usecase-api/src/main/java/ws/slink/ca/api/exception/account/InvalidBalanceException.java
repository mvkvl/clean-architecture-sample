package ws.slink.ca.api.exception.account;

import ws.slink.ca.api.exception.UseCaseException;

public class InvalidBalanceException extends UseCaseException {

    private static final String MESSAGE = "invalid account balance";

    public InvalidBalanceException() {
        super(MESSAGE);
    }

}
