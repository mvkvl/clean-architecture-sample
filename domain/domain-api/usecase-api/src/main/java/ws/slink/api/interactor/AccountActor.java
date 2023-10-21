package ws.slink.api.interactor;

import ws.slink.ca.domain.entity.Account;

import java.util.List;

public interface AccountActor {

    Account find(Long accountId);
    List<Account> list();
    Account create(double initialBalance);
    void delete(long accountId);
    Account add(long accountId, double amount);
    Account deduct(long accountId, double amount);
    List<Account> transfer(long accountIdFrom, long accountIdTo, double amount);

}
