package ws.slink.ca.api.usecase.account;

import ws.slink.ca.domain.entity.Account;

/**
 * Create new account with initial balance
 */
@FunctionalInterface
public interface Create {

    Account execute(double initialBalance);

}
