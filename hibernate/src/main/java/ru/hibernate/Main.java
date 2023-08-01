package ru.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.hibernate.movies.model.Director;
import ru.hibernate.movies.model.Movie;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);

        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            getDirectorAndAllHisMovies(session);
//            getMovieAndItsDirector(session);
//            addMovieForDirector(session);
//            addNewDirectorWithMovie(session);
//            changeDirectorForMovie(session);
            deleteFilmFromDirector(session);

            session.getTransaction().commit();
        }
    }

    private static void getDirectorAndAllHisMovies(Session session) {
        Director director = session.get(Director.class, 1); // select
        director.getMovies().forEach(System.out::println); // select
    }

    private static void getMovieAndItsDirector(Session session) {
        Movie movie = session.get(Movie.class, 7); // select
        System.out.println(movie);
        System.out.println(movie.getDirector());
    }

    private static void addMovieForDirector(Session session) {
        Director director = session.get(Director.class, 3); // select
        Movie newMovie = new Movie();
        newMovie.setName("Brand new movie");
        newMovie.setYearOfProduction(2023);
        newMovie.setDirector(director);

        session.persist(newMovie); // insert (in the end of transaction)
        director.getMovies().add(newMovie);
    }

    private static void addNewDirectorWithMovie(Session session) {
        Director director = new Director();
        director.setName("Some name");
        director.setAge(45);

        Movie movie = new Movie();
        movie.setName("Some movie");
        movie.setYearOfProduction(2023);
        movie.setDirector(director);

        director.setMovies(new ArrayList<>(List.of(movie)));

        session.persist(director); // insert
        session.persist(movie); // insert

        System.out.println(director);
        System.out.println(movie);
    }

    private static void changeDirectorForMovie(Session session) {
        Movie movie = session.get(Movie.class, 14); // select
        Director oldDirector = movie.getDirector();
        Director newDirector = session.get(Director.class, 1); // select

        movie.setDirector(newDirector); // update
        oldDirector.getMovies().remove(movie); // select
        newDirector.getMovies().add(movie); // why no select?

        System.out.println(newDirector.getMovies()); // select
    }

    private static void deleteFilmFromDirector(Session session) {
        Movie movie = session.get(Movie.class, 14); // select
        Director director = movie.getDirector();

        session.remove(movie); // delete
        director.getMovies().remove(movie); // select
    }
}
