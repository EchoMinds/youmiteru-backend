INSERT INTO youmiteru_backend.comment (creation_date, message, rating_value, reply_to, writer_id, season_id)
VALUES ('2024-02-23 20:12:49.000000', 'This is parent', 4, null, 1, 1);

INSERT INTO youmiteru_backend.comment (creation_date, message, rating_value, reply_to, writer_id, season_id)
VALUES ('2024-02-23 20:12:49.000000', 'This is child', 4, 1, 1, 1);

INSERT INTO youmiteru_backend.comment (creation_date, message, rating_value, reply_to, writer_id, season_id)
VALUES ('2024-02-23 20:12:49.000000', 'This is child', 4, 1, 1, 1);