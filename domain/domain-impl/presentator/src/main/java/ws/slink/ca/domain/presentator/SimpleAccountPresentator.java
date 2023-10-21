package ws.slink.ca.domain.presentator;

import ws.slink.ca.api.presentator.AccountPresentator;
import ws.slink.ca.domain.entity.Account;

import java.util.Map;

public class SimpleAccountPresentator implements AccountPresentator<Map<String, String>> {

    @Override
    public Map<String, String> present(Account value) {
        return Map.of(
            "account-id", "" + value.getId(),
            "account-balance", String.format("%.2f₽", value.getBalance())
        );
    }

}
