```sql
INSERT INTO youmiteru_backend.user (profile_picture_url, name, email, role)
VALUES ('https://example.com/john.png', 'John Doe', 'john@doe.com', 'USER');

INSERT INTO youmiteru_backend.user (profile_picture_url, name, email, role)
VALUES ('https://example.com/jane.png', 'Jane Smith', 'jane@smith.com', 'ACTOR');

INSERT INTO youmiteru_backend.user (profile_picture_url, name, email, role)  
VALUES (null, 'Bob Admin', 'bob@company.com', 'ADMIN');




INSERT INTO youmiteru_backend.title (title_image_url, name, description)
VALUES ('https://animesite.com/demon-slayer.jpg', 'Demon Slayer', 'Tanjiro sets out to become a demon slayer to avenge his family.');

INSERT INTO youmiteru_backend.title (title_image_url, name) 
VALUES ('https://animesite.com/attack-on-titan.jpg', 'Attack on Titan');

INSERT INTO youmiteru_backend.title (title_image_url, name, description)
VALUES ('https://animesite.com/kaguya-sama.jpg', 'Kaguya-sama: Love is War', 'Comedy romance anime about two geniuses trying to get the other to confess their love first.');




INSERT INTO youmiteru_backend.genre (name)
VALUES ('Action');

INSERT INTO youmiteru_backend.genre (name)
VALUES ('Adventure');

INSERT INTO youmiteru_backend.genre (name) 
VALUES ('Comedy');


INSERT INTO youmiteru_backend.season (
  season_image_url, 
  name, 
  anime_format,
  description,
  release_date,
  title_id,
  title_state,
  age_restriction, 
  year_season,
  anime_pictures
)
VALUES (
  'https://animesite.com/demon-slayer-s1.jpg',
  'Demon Slayer Season 1',
  'TV',
  'Tanjiro becomes a demon slayer to turn his sister Nezuko, who has become a demon, back into a human, and to avenge the death of his family by hunting down the demon who killed them.',
  '2019-04-06', 
  1, 
  'RELEASED',
  'R',
  'Spring 2019',
  '[{"url": "https://image1.jpg"}, {"url": "https://image2.jpg"}]'
);

INSERT INTO youmiteru_backend.season (
  name, 
  anime_format, 
  title_id,
  title_state,
  year_season  
)
VALUES (
  'Attack on Titan Final Season',
  'TV',
  2,
  'ANNOUNCEMENT',
  'Winter 2023' 
);

INSERT INTO youmiteru_backend.season (
  season_image_url,
  name,
  anime_format, 
  release_date,
  title_id,
  title_state,
  year_season 
)  
VALUES (
  'https://animesite.com/kaguya-sama-s3.jpg', 
  'Kaguya-sama Season 3',
  'TV',
  '2024-01-10', 
  3,
  'FINISHED',
  'Winter 2024'
);



INSERT INTO youmiteru_backend.voice_actor (name, user_id)
VALUES ('Johnny Yong Bosch', 1);

INSERT INTO youmiteru_backend.voice_actor (name)
VALUES ('Yuki Kaji');

INSERT INTO youmiteru_backend.voice_actor (name, user_id) 
VALUES ('Saori Hayami', 2);

INSERT INTO youmiteru_backend.user (profile_picture_url, name, email, role)
VALUES ('https://animesite.com/bosch.png', 'Johnny Yong Bosch', 'bosch@email.com', 'ACTOR');

INSERT INTO youmiteru_backend.user (name, email, role)
VALUES ('Saori Hayami', 'hayami@email.com', 'ACTOR');


INSERT INTO youmiteru_backend.comment (
  creation_date, 
  message,
  rating_value,
  writer_id,
  season_id
)
VALUES (
  '2023-01-01 08:00:00',
  'Great first episode! Looking forward to the rest of the season.',
  5,
  1,
  1
);

INSERT INTO youmiteru_backend.comment (
  creation_date,
  message,
  writer_id,
  season_id  
)
VALUES (
  '2023-01-03 12:30:00',
  'Does anyone know when the next episode will be released?',
  3, 
  1
);

INSERT INTO youmiteru_backend.comment (
  creation_date,
  message,
  reply_to,
  writer_id,
  season_id
)
VALUES (
  '2023-01-04 16:00:00', 
  'New episodes come out every Saturday at 3PM EST',
  2,
  2,
  1  
);


INSERT INTO youmiteru_backend.video (
  episode, 
  player, 
  link,
  season_id
)
VALUES (
  1,
  'Youtube',
  'https://www.youtube.com/watch?v=XMXgHfHxKVM',
  1  
);

INSERT INTO youmiteru_backend.video (
  episode,
  player,
  link,
  season_id
)
VALUES (
  2, 
  'Crunchyroll',
  'https://www.crunchyroll.com/demon-slayer-kimetsu-no-yaiba/episode-2-trainer-sakonji-urokodaki-783658',
  1
);



INSERT INTO youmiteru_backend.rating ( value, user_id, season_id ) VALUES (10, 1, 1); INSERT INTO youmiteru_backend.rating ( value, user_id, season_id ) VALUES (8, 2, 1);


INSERT INTO youmiteru_backend.anime_genres (
  genre_id,
  title_id
)
VALUES
  (1, 1), 
  (2, 1);

INSERT INTO youmiteru_backend.seasons_voice_actors (
  season_id,
  voice_actor_id  
) 
VALUES
  (1, 1);
```