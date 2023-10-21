package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.api.usecase.account.Create;
import ws.slink.ca.api.datastore.DataStore;
import ws.slink.ca.domain.entity.Account;

public class CreateImpl implements Create {

    private final DataStore<Long, Account> dataStore;

    public CreateImpl(DataStore<Long, Account> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Account execute(double initialBalance) {
        return dataStore.save(new Account(initialBalance));
    }

}
