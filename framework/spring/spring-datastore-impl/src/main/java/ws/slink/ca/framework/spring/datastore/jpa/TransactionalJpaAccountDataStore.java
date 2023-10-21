package ws.slink.ca.framework.spring.datastore.jpa;

import org.hibernate.exception.LockAcquisitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ws.slink.ca.api.datastore.exception.DataStoreException;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.domain.exception.DomainException;
import ws.slink.ca.domain.usecase.account.exception.AccountNotFoundException;
import ws.slink.ca.api.datastore.exception.AccountLockException;
import ws.slink.ca.domain.usecase.common.exception.UseCaseException;
import ws.slink.ca.framework.spring.datastore.mapper.AccountMapper;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public class TransactionalJpaAccountDataStore {

    Logger LOG = LoggerFactory.getLogger(TransactionalJpaAccountDataStore.class);

    private final JpaAccountRepository repository;

    public TransactionalJpaAccountDataStore(@Autowired JpaAccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> list() {
        return repository.findAll().stream().map(AccountMapper::fromEntity).sorted(Comparator.comparing(Account::getId)).toList();
    }
    public Account find(Long key) {
        return repository.findById(key).map(AccountMapper::fromEntity).orElseThrow(AccountNotFoundException::new);
    }
    public Account getForUpdate(Long key) {
        try {
            return repository.findFirstById(key).map(AccountMapper::fromEntity).orElseThrow(() -> new AccountLockException(key));
        } catch(CannotAcquireLockException | LockAcquisitionException e) {
            throw new AccountLockException(key);
        }
    }

    public Account save(Account value) {
        return Optional.ofNullable(value)
            .map(AccountMapper::toEntity)
            .map(repository::save)
            .map(AccountMapper::fromEntity)
            .orElseThrow(IllegalArgumentException::new)
        ;
    }
    public void remove(Long key) {
        repository.deleteById(key);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transactional(Runnable runnable) {
        runnable.run();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public <T> T transactional(Callable<T> callable) {
        try {
            return callable.call();
        } catch(DomainException | UseCaseException | DataStoreException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
