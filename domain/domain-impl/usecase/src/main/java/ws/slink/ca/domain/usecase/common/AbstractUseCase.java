package ws.slink.ca.domain.usecase.common;

import ws.slink.ca.api.datastore.DataStore;
import ws.slink.ca.domain.usecase.account.exception.EntityNotFoundException;

public class AbstractUseCase<K, V> {

    private final DataStore<K, V> dataStore;

    protected AbstractUseCase(final DataStore<K, V> dataStore) {
        this.dataStore = dataStore;
    }

    protected V getEntity(K id) {
        V entity = dataStore.get(id);
        if (entity == null) {
            throw new EntityNotFoundException(getEntityId(id));
        }
        return entity;
    }

    protected V getEntityForUpdate(K id) {
        V entity = dataStore.getForUpdate(id);
        if (entity == null) {
            throw new EntityNotFoundException(getEntityId(id));
        }
        return entity;
    }

    protected String getEntityId(Object id) {
        if (id == null) {
            return "entity";
        } else {
            return String.format("entity %s", id);
        }
    }

}
