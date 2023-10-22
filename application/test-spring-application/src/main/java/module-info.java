module test_app_string_jpa {

    requires domain_entity;
    requires usecase_api;
    requires presentator_api;
    requires datastore_api;
    requires presentator;
    requires usecase;

    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires spring.context;
    requires spring.beans;
    requires spring.boot;
    requires spring.web;

    requires org.slf4j;
    requires java.sql;
    requires lombok;

    opens ws.slink.ca.application;
    opens ws.slink.ca.application.config;
    opens ws.slink.ca.application.service to spring.beans;
    opens ws.slink.ca.application.controller;
    opens ws.slink.ca.application.exception;

}
