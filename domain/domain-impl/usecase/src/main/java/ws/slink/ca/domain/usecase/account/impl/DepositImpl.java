package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.api.usecase.account.Deposit;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.domain.usecase.account.AbstractAccountUseCase;

public class DepositImpl extends AbstractAccountUseCase implements Deposit {

    private final AccountDataStore dataStore;

    public DepositImpl(AccountDataStore dataStore) {
        super(dataStore);
        this.dataStore = dataStore;
    }

    @Override
    public Account execute(long accountId, double amount) {
        checkAmount(amount);
        return dataStore.transactional(() -> {
            Account account = getEntityForUpdate(accountId);
            account.setBalance(account.getBalance() + amount);
            dataStore.save(account);
            return account;
        });
    }

}

//        try {
//            boolean startedNewTx = dataStore.getTxManager().start(TxMode.USE_EXISTING);
//            Account account = getEntity(accountId);
//            account.setBalance(account.getBalance() + amount);
//            dataStore.save(account);
//            if (startedNewTx) {
//                dataStore.getTxManager().commit();
//            }
//        } catch (Exception e) {
//            dataStore.getTxManager().rollback();
//        }
