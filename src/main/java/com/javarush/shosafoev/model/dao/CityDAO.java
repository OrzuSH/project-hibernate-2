package com.javarush.shosafoev.model.dao;

import com.javarush.shosafoev.model.entity.City;
import org.hibernate.SessionFactory;

public class CityDAO extends GenericDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
    
    public City getByName(String name) {
        return getCurrentSession()
                .createQuery(
                        "FROM City c WHERE c.name = :NAME"
                        , City.class
                ).setParameter("NAME", name)
                .uniqueResult();
    }
}