package com.javarush.shosafoev.mysql;

import com.javarush.shosafoev.model.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

import static java.util.Objects.isNull;

public class MysqlDb {
    private static MysqlDb MYSQL_DB;
    private final SessionFactory sessionFactory;
    
    public MysqlDb() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
//        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/movie");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();
        MYSQL_DB = this;
    }
    
    public static SessionFactory getFactory() {
        if (isNull(MYSQL_DB)) {
            new MysqlDb();
        }
        return MYSQL_DB.sessionFactory;
    }
}