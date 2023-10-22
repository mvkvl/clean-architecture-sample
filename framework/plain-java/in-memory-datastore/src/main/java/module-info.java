module datastore.java.inmem {

    // required in compile time
    requires domain.entity;
    requires api.datastore;
    requires api.usecase;

    // exported in compile time
    exports ws.slink.ca.framework.java.ds;

}
