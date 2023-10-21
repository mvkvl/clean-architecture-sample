package ws.slink.ca.domain.usecase.account;

import ws.slink.ca.api.datastore.DataStore;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.domain.exception.NegativeAmountException;
import ws.slink.ca.domain.usecase.account.exception.InvalidBalanceException;
import ws.slink.ca.domain.usecase.common.AbstractUseCase;

public class AbstractAccountUseCase extends AbstractUseCase<Long, Account> {

    protected AbstractAccountUseCase(final DataStore<Long, Account> dataStore) {
        super(dataStore);
    }

    protected Account checkBalance(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new InvalidBalanceException();
        }
        return account;
    }

    protected void checkAmount(double amount) {
        if (amount < 0) {
            throw new NegativeAmountException();
        }
    }

    protected String getEntityId(Long id) {
        return String.format("account %d", id);
    }

    protected Account getEntity(Long id) {
        return super.getEntity(id);
    }

    protected Account getEntityForUpdate(Long id) {
        return super.getEntityForUpdate(id);
    }

}
