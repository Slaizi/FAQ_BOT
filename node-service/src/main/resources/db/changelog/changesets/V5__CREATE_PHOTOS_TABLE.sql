CREATE TABLE IF NOT EXISTS photos
    (
        id BIGSERIAL,
        original_file_name VARCHAR(255),
        telegram_file_id VARCHAR(36),
        PRIMARY KEY (id)
    );