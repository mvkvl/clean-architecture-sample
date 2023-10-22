module test.app.spring.jpa {

    requires domain.entity;
    requires api.usecase;
    requires api.presentator;
    requires api.datastore;
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

    requires datastore.java.inmem;

    opens ws.slink.ca.application;
    opens ws.slink.ca.application.config;
    opens ws.slink.ca.application.service to spring.beans;
    opens ws.slink.ca.application.controller;
    opens ws.slink.ca.application.exception;

}
