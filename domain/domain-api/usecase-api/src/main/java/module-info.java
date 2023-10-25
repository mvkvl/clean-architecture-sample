module api.usecase {

    // required in compile time
    requires domain.entity;

    // exported in compile time
    exports ws.slink.ca.api.usecase;
    exports ws.slink.ca.api.exception;
    exports ws.slink.ca.api.exception.account;
    exports ws.slink.ca.api.usecase.account to usecase;

}
