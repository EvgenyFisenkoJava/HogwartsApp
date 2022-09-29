CREATE TABLE users
(
    id              SERIAL,
    name            CHAR(255)                 NOT NULL PRIMARY KEY,
    age             SMALLINT CHECK (age > 17) NOT NULL,
    driving_license BOOLEAN,
    car_id          INTEGER REFERENCES cars (id)
);

CREATE TABLE cars
(
    id    SERIAL PRIMARY KEY,
    brand varchar(100) NOT NULL,
    model varchar(100) NOT NULL,
    cost  INTEGER      NOT NULL
);