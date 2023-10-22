module datastore_api {

    // required in compile time
    requires domain_entity;

    // exported in compile time
    exports ws.slink.ca.api.datastore;
    exports ws.slink.ca.api.datastore.exception;
    exports ws.slink.ca.api.datastore.common to usecase;

}
