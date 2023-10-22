package ws.slink.ca.domain.usecase.account.impl;

import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.api.usecase.account.Deduct;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.domain.usecase.account.AbstractAccountUseCase;

public class DeductImpl extends AbstractAccountUseCase implements Deduct {

    private final AccountDataStore dataStore;

    public DeductImpl(AccountDataStore dataStore) {
        super(dataStore);
        this.dataStore = dataStore;
    }

    @Override
    public Account execute(long accountId, double amount) {
        checkAmount(amount);
        return dataStore.transactional(() -> {
            Account account = checkBalance(getEntityForUpdate(accountId), amount);
            account.setBalance(account.getBalance() - amount);
            dataStore.save(account);
            return account;
        });
    }

}


//        try {
//            boolean startedNewTx = dataStore.getTxManager().start(TxMode.USE_EXISTING);
//            Account account = checkBalance(getEntity(accountId), amount);
//            account.setBalance(account.getBalance() - amount);
//                    dataStore.save(account);
//            if (startedNewTx) {
//                dataStore.getTxManager().commit();
//            }
//        } catch (Exception e) {
//            dataStore.getTxManager().rollback();
//        }
