package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.api.usecase.account.Find;
import ws.slink.ca.api.datastore.DataStore;
import ws.slink.ca.domain.entity.Account;

public class FindImpl implements Find {

    private final DataStore<Long, Account> dataStore;

    public FindImpl(DataStore<Long, Account> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Account execute(long accountId) {
        return dataStore.get(accountId);
    }

}
