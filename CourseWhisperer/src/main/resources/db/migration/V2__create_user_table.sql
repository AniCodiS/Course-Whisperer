CREATE TABLE IF NOT EXISTS "user"
(
    id       SERIAL PRIMARY KEY,
    mail     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);