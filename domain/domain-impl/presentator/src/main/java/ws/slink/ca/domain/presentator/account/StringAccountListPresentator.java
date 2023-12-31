package ws.slink.ca.domain.presentator.account;

import ws.slink.ca.api.presentator.AccountListPresentator;
import ws.slink.ca.domain.entity.Account;

import java.util.Collection;
import java.util.stream.Collectors;

public class StringAccountListPresentator implements AccountListPresentator<String> {

    @Override
    public String present(Collection<Account> value) {
        return value.stream()
            .map(a -> String.format("id: %d; balance: %.2f ₽", a.getId(), a.getBalance()))
            .collect(Collectors.joining("\n"))
        ;
    }

}
