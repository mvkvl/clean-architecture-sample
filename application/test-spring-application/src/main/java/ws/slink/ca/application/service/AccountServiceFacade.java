package ws.slink.ca.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.slink.ca.api.actor.AccountActor;
import ws.slink.ca.domain.entity.Account;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceFacade {

    private final AccountActor accounts;

    public List<Account> list() {
        log.debug("list accounts");
        return accounts.list();
    }
    public Account find(Long id) {
        log.debug("find account #{}", id);
        return accounts.find(id);
    }
    public Account add(Long id, Double amount) {
        log.debug("add {} to account #{}", amount, id);
        return accounts.add(id, amount);
    }
    public Account deduct(Long id, Double amount) {
        log.debug("deduct {} from account #{}", amount, id);
        return accounts.deduct(id, amount);
    }
    public Account create(Double balance) {
        log.debug("create account with initial balance {}", balance);
        return accounts.create(balance);
    }
    public void delete(Long id) {
        log.debug("delete account #{}", id);
        accounts.delete(id);
    }

    public List<Account> transfer(Long from, Long to, Double amount) {
        return accounts.transfer(from, to, amount);
    }

}
