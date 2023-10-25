package ws.slink.ca.api.usecase;

import ws.slink.ca.domain.entity.Account;

import java.util.List;

public interface Accounts {

    Account find(Long accountId);
    List<Account> list();
    Account create(double initialBalance);
    void delete(long accountId);
    Account deposit(long accountId, double amount);
    Account deduct(long accountId, double amount);
    List<Account> transfer(long accountIdFrom, long accountIdTo, double amount);

}
