CREATE TABLE IF NOT EXISTS requests
    (
        id BIGSERIAL,
        complain_text TEXT NOT NULL,
        create_at TIMESTAMP DEFAULT now() NOT NULL,
        status VARCHAR(36) NOT NULL,
        PRIMARY KEY (id),
        CHECK(length(complain_text) <= 2000)
    );