package ws.slink.ca.framework.spring.datastore.jpa;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ws.slink.ca.framework.spring.datastore.entity.AccountEntity;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

    @Lock(LockModeType.WRITE)
    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "250"))
    Optional<AccountEntity> findFirstById(Long id);

}



//    @QueryHints(@QueryHint(name = AvailableSettings.JPA_LOCK_TIMEOUT, value = "" + LockOptions.SKIP_LOCKED))
//    @NamedQuery(
//        name="lockPersonQuery",
//        query="SELECT p FROM Person p WHERE p.name LIKE :name",
//        lockMode=PESSIMISTIC_READ)
