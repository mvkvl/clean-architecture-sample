package ws.slink.ca.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ws.slink.ca.api.usecase.Accounts;
import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.domain.usecase.account.actor.AccountsImpl;

@Configuration
@EnableJpaRepositories
public class AccountsConfig {

    @Bean
    Accounts accounts(@Autowired AccountDataStore ds) {
        return new AccountsImpl(ds);
    }

}
