package ws.slink.ca.api.datastore;

import java.util.List;
import java.util.concurrent.Callable;

public interface DataStore<K, V> {

    List<V> list();

    V get(K key);
    V getForUpdate(K key);

    V save(V value);
    void remove(K key);

    <T> T transactional(Callable<T> function);
    void transactional(Runnable procedure);

}
