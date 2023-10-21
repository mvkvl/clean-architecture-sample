package ws.slink.ca.domain.usecase.account.exception;

import ws.slink.ca.domain.usecase.common.exception.UseCaseException;

public class AccountNotFoundException extends UseCaseException {

    private static final String MESSAGE = "account%snot found";

    public AccountNotFoundException() {
        super(String.format(MESSAGE, " "));
    }

    public AccountNotFoundException(long accountId) {
        super(String.format(MESSAGE, String.format(" %d ", accountId)));
    }

}
