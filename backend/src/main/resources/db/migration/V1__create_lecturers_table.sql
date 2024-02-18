CREATE TABLE IF NOT EXISTS "lecturers"
(
    id            SERIAL PRIMARY KEY,
    lecturer_name VARCHAR(255) NOT NULL,
    department    VARCHAR(255),
    email         VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);