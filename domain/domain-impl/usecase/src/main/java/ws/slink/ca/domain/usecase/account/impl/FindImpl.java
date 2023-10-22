package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.api.usecase.account.Find;
import ws.slink.ca.domain.entity.Account;

public class FindImpl implements Find {

    private final AccountDataStore dataStore;

    public FindImpl(AccountDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Account execute(long accountId) {
        return dataStore.get(accountId);
    }

}
