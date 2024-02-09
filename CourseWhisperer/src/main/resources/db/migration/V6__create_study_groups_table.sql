CREATE TABLE IF NOT EXISTS "study_groups"
(
    id           SERIAL PRIMARY KEY,
    subject_name VARCHAR(255),
    meeting_time TIMESTAMP,
    group_name   VARCHAR(255) NOT NULL
);