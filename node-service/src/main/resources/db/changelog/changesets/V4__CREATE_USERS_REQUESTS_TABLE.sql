CREATE TABLE IF NOT EXISTS users_requests
    (
       user_id BIGINT NOT NULL,
       request_id BIGINT NOT NULL,
       PRIMARY KEY (user_id, request_id),
       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
       FOREIGN KEY (request_id) REFERENCES requests(id) ON DELETE CASCADE ON UPDATE CASCADE
    );