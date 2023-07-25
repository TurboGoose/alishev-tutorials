-------- ONE-TO-MANY relation example
-- Create tables
CREATE TABLE Director
(
    director_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    age         INT CHECK (age > 10)
);

CREATE TABLE Movie
(
    movie_id           INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    director_id        INT REFERENCES Director (director_id),
    name               VARCHAR(200) NOT NULL,
    year_of_production INT CHECK (year_of_production > 1900)
);

-- Populate tables
INSERT INTO Director(name, age)
VALUES ('Quentin Tarantino', 57),
       ('Martin Scorsese', 78),
       ('Guy Ritchie', 52),
       ('Woody Allen', 85),
       ('David Lynch', 74),
       ('Christofer Nolan', 50);

INSERT INTO Movie(director_id, name, year_of_production)
VALUES (1, 'Reservoir Dogs', 1992),
       (1, 'Pulp Fiction', 1994),
       (1, 'The hateful eight', 2015),
       (1, 'Once upon a time in Hollywood', 2019),
       (2, 'Taxi driver', 1976),
       (2, 'Good fellas', 1990),
       (2, 'The wolf of the wall street', 2013),
       (3, 'Lock, Stock and two smoking barrels', 1998),
       (3, 'Snatch', 2000),
       (4, 'Midnight in Paris', 2011),
       (6, 'Inception', 2010);


-- Test tables
-- insert into movie(director_id, name, year_of_production) values (7, 'some film', 2000); -- foreign key violation

select d.name, m.name
from director d
         left join movie m on m.director_id = d.director_id;



-------- ONE-TO-ONE relation example
-- Create tables
CREATE TABLE Citizen
(
    citizen_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    age        INT CHECK (age > 0)
);

CREATE TABLE Passport
(
    citizen_id      INT PRIMARY KEY REFERENCES Citizen (citizen_id),
    passport_number INT
);

-- Populate tables
INSERT INTO Citizen(name, age)
VALUES ('Anton', 13),
       ('Andrey', 19),
       ('Ilya', 21),
       ('Liza', 35);


INSERT INTO Passport(citizen_id, passport_number)
VALUES (2, 123456),
       (3, 567891),
       (4, 321456);

-- Test tables
select c.citizen_id, c.name, c.age, p.passport_number
from citizen c
         left join passport p on c.citizen_id = p.citizen_id
order by age;

-- insert into passport(citizen_id, passport_number) values (2, 979349); -- primary key violation
-- insert into passport(citizen_id, passport_number) values (100, 789647); -- foreign key violation


-------- MANY-TO-MANY relation example
-- Create tables
CREATE TABLE Actor
(
    actor_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name     VARCHAR(100) NOT NULL UNIQUE,
    age      INT CHECK ( age > 0 )
);

CREATE TABLE Actor_Movie
(
    actor_id INT REFERENCES Actor (actor_id),
    movie_id INT REFERENCES Movie (movie_id),
    PRIMARY KEY (actor_id, movie_id)
);

-- Populate tables
INSERT INTO Actor(name, age)
VALUES ('Harvey Keitel', 81),
       ('Robert De Niro', 77),
       ('Leonardo DiCaprio', 46),
       ('Jason Statham', 53),
       ('Joe Pesci', 77),
       ('Samuel L. Jackson', 72);


INSERT INTO Actor_Movie(actor_id, movie_id)
VALUES (1, 1),
       (1, 2),
       (2, 5),
       (2, 6),
       (3, 4),
       (3, 7),
       (3, 11),
       (4, 8),
       (4, 9),
       (5, 6),
       (6, 2),
       (6, 3);

-- Test tables
-- insert into Actor_Movie(actor_id, movie_id) values (2, 5); -- primary key violation

select a.name, m.name
from actor a
         join actor_movie am on a.actor_id = am.actor_id
         join movie m on am.movie_id = m.movie_id;