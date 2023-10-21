package ws.slink.api.usecase.account;

import ws.slink.ca.domain.entity.Account;

/**
 * Find account
 */
@FunctionalInterface
public interface Find {

    Account execute(long accountId);

}
