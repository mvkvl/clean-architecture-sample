package ws.slink.ca.domain.usecase.account;

import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.api.exception.account.InvalidBalanceException;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.domain.exception.NegativeAmountException;
import ws.slink.ca.domain.usecase.common.AbstractUseCase;

public class AbstractAccountUseCase extends AbstractUseCase<Long, Account> {

    protected AbstractAccountUseCase(final AccountDataStore dataStore) {
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

}
