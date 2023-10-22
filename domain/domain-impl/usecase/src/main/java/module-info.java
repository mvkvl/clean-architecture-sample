module usecase {

    // required in compile time
    requires domain.entity;
    requires api.usecase;
    requires api.datastore;

    // exported in run time
    exports ws.slink.ca.domain.usecase.account.actor;

}
