package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.api.usecase.account.Delete;

public class DeleteImpl implements Delete {

    private final AccountDataStore dataStore;

    public DeleteImpl(AccountDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(long accountId) {
        dataStore.remove(accountId);
    }

}
