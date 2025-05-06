package com.javarush.shosafoev.model.dao;

import com.javarush.shosafoev.model.entity.Country;
import org.hibernate.SessionFactory;

@SuppressWarnings("unused")
public class CountryDAO extends GenericDAO<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}