module usecase_api {

    // required in compile time
    requires domain_entity;

    // exported in compile time
    exports ws.slink.ca.api.actor;
    exports ws.slink.ca.api.exception;
    exports ws.slink.ca.api.exception.account;
    exports ws.slink.ca.api.usecase.account to usecase;

}
