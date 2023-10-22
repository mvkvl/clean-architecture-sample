package ws.slink.ca.framework.spring.datastore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ws.slink.ca.framework.spring.datastore.jpa.JpaAccountDataStore;
import ws.slink.ca.framework.spring.datastore.jpa.JpaAccountRepository;
import ws.slink.ca.framework.spring.datastore.jpa.TransactionalJpaAccountDataStore;

@Configuration
@EnableJpaRepositories
public class JpaDataStoreAutoconfig {

    private static final Logger LOG = LoggerFactory.getLogger(JpaDataStoreAutoconfig.class);

    @Bean
    TransactionalJpaAccountDataStore txJpaAccountDataStore(@Autowired JpaAccountRepository repository) {
        LOG.info("Instantiating transactional jpa account data store");
        return new TransactionalJpaAccountDataStore(repository);
    }

    @Bean
    @Qualifier("jpa")
    JpaAccountDataStore jpaAccountDataStore(TransactionalJpaAccountDataStore txAccountDS) {
        LOG.info("Instantiating jpa account data store");
        return new JpaAccountDataStore(txAccountDS);
    }

}
