CREATE TABLE IF NOT EXISTS messages (
                                        message_id SERIAL  PRIMARY KEY,
                                        sender_id VARCHAR(255) ,
                                        receiver_id VARCHAR(255) ,
                                        content TEXT NOT NULL,
                                        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--                                         FOREIGN KEY (sender_id) REFERENCES users(email),
--     FOREIGN KEY (receiver_id) REFERENCES users(email)
    );