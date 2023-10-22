package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.api.usecase.account.Create;
import ws.slink.ca.domain.entity.Account;

public class CreateImpl implements Create {

    private final AccountDataStore dataStore;

    public CreateImpl(AccountDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Account execute(double initialBalance) {
        return dataStore.save(new Account(initialBalance));
    }

}
