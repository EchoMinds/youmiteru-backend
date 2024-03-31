create table if not exists youmiteru_backend.user
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    profile_picture_url varchar,
    name                varchar(32) not null,
    email               varchar(64),
    role                varchar(32) not null,
    creation_time       timestamp
);
create table if not exists youmiteru_backend.title
(
    id              BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title_image_url varchar,
    name            varchar(128) not null,
    description     varchar(5012)
);
create table if not exists youmiteru_backend.genre
(
    id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(64) not null
);
create table if not exists youmiteru_backend.voice_actor
(
    id      BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name    varchar(32) not null,
    user_id BIGINT references youmiteru_backend.user (id)
);
create table if not exists youmiteru_backend.season
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    season_image_url    varchar,
    name                varchar(128)                                                     not null,
    reduced_description VARCHAR(255),
    anime_format        varchar(24)                                                      not null,
    description         text,
    release_date        DATE,
    title_id            BIGINT references youmiteru_backend.title (id) on delete cascade not null,
    title_state         varchar(64)                                                      not null,
    age_restriction     varchar(24),
    year_season         varchar(24),
    anime_banner_url    varchar,
    frame_with_videoplayer varchar
);

create table if not exists youmiteru_backend.comment
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    creation_date timestamp                                                         not null,
    message       varchar(512)                                                      not null,
    rating_value  int default 0,
    reply_to      BIGINT references youmiteru_backend.comment (id),
    writer_id     BIGINT references youmiteru_backend.user (id) on delete cascade   not null,
    season_id     BIGINT references youmiteru_backend.season (id) on delete cascade not null
);

create table if not exists youmiteru_backend.rating
(
    id        BIGINT primary key GENERATED ALWAYS AS IDENTITY,
    value     int check (value between 1 and 10)                                not null,
    user_id   BIGINT references youmiteru_backend.user (id) on delete cascade   not null,
    season_id BIGINT references youmiteru_backend.season (id) on delete cascade not null
);
create table if not exists youmiteru_backend.anime_genres
(
    genre_id BIGINT references youmiteru_backend.genre (id) on delete cascade not null,
    title_id BIGINT references youmiteru_backend.title (id) on delete cascade not null
);
create table if not exists youmiteru_backend.seasons_voice_actors
(
    season_id      BIGINT references youmiteru_backend.season (id) on delete cascade      not null,
    voice_actor_id BIGINT references youmiteru_backend.voice_actor (id) on delete cascade not null
);

create table if not exists youmiteru_backend.seasons_users
(
    season_id BIGINT references youmiteru_backend.season (id) on delete cascade not null,
    user_id   BIGINT references youmiteru_backend.user (id) on delete cascade   not null
)