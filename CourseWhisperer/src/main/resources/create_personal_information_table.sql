CREATE TABLE IF NOT EXISTS "personal_information"
(
    user_id      INT,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    year         INT,
    faculty      VARCHAR(255),
    account_mail VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);