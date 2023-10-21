package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.api.usecase.account.Delete;
import ws.slink.ca.api.datastore.DataStore;
import ws.slink.ca.domain.entity.Account;

public class DeleteImpl implements Delete {

    private final DataStore<Long, Account> dataStore;

    public DeleteImpl(DataStore<Long, Account> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(long accountId) {
        dataStore.remove(accountId);
    }

}
