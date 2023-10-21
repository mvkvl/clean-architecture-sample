package ws.slink.ca.api.presentator;

public interface Presentator<A, B> {

    B present(A value);

}
