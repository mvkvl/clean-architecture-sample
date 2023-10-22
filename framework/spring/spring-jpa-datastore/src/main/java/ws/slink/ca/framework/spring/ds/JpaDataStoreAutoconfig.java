package ws.slink.ca.framework.spring.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.framework.spring.ds.jpa.JpaAccountDataStore;
import ws.slink.ca.framework.spring.ds.jpa.JpaAccountRepository;
import ws.slink.ca.framework.spring.ds.jpa.TransactionalJpaAccountDataStore;

@Configuration
@EnableJpaRepositories
@ConditionalOnProperty(value = "ca.account.datastore.type", havingValue = "jpa")
public class JpaDataStoreAutoconfig {

    private static final Logger LOG = LoggerFactory.getLogger(JpaDataStoreAutoconfig.class);

    @Bean
    TransactionalJpaAccountDataStore txJpaAccountDataStore(@Autowired JpaAccountRepository repository) {
        LOG.info("Instantiating transactional jpa account data store");
        return new TransactionalJpaAccountDataStore(repository);
    }

    @Bean
    @Qualifier("jpa")
    AccountDataStore jpaAccountDataStore(TransactionalJpaAccountDataStore txAccountDS) {
        LOG.info("Instantiating jpa account data store");
        return new JpaAccountDataStore(txAccountDS);
    }

}
