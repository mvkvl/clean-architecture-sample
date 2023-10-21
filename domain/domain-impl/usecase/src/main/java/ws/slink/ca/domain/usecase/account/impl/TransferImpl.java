package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.api.usecase.account.Transfer;
import ws.slink.ca.api.datastore.DataStore;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.domain.usecase.account.AbstractAccountUseCase;

import java.util.List;

public class TransferImpl extends AbstractAccountUseCase implements Transfer {

    private final DataStore<Long, Account> dataStore;

    public TransferImpl(DataStore<Long, Account> dataStore) {
        super(dataStore);
        this.dataStore = dataStore;
    }

    @Override
    public List<Account> execute(long accountIdFrom , long accountIdTo, double amount) {
        checkAmount(amount);
        return dataStore.transactional(() -> {

            // check account existence
            getEntity(accountIdFrom);
            getEntity(accountIdTo);

            // try to lock for update
            Account accountFrom = checkBalance(getEntityForUpdate(accountIdFrom), amount);
            Account accountTo = getEntityForUpdate(accountIdTo);

            // update
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);

            // store
            dataStore.save(accountFrom);
            dataStore.save(accountTo);

            return List.of(accountFrom, accountTo);
        });
    }

}
