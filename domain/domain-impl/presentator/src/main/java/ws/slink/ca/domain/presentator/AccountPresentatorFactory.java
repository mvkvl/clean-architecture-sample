package ws.slink.ca.domain.presentator;

import ws.slink.ca.api.presentator.AccountListPresentator;
import ws.slink.ca.api.presentator.AccountPresentator;
import ws.slink.ca.domain.presentator.account.SimpleAccountPresentator;
import ws.slink.ca.domain.presentator.account.StringAccountListPresentator;
import ws.slink.ca.domain.presentator.account.StringAccountPresentator;

public class AccountPresentatorFactory {

    @SuppressWarnings("rawtypes")
    public AccountPresentator createItemPresentator(PresentatorType type) {
        return switch (type) {
            case SIMPLE -> new SimpleAccountPresentator();
            case STRING -> new StringAccountPresentator();
            default -> throw new IllegalArgumentException("invalid presenator type for item: " + type);
        };
    }

    @SuppressWarnings("rawtypes")
    public AccountListPresentator createCollectionPresentator(PresentatorType type) {
        return switch (type) {
            case STRING_LIST -> new StringAccountListPresentator();
            case SIMPLE,STRING ->  throw new IllegalArgumentException("invalid presentator type for list: " + type);
        };
    }

}
