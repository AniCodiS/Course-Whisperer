CREATE TABLE IF NOT EXISTS user_scores
(
    id SERIAL PRIMARY KEY,
    user_id INT,
    score INT
);
