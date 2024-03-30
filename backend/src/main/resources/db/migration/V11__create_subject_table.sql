CREATE TABLE subject
(
    subject_code VARCHAR(255) PRIMARY KEY,
    subject_name VARCHAR(255),
    school_name  VARCHAR(255),
    credit_score INTEGER,
    lecturer     VARCHAR(255),
    semester     VARCHAR(255) NOT NULL
);
