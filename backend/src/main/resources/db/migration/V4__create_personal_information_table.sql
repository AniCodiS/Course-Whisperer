CREATE TABLE IF NOT EXISTS "personal_information"
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255)        NOT NULL,
    first_name VARCHAR(255)        NOT NULL,
    last_name  VARCHAR(255)        NOT NULL,
    year       INT,
    faculty    VARCHAR(255),
    email      VARCHAR(255) UNIQUE NOT NULL,
    FOREIGN KEY (username) REFERENCES "user" (username)
);