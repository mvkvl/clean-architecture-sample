module datastore_spring_jpa {

    // required in compile time
    requires domain_entity;
    requires usecase_api;
    requires usecase;
    requires datastore_api;
    requires org.slf4j;
    requires spring.data.jpa;
    requires spring.beans;
    requires spring.tx;
    requires spring.context;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    // opened in run time
    opens ws.slink.ca.framework.spring.datastore;
    opens ws.slink.ca.framework.spring.datastore.jpa;

}
