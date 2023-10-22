module datastore.spring.jpa {

    // required in compile time
    requires domain.entity;
    requires api.datastore;
    requires api.usecase;

    requires org.slf4j;
    requires spring.data.jpa;
    requires spring.beans;
    requires spring.tx;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    // opened in run time
    opens ws.slink.ca.framework.spring.ds;
    opens ws.slink.ca.framework.spring.ds.jpa;

}
