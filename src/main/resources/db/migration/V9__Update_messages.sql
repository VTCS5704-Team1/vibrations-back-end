ALTER TABLE messages
    RENAME COLUMN sender_id TO sender_email;

ALTER TABLE messages
    RENAME COLUMN receiver_id TO receiver_email;