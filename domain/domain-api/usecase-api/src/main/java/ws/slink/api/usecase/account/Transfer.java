package ws.slink.api.usecase.account;

import ws.slink.ca.domain.entity.Account;

import java.util.List;

/**
 * Transfer from one account to another
 */
@FunctionalInterface
public interface Transfer {

    List<Account> execute(long accountIdFrom, long accountIdTo, double amount);

}
