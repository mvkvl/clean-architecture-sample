package ws.slink.ca.framework.spring.ds.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.api.datastore.exception.AccountLockException;

import java.util.List;
import java.util.concurrent.Callable;

public class JpaAccountDataStore implements AccountDataStore {

    private final TransactionalJpaAccountDataStore tds;

    public JpaAccountDataStore(@Autowired TransactionalJpaAccountDataStore ds) {
        this.tds = ds;
    }

    // region - public API

    @Override
    public List<Account> list() {
        return tds.list();
    }

    @Override
    public Account get(Long key) {
        return tds.find(key);
    }

    public Account getForUpdate(Long key) {
        return tds.getForUpdate(key);
    }

    @Override
    public Account save(Account value) {
        return tds.save(value);
    }

    @Override
    public void remove(Long key) {
        tds.remove(key);
    }

    @Override
    public void transactional(Runnable runnable) {
        try {
            tds.transactional(runnable);
        } catch (CannotAcquireLockException e) {
            throw new AccountLockException();
        }
    }

    @Override
    public <T> T transactional(Callable<T> callable) {
        try {
            return tds.transactional(callable);
        } catch (CannotAcquireLockException e) {
            throw new AccountLockException();
        }
    }

    // endregion

}
