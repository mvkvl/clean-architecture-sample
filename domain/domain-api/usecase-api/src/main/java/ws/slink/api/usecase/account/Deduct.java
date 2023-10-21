package ws.slink.api.usecase.account;

import ws.slink.ca.domain.entity.Account;

/**
 * Deduct amount from account
 */
@FunctionalInterface
public interface Deduct {

    Account execute(long accountId, double amount);

}
