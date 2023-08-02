package ru.hibernate.schools;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);

        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            getSchoolByPrincipal(session);
//            getPrincipalBySchool(session);
//            createPrincipalAndSchool(session);
//            changePrincipal(session);
            tryToAddSecondSchoolToPrincipal(session);



            session.getTransaction().commit();
        }
    }

    private static void getSchoolByPrincipal(Session session) {
        Principal principal = session.get(Principal.class, 1);
        System.out.println(principal);
        System.out.println(principal.getSchool());
    }

    private static void getPrincipalBySchool(Session session) {
        School school = session.get(School.class, 1);
        System.out.println(school);
    }

    private static void createPrincipalAndSchool(Session session) {
        School school = new School();
        school.setNumber(69);

        Principal principal = new Principal();
        principal.setName("Duke");
        principal.setSchool(school);

        school.setPrincipal(principal);

        session.persist(principal);
    }

    private static void changePrincipal(Session session){
        School school = session.get(School.class, 1);
        Principal oldPrincipal = school.getPrincipal();

        Principal newPrincipal = new Principal();
        newPrincipal.setName("Colin Smith");
        session.persist(newPrincipal);

        school.setPrincipal(newPrincipal);
        newPrincipal.setSchool(school);

        oldPrincipal.setSchool(null);

        System.out.println(newPrincipal.getSchool());
        System.out.println(oldPrincipal.getSchool());
    }

    private static void tryToAddSecondSchoolToPrincipal(Session session) {
        Principal principal = session.get(Principal.class, 1);

        School newSchool = new School();
        newSchool.setNumber(555);
        session.persist(newSchool);

        newSchool.setPrincipal(principal);
    }
}
