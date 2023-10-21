package ws.slink.ca.api.datastore.exception;

public class AccountLockException extends DataStoreException {

    private static final String MESSAGE = "could not lock account%sfor update";

    public AccountLockException() {
        super(String.format(MESSAGE, " "));
    }

    public AccountLockException(long accountId) {
        super(String.format(MESSAGE, String.format(" %d ", accountId)));
    }

}
