CREATE TABLE passed_subject
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at   TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    modified_at  TIMESTAMP WITHOUT TIME ZONE,
    username     VARCHAR(255),
    subject      VARCHAR(255),
    grade        VARCHAR(255),
    grade_score  INTEGER
);
