package ws.slink.ca.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.slink.ca.api.datastore.exception.DataStoreException;
import ws.slink.ca.domain.entity.Account;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConcurrencyTestService {

    private final AccountServiceFacade accounts;

    private final AtomicBoolean flag1 = new AtomicBoolean(false);
    private final AtomicBoolean flag2 = new AtomicBoolean(false);
    private final AtomicBoolean flag3= new AtomicBoolean(false);
    private final AtomicBoolean flag4= new AtomicBoolean(false);
    private final AtomicBoolean flag5 = new AtomicBoolean(false);
    private final AtomicBoolean flag6 = new AtomicBoolean(false);

    private Long id1, id2, id3;
    private Random rnd = new Random();

    public void start() {

        id1 = accounts.create(100.0).getId();
        id2 = accounts.create(100.0).getId();
        id3 = accounts.create(100.0).getId();

        createProcessThread("thread1", id1, id2, flag1).start();
        createProcessThread("thread2", id2, id1, flag2).start();
        createProcessThread("thread3", id1, id3, flag3).start();
        createProcessThread("thread4", id3, id1, flag4).start();
        createProcessThread("thread5", id2, id3, flag5).start();
        createProcessThread("thread6", id3, id2, flag6).start();

    }

    public List<Account> stop() {

        // stop threads
        flag1.set(false);
        flag2.set(false);
        flag3.set(false);
        flag4.set(false);
        flag5.set(false);
        flag6.set(false);

        var result = accounts.list();

        // remove accounts
        if (id1 != null) {
            accounts.delete(id1);
        }
        if (id2 != null) {
            accounts.delete(id2);
        }
        if (id3 != null) {
            accounts.delete(id3);
        }

        return result;
    }

    private Thread createProcessThread(String name, Long from, Long to, AtomicBoolean flag) {
        var thread = new Thread(() -> {
            flag.set(true);
            while (flag.get()) {
                try {
                    accounts.transfer(from, to, 10.0);
                } catch(DataStoreException e) {
                    log.debug("exception {} in {}: {}", e.getClass().getSimpleName(), name, e.getMessage());
                } catch (Exception e) {
                    log.warn("exception {} in {}: {}", e.getClass().getSimpleName(), name, e.getMessage());
                }
                try {
                    Thread.sleep(rnd.nextInt(100) + 50L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.setDaemon(true);
        return thread;
    }

}
