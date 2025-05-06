package com.javarush.shosafoev.model.dao;

import com.javarush.shosafoev.model.entity.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO extends GenericDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
    
    public Inventory getByFilm(Short filmId, Byte storeId) {
        //noinspection deprecation
        return getCurrentSession()
                .createQuery("""
                                    FROM Inventory i
                                        WHERE i.store.id = :STORE_ID
                                        AND i.film = :FILM_ID
                                        AND i.id
                                            NOT IN(
                                                SELECT DISTINCT r.inventory
                                                    FROM Rental r
                                                    WHERE r.returnDate >= CURDATE()
                                                    OR r.returnDate IS NULL)
                                """
                        , Inventory.class)
                .setParameter("STORE_ID", storeId)
                .setShort("FILM_ID", filmId)
                .setMaxResults(1)
                .getSingleResult();
    }
}