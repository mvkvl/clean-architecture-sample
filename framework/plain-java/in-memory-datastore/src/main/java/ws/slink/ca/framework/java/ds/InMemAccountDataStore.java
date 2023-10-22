package ws.slink.ca.framework.java.ds;

import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.api.datastore.exception.AccountLockException;
import ws.slink.ca.api.datastore.exception.DataStoreException;
import ws.slink.ca.api.exception.UseCaseException;
import ws.slink.ca.domain.entity.Account;
import ws.slink.ca.domain.exception.DomainException;
import ws.slink.ca.domain.exception.UncheckedException;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InMemAccountDataStore implements AccountDataStore {

    private static final Duration LOCK_WAIT_TIME = Duration.ofMillis(1);

    private final AtomicLong idSequence = new AtomicLong(0);
    private final Map<Long, Account> accounts = new ConcurrentHashMap<>();
    private final Map<Long, ReadWriteLock> locks = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("UnusedReturnValue")
    public List<Account> list() {
        var result =  accounts.values()
            .stream()
            .sorted(Comparator.comparing(Account::getId))
            .toList()
        ;
        return result;
    }
    @Override
    public Account get(Long key) {
        return accounts.get(key);
    }
    @Override
    @SuppressWarnings("UnusedReturnValue")
    public Account getForUpdate(Long key) {
        tryGetLock(key);
        return accounts.get(key);
    }
    @Override
    public Account save(Account value) {
        if (value.getId() < 0) {
            value.setId(idSequence.incrementAndGet());
        }
        accounts.put(value.getId(), value);
        return accounts.get(value.getId());
    }
    @Override
    public void remove(Long key) {
        accounts.remove(key);
    }

    @Override
    public <T> T transactional(Callable<T> callable) {
        synchronized (accounts) {
            try {
                return callable.call();
            } catch(DomainException | UseCaseException | DataStoreException e) {
                throw e;
            } catch (Exception e) {
                throw new UncheckedException(e);
            } finally {
                cleanLocks();
            }
        }
    }
    @Override
    public void transactional(Runnable runnable) {
        synchronized (accounts) {
            try {
                runnable.run();
            } catch(DomainException | UseCaseException | DataStoreException e) {
                throw e;
            } catch (Exception e) {
                throw new UncheckedException(e);
            } finally {
                cleanLocks();
            }
        }
    }

    private void tryGetLock(Long key) {
        synchronized (locks) {
            locks.computeIfAbsent(key, k -> new ReentrantReadWriteLock());
        }
        try {
            // try to lock
            if (locks.get(key).writeLock().tryLock(LOCK_WAIT_TIME.toMillis(), TimeUnit.MILLISECONDS)) {
                // if ok start lock cleanup timer (do we need this?)
//                var cleanupThread = new Thread(() -> {
//                    try {
//                        Thread.sleep(LOCK_TTL.toMillis());
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                        throw new UncheckedException(e);
//                    }
//                });
//                cleanupThread.setDaemon(true);
//                cleanupThread.start();
            } else {
                throw new AccountLockException(key);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new UncheckedException(e);
        }
    }
    private void cleanLocks() {
        synchronized (locks) {
            locks.values().forEach(l -> {
                try {
                    l.writeLock().unlock();
                } catch(IllegalMonitorStateException e) {
                  // skip
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            locks.clear();
        }
    }

}
