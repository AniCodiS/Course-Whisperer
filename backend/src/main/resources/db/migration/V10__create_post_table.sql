CREATE TABLE post
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    username    VARCHAR(255),
    subject     VARCHAR(255),
    content     TEXT,
    up_vote     INTEGER DEFAULT 0,
    down_vote   INTEGER DEFAULT 0,
    post_type   VARCHAR(255)
);
