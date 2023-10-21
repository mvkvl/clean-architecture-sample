package ws.slink.api.usecase.account;

import ws.slink.ca.domain.entity.Account;

/**
 * Add amount to account
 */
@FunctionalInterface
public interface Add {

    Account execute(long accountId, double amount);

}
