package ru.hibernate.movies;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            createAndSaveActorsAndMovies(session);
//            addMovieToActor(session);
            unlinkActorFromMovie(session);

            session.getTransaction().commit();
        }
    }

    private static void unlinkActorFromMovie(Session session) {
        Actor actor = session.get(Actor.class, 5); // select
        Movie movieToRemove = actor.getMovies().get(0); // select

        actor.getMovies().remove(movieToRemove);
        movieToRemove.getActors().remove(actor); // select
        // delete
    }

    private static void createAndSaveActorsAndMovies(Session session) {
        Movie movie = new Movie("Movie 1");
        Actor actor1 = new Actor("Actor 1");
        Actor actor2 = new Actor("Actor 2");

        movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
        actor1.setMovies(new ArrayList<>(List.of(movie)));
        actor2.setMovies(new ArrayList<>(List.of(movie)));

        session.persist(actor1);
    }

    private static void addMovieToActor(Session session) {
        Actor actor = session.get(Actor.class, 4); // select
        Movie newMovie = new Movie("Brand new movie");

        actor.getMovies().add(newMovie); // select
        newMovie.setActors(new ArrayList<>(List.of(actor)));

        session.persist(newMovie); // insert

        // delete + insert + insert
    }
}
