package ru.hibernate.passports;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Citizen.class)
                .addAnnotatedClass(Passport.class);

        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            createCitizenAndPassport(session);
//            getPassportByCitizen(session);
            getCitizenByPassport(session);
//            changePassportNumber(session);
//            deleteCitizen(session);

            session.getTransaction().commit();
        }
    }


    private static void deleteCitizen(Session session) {
        Citizen citizen = session.get(Citizen.class, 1);
        session.remove(citizen);
    }

    private static void changePassportNumber(Session session) {
        Passport passport = session.get(Passport.class, 1);
        passport.setNumber(777777);
    }

    private static void getPassportByCitizen(Session session) {
        Citizen citizen = session.get(Citizen.class, 1);
        System.out.println(citizen.getPassport());
    }

    private static void getCitizenByPassport(Session session) {
        Passport passport = session.get(Passport.class, 2);
        System.out.println(passport.getCitizen());
    }

    private static void createCitizenAndPassport(Session session) {
        Passport passport = new Passport(12345);
        Citizen citizen = new Citizen("Person", 54);
        citizen.setPassport(passport);
        passport.setCitizen(citizen);

        session.persist(citizen);
    }
}
