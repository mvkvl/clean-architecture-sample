package ws.slink.ca.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ws.slink.ca.api.actor.AccountActor;
import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.domain.usecase.account.actor.AccountActorImpl;

@Configuration
@EnableJpaRepositories
public class AccountsConfig {

    @Bean
    AccountActor accounts(@Autowired @Qualifier("jpa") AccountDataStore ds) {
        return new AccountActorImpl(ds);
    }

}
