CREATE TABLE IF NOT EXISTS "study_group_members"
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    study_group_id BIGINT,
    member_id      BIGINT,
    FOREIGN KEY (study_group_id) REFERENCES "study_groups" (id),
    FOREIGN KEY (member_id) REFERENCES "user" (id),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);