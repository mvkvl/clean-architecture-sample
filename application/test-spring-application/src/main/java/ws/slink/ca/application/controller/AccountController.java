package ws.slink.ca.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.slink.ca.api.presentator.AccountListPresentator;
import ws.slink.ca.api.presentator.AccountPresentator;
import ws.slink.ca.application.service.AccountServiceFacade;
import ws.slink.ca.domain.entity.Account;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/api/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountServiceFacade accounts;
    private final AccountPresentator<String> stringPresentator;
    private final AccountPresentator<Map<String, String>> simplePresentator;
    private final AccountListPresentator<String> stringListPresentator;

    @GetMapping("")
    public Object list(@RequestParam(value = "presentation", required = false) String presentation) {
        if (Objects.equals("simple", presentation)) {
            return accounts.list().stream().map(simplePresentator::present).toList();
        } else if (Objects.equals("string", presentation)) {
            return stringListPresentator.present(accounts.list());
        } else {
            return accounts.list();
        }
    }

    @GetMapping("/summary")
    public Object list() {
        var list = accounts.list();
        return Map.of(
                "accounts", list.stream().map(stringPresentator::present).toList(),
                "total", list.stream().mapToDouble(Account::getBalance).sum()
        );
    }

    @PostMapping("")
    public Account create(@RequestParam("balance") Double balance) {
        return accounts.create(balance);
    }
    @DeleteMapping("")
    public void create(@RequestParam("id") Long id) {
        accounts.delete(id);
    }
    @PostMapping("/a")
    public Account add(@RequestParam("id") Long accountId, @RequestParam("amount") Double amount) {
        accounts.add(accountId, amount);
        return accounts.find(accountId);
    }
    @PostMapping("/d")
    public Account deduct(@RequestParam("id") Long accountId, @RequestParam("amount") Double amount) {
        accounts.deduct(accountId, amount);
        return accounts.find(accountId);
    }
    @PutMapping("")
    public List<Account> transfer(@RequestParam("from") Long accountIdFrom,
                                  @RequestParam("to") Long accountIdTo,
                                  @RequestParam("amount") Double amount) {
        return accounts.transfer(accountIdFrom, accountIdTo, amount);
    }


}
