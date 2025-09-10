CREATE TABLE IF NOT EXISTS users
    (
        id BIGSERIAL,
        first_name VARCHAR(36),
        last_name VARCHAR(36),
        username VARCHAR(36),
        register_date TIMESTAMP DEFAULT now(),
        PRIMARY KEY (id),
        UNIQUE(id, username)
    );