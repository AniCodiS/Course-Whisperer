CREATE TABLE IF NOT EXISTS "prerequisites"
(
    id                      SERIAL PRIMARY KEY,
    subject_id              INT,
    prerequisite_subject_id INT,
    FOREIGN KEY (subject_id) REFERENCES subjects (id),
    FOREIGN KEY (prerequisite_subject_id) REFERENCES subjects (id)
);