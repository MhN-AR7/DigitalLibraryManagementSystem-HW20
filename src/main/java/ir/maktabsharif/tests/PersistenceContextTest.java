package ir.maktabsharif.tests;

import ir.maktabsharif.enums.StockStatus;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.model.PublisherAddress;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class PersistenceContextTest {
    private static final PublisherAddress PUBLISHER_ADDRESS = new PublisherAddress("Tehran", "Azadi", "00000");
    private static final Book BOOK = new Book("PCT", "0000", 2026, BigDecimal.valueOf(100.45), StockStatus.IN_STOCK, PUBLISHER_ADDRESS);

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("postgres-pu");

    public static void test() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        System.out.println("--- Transient ---");
        System.out.println("Book is in Persistence Context: " + em.contains(BOOK));
        tx.begin();
        em.persist(BOOK);
        System.out.println("--- Managed ---");
        System.out.println("Book is in Persistence Context: " + em.contains(BOOK));
        BOOK.setPrice(BigDecimal.valueOf(99.32));
        System.out.println(BOOK);
        em.detach(BOOK);
        System.out.println("--- Detached ---");
        System.out.println("Book is in Persistence Context: " + em.contains(BOOK));
        BOOK.setIsbn("9999");
        System.out.println(BOOK);
        tx.commit();
        tx.begin();
        Book managedBook = em.merge(BOOK);
        System.out.println("--- Merged ---");
        System.out.println("Original Book is in Persistence Context: " + em.contains(BOOK));
        System.out.println("Managed Book is in Persistence Context: " + em.contains(managedBook));
        tx.commit();
    }
}
