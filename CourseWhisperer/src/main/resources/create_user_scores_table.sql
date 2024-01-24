CREATE TABLE IF NOT EXISTS "user_scores"
(
    user_id INT,
    score   INT,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);
