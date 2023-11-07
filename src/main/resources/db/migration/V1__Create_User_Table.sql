CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       profile_picture_link VARCHAR(255),
                       email VARCHAR(255) NOT NULL,
                       phone_number BIGINT,
                       password VARCHAR(255) NOT NULL,
                       fav_song TEXT[],
                       fav_artist TEXT[],
                       fav_genre TEXT[],
                       score DOUBLE PRECISION,
                       latitude DOUBLE PRECISION,
                       longitude DOUBLE PRECISION
);
