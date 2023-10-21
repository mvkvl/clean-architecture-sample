package ws.slink.api.usecase.account;

/**
 * Remove account
 */
@FunctionalInterface
public interface Delete {

    void execute(long accountId);

}
