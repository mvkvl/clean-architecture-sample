package ws.slink.ca.api.usecase.account;

/**
 * Remove account
 */
@FunctionalInterface
public interface Delete {

    void execute(long accountId);

}
