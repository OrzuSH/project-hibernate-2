package com.javarush.shosafoev.model.dao;

import com.javarush.shosafoev.model.entity.Staff;
import org.hibernate.SessionFactory;

@SuppressWarnings("unused")
public class StaffDAO extends GenericDAO<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}