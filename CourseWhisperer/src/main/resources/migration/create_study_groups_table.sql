CREATE TABLE IF NOT EXISTS "study_groups"
(
    id           SERIAL PRIMARY KEY,
    subject_id   INT,
    meeting_time TIMESTAMP,
    group_name   VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "study_group_members"
(
    id             SERIAL PRIMARY KEY,
    study_group_id INT,
    member_id      INT,
    FOREIGN KEY (study_group_id) REFERENCES "study_groups" (id),
    FOREIGN KEY (member_id) REFERENCES "user" (id)
);