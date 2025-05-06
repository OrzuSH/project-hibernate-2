package com.javarush.shosafoev.model.dao;

import com.javarush.shosafoev.model.entity.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends GenericDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFreeFilmInStore(int storeId) {
        Byte byteStoreId = (byte) storeId;
        String query = """
                FROM Film f
                WHERE f.id
                    IN (
                        SELECT i.film
                        FROM Inventory i
                        WHERE i.store.id = :STORE_ID AND i.id
                            NOT IN(
                                SELECT DISTINCT r.inventory
                                FROM Rental r
                                WHERE r.returnDate >= CURDATE()
                                OR r.returnDate IS NULL))""";
        return getCurrentSession().createQuery(
                        query
                        , Film.class
                )
                .setParameter("STORE_ID", byteStoreId)
                .setMaxResults(1)
                .getSingleResult();
    }
}