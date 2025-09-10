CREATE TABLE IF NOT EXISTS requests_photos
    (
        request_id BIGINT NOT NULL,
        photo_id BIGINT NOT NULL,
        PRIMARY KEY (request_id, photo_id),
        FOREIGN KEY (request_id) REFERENCES requests(id) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (photo_id) REFERENCES photos(id) ON DELETE CASCADE ON UPDATE CASCADE
    );