CREATE TABLE IF NOT EXISTS user_scores
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255),
    score    INT
);
