ALTER TABLE users
    RENAME COLUMN profile_picture_link TO bio;

CREATE TABLE profile_images (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(255),
                                email VARCHAR(255),
                                type VARCHAR(255),
                                imagedata BYTEA
);

