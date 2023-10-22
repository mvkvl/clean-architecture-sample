module presentator {

    // required in compile time
    requires domain_entity;
    requires presentator_api;

    // exported in compile time
    exports ws.slink.ca.domain.presentator;

    // opened in run time
    opens ws.slink.ca.domain.presentator.account;

}
