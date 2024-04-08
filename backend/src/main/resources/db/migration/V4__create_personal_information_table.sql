CREATE TABLE IF NOT EXISTS "personal_information"
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username   VARCHAR(255)        NOT NULL,
    first_name VARCHAR(255)        NOT NULL,
    last_name  VARCHAR(255)        NOT NULL,
    year       INT,
    faculty    VARCHAR(255),
    email      VARCHAR(255) UNIQUE NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (username) REFERENCES "user" (username)
);