CREATE TABLE Person (
    user_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT
);

CREATE TABLE "Order" (
    order_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id INT REFERENCES Person(user_id) ON DELETE SET NULL,
    item_name VARCHAR(100)
);

INSERT INTO Person(name, age) VALUES ('Anton', 25);
INSERT INTO "Order"(user_id, item_name) VALUES (1, 'Bicycle');

SELECT p.name, o.item_name FROM Person p right join "Order" o on p.user_id = o.user_id;
SELECT * FROM Person;
SELECT * FROM "Order";

DELETE FROM Person where user_id = 1;

DROP TABLE "Order";
DROP TABLE Person;