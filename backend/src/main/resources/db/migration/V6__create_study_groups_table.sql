CREATE TABLE IF NOT EXISTS "study_groups"
(
    id                   SERIAL PRIMARY KEY,
    subject_name         VARCHAR(255),
    creator_username     VARCHAR(255) NOT NULL,
    meeting_time         TIMESTAMP,
    group_name           VARCHAR(255) NOT NULL,
    current_member_count INT,
    max_member_count     INT,
    created_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);