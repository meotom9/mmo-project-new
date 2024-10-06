package mmo.project.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final EntityManagerFactory emFactory;

    static {
        emFactory = Persistence.createEntityManagerFactory("mmo-project-persistence-unit");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emFactory;
    }

    public static void shutdown() {
        emFactory.close();
    }
}
