CREATE TABLE IF NOT EXISTS "subjects"
(
    id           SERIAL PRIMARY KEY,
    subject_name VARCHAR(255) NOT NULL,
    credit_score INT,
    taught_by    INT,
    semester     INT,
    FOREIGN KEY (taught_by) REFERENCES lecturers (id)
);