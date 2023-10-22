package ws.slink.ca.api.exception.account;

import ws.slink.ca.api.exception.UseCaseException;

public class InsufficientBalanceException extends UseCaseException {

    private static final String MESSAGE = "insufficient account%sbalance%s";

    public InsufficientBalanceException() {
        super(String.format(MESSAGE, " ", ""));
    }
    public InsufficientBalanceException(Long accountId) {
        super(String.format(MESSAGE, String.format(" #%d ", accountId), ""));
    }
    public InsufficientBalanceException(Long accountId, Double balance) {
        super(String.format(MESSAGE, String.format(" #%d ", accountId), String.format(": %.2f", balance)));
    }

}
