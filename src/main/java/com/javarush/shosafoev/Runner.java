package com.javarush.shosafoev;

import com.javarush.shosafoev.model.dao.*;
import com.javarush.shosafoev.model.entity.*;
import com.javarush.shosafoev.mysql.MysqlDb;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        Customer newCustomer = createNewCustomer();
        returnInventoryToStore();
        wentToShop(newCustomer);
        newFilmInRental();
    }
    
    private static void returnInventoryToStore() {
        SessionFactory factory = MysqlDb.getFactory();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            RentalDAO rentalDAO = new RentalDAO(factory);
            Rental rental = rentalDAO.getNoReturnedInventory();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(rental);
            session.getTransaction().commit();
        }
    }
    
    private static void newFilmInRental() {
        SessionFactory factory = MysqlDb.getFactory();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Actor actor = new Actor();
            actor.setLastName("Javkov");
            actor.setFirstName("Crud");
            ActorDAO actorDAO = new ActorDAO(factory);
            actorDAO.save(actor);
            List<Actor> actors = actorDAO.getItems(1, 4);
            actors.add(actor);
            CategoryDAO categoryDAO = new CategoryDAO(factory);
            Category category = new Category();
            category.setName("New category");
            categoryDAO.save(category);
            List<Category> categories = categoryDAO.getItems(2, 4);
            categories.add(category);
            LanguageDAO languageDAO = new LanguageDAO(factory);
            Language language = languageDAO.getItems(2, 3).get(0);
            Set<Feature> specialFeatures = Set.of(Feature.COMMENTARIES, Feature.TRAILERS);
            FilmDAO filmDAO = new FilmDAO(factory);
            Film film = new Film();
            film.setTitle("Javarush");
            film.setDescription("Jxtym lknb");
            film.setReleaseYear(Year.now());
            film.setLanguage(language);
            film.setRentalDuration((byte) 2.4);
            film.setRentalRate(BigDecimal.valueOf(0));
            film.setLength((short) 59);
            film.setReplacementCost(BigDecimal.valueOf(23));
            film.setRating(Rating.PG);
            film.setSpecialFeatures(specialFeatures);
            film.setActors(actors);
            film.setCategories(categories);
            filmDAO.save(film);
            FilmTextDAO filmTextDAO = new FilmTextDAO(factory);
            FilmText filmText = new FilmText();
            filmText.setTitle("text test film ");
            filmText.setFilm(film);
            filmText.setDescription("Very long");
            filmTextDAO.save(filmText);
            session.getTransaction().commit();
        }
        
        
    }
    
    private static void wentToShop(Customer customer) {
        SessionFactory factory = MysqlDb.getFactory();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Store store = new StoreDAO(factory).getItems(0, 1).get(0);
            Film film = new FilmDAO(factory).getFreeFilmInStore(store.getId());
            Inventory inventory = new InventoryDAO(factory).getByFilm(film.getId(), store.getId());
            Staff staff = store.getStaff();
            Rental rental = new Rental();
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setRentalDate(LocalDateTime.now());
            rental.setStaff(staff);
            new RentalDAO(factory).save(rental);
            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(staff);
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setAmount(BigDecimal.valueOf(23.99));
            PaymentDAO paymentDAO = new PaymentDAO(factory);
            paymentDAO.save(payment);
            session.getTransaction().commit();
        }
        
        
    }
    
    private static Customer createNewCustomer() {
        SessionFactory factory = MysqlDb.getFactory();
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Address address = new Address();
            AddressDAO addressDAO = new AddressDAO(factory);
            City city = new CityDAO(factory).getByName("Ahmadnagar");
            address.setCity(city);
            address.setAddress("Testovayia 23");
            address.setPhone("12312 123 123");
            address.setPostalCode("2134");
            address.setDistrict("West");
            addressDAO.save(address);
            //Из за Short id не работает метод джинерика, так как там id Integer
            Store store = new StoreDAO(factory).getItems(0, 1).get(0);
            Customer customer = new Customer();
            CustomerDAO customerDAO = new CustomerDAO(factory);
            customer.setFirstName("Test");
            customer.setLastName("Testov");
            customer.setEmail("asdas@asdsda.bry");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setIsActive(true);
            customerDAO.save(customer);
            currentSession.getTransaction().commit();
            return customer;
        }
    }
    
}
    
