package com.app.rest.DB;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

// EJB (Enterprise Java Bean) is used to develop scalable, robust and secured enterprise applications in java.
// source: https://www.javatpoint.com/ejb-tutorial

// Flyway is a Java library to version your database schema and evolve it over time. 


@Startup // make sure the schema is updated only once
@Singleton // single session bean
@TransactionManagement(TransactionManagementType.BEAN) //This will delegate the transaction handling to the bean and not the container
public class FlywayUpdater {

    @Resource(lookup = "jdbc/postgresql")
    private DataSource dataSource;
    

    @PostConstruct
    public void initFlyway() {
        System.out.println("Starting to migrate the database schema with Flyway");
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
        System.out.println("Successfully applied latest schema changes");
    }
}
