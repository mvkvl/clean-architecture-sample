module usecase {

    // required in compile time
    requires domain_entity;
    requires usecase_api;
    requires datastore_api;

    // exported in run time
    exports ws.slink.ca.domain.usecase.account.actor;

}
