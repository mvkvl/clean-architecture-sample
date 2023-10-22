package ws.slink.ca.domain.presentator.account;

import ws.slink.ca.api.presentator.AccountPresentator;
import ws.slink.ca.domain.entity.Account;

public class StringAccountPresentator implements AccountPresentator<String> {

    @Override
    public String present(Account value) {
        return String.format("id: %d; balance: %.2f ₽", value.getId(), value.getBalance());
    }

}
