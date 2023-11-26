ALTER TABLE users
    RENAME COLUMN score TO max_distance;
ALTER TABLE users
    DROP COLUMN fav_genre;