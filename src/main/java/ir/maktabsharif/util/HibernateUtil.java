package ir.maktabsharif.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Function;

public class HibernateUtil {
    private static final String PERSISTENCE_UNIT = "postgres-pu";

    private static EntityManagerFactory emf;

    private static EntityManagerFactory getEmf() {
        if (emf == null) emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        return emf;
    }

    public static void closeEmf() {
        if (emf != null && emf.isOpen()) emf.close();
    }

    private static EntityManager getEm() {
        return getEmf().createEntityManager();
    }

    public static <T> T inTxResult(Function<EntityManager, T> operation) {
        EntityManager em = getEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T result = operation.apply(em);
            tx.commit();
            return result;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
        finally {
            em.close();
        }
    }
}
