package ws.slink.ca.domain.usecase.account.actor;

import ws.slink.ca.api.actor.AccountActor;
import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.domain.usecase.account.impl.AddImpl;
import ws.slink.ca.domain.usecase.account.impl.CreateImpl;
import ws.slink.ca.domain.usecase.account.impl.DeductImpl;
import ws.slink.ca.domain.usecase.account.impl.DeleteImpl;
import ws.slink.ca.domain.usecase.account.impl.TransferImpl;

import java.util.List;

public class AccountActorImpl implements AccountActor {

    private final AccountDataStore dataStore;

    public AccountActorImpl(final AccountDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Account find(Long accountId) {
        return dataStore.get(accountId);
    }
    @Override
    public List<Account> list() {
        return dataStore.list();
    }
    @Override
    public Account create(double initialBalance) {
        return new CreateImpl(dataStore).execute(initialBalance);
    }
    @Override
    public void delete(long accountId) {
        new DeleteImpl(dataStore).execute(accountId);
    }
    @Override
    public Account add(long accountId, double amount) {
        return new AddImpl(dataStore).execute(accountId, amount);
    }
    @Override
    public Account deduct(long accountId, double amount) {
        return new DeductImpl(dataStore).execute(accountId, amount);
    }
    @Override
    public List<Account> transfer(long accountIdFrom, long accountIdTo, double amount) {
        return new TransferImpl(dataStore).execute(accountIdFrom, accountIdTo, amount);
    }

}
