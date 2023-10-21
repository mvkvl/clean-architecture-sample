package ws.slink.ca.framework.spring.datastore.mapper;

import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.framework.spring.datastore.entity.AccountEntity;

public class AccountMapper {

    private AccountMapper() {
    }

    public static Account fromEntity(AccountEntity entity) {
        return new Account(entity.getId(), entity.getBalance());
    }
    public static AccountEntity toEntity(Account account) {
        var entity = new AccountEntity();
        if (account.getId() >= 0) {
            entity.setId(account.getId());
        }
        entity.setBalance(account.getBalance());
        return entity;
    }

}
