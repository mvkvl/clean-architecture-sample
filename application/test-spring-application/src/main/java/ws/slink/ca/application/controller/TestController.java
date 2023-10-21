package ws.slink.ca.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.slink.ca.application.service.ConcurrencyTestService;
import ws.slink.ca.domain.entity.Account;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    private final ConcurrencyTestService tests;
    @PostMapping("/start")
    public void start() {
        tests.start();
    }

    @PostMapping("/stop")
    public List<Account> stop() {
        return tests.stop();
    }

}
