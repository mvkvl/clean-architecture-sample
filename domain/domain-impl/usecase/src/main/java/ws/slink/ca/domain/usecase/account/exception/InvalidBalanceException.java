package ws.slink.ca.domain.usecase.account.exception;

import ws.slink.ca.domain.usecase.common.exception.UseCaseException;

public class InvalidBalanceException extends UseCaseException {

    private static final String MESSAGE = "invalid account balance";

    public InvalidBalanceException() {
        super(MESSAGE);
    }

}
