CREATE TABLE IF NOT EXISTS "study_group_members"
(
    id             SERIAL PRIMARY KEY,
    study_group_id INT,
    member_id      INT,
    FOREIGN KEY (study_group_id) REFERENCES "study_groups" (id),
    FOREIGN KEY (member_id) REFERENCES "user" (id),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);