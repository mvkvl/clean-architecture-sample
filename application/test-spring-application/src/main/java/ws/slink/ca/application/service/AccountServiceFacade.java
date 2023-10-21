package ws.slink.ca.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.slink.api.interactor.AccountActor;
import ws.slink.ca.domain.entity.Account;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceFacade {

    private final AccountActor accounts;

    public List<Account> list() {
        log.warn("list accounts");
        return accounts.list();
    }
    public Account find(Long id) {
        log.warn("find account #{}", id);
        return accounts.find(id);
    }
    public Account add(Long id, Double amount) {
        log.warn("add {} to account #{}", amount, id);
        return accounts.add(id, amount);
    }
    public Account deduct(Long id, Double amount) {
        log.warn("deduct {} from account #{}", amount, id);
        return accounts.deduct(id, amount);
    }
    public Account create(Double balance) {
        log.warn("create account with initial balance {}", balance);
        return accounts.create(balance);
    }
    public void delete(Long id) {
        log.warn("delete account #{}", id);
        accounts.delete(id);
    }

    public List<Account> transfer(Long from, Long to, Double amount) {
        return accounts.transfer(from, to, amount);
    }

}
