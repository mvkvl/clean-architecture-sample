package ws.slink.ca.api.usecase.account;

import ws.slink.ca.domain.entity.Account;

/**
 * Add amount to account
 */
@FunctionalInterface
public interface Deposit {

    Account execute(long accountId, double amount);

}
