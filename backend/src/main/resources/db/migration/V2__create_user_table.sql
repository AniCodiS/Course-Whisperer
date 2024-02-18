CREATE TABLE IF NOT EXISTS "user"
(
    id          SERIAL PRIMARY KEY,
    email       VARCHAR(255)        NOT NULL,
    username    VARCHAR(255) UNIQUE NOT NULL,
    password    VARCHAR(255)        NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
