package kz.ilotterytea.bin.utils;

import kz.ilotterytea.bin.entities.File;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ilotterytea
 * @since 1.0
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = generateSessionFactory();

    private static SessionFactory generateSessionFactory() {
        return new Configuration()
                .configure()
                .addAnnotatedClass(File.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}