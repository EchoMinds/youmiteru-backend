/*1 Тайтл*/
/*10 Анонсов */
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/55690/49d51b1c556632d0214a49831575b5e0.jpeg',
        'Boku no Kokoro no Yabai Yatsu Season 2',
        'Повседневная жизнь маленького социопата наполнена комичными ситуациями, которые можно увидеть, если смотреть аниме “Boku no Kokoro no Yabai Yatsu”. Очередная встреча Кётаро с очаровательной девчонкой произошла в книжном магазине, что выбило парня из колеи. Юноша не догадывается, что за милым образом скрывается совершенно другой человек.');
/*Сезон1*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                   description, release_date, title_id, title_state,
                   age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/52578/6ca77a2fc65011e3d7053304b8b001e3.jpeg',
        'Boku no Kokoro no Yabai Yatsu Season 1', 'TV_SHOW',
        'Повседневная жизнь маленького социопата наполнена комичными ситуациями, которые можно увидеть, если смотреть аниме “Boku no Kokoro no Yabai Yatsu”. Очередная встреча Кётаро с очаровательной девчонкой произошла в книжном магазине, что выбило парня из колеи. Юноша не догадывается, что за милым образом скрывается совершенно другой человек.',
        TO_DATE ('2023/04/2', 'yyyy/mm/dd'), 1, 'ANNOUNCEMENT', '16', 'SPRING');
/*Сезон2*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                   description, release_date, title_id, title_state,
                   age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/55690/49d51b1c556632d0214a49831575b5e0.jpeg',
        'Boku no Kokoro no Yabai Yatsu Season 2', 'TV_SHOW',
        'Повседневная жизнь маленького социопата наполнена комичными ситуациями, которые можно увидеть, если смотреть аниме “Boku no Kokoro no Yabai Yatsu”. Очередная встреча Кётаро с очаровательной девчонкой произошла в книжном магазине, что выбило парня из колеи. Юноша не догадывается, что за милым образом скрывается совершенно другой человек.',
        TO_DATE ('2024/01/7', 'yyyy/mm/dd'), 1, 'ANNOUNCEMENT', '12', 'WINTER');

/*2 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/35860/4650317350e47fa4f658d3c90ef656e1.jpeg',
        'Karakai Jouzu no Takagi-san',
        'В классе Нисикаты учится девочка по имени Такаги. Весёлая, симпатичная — глаз не оторвать. И — чудо! — сидит за соседней партой. Любой мальчишка на месте Нисикаты надул бы в штаны от счастья, однако для последнего Такаги — сущее наказание. Ведь именно его, бедного, бедного Нисикату она выбрала в качестве объекта для своих глупых розыгрышей.');

/*Сезон1*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                   description, release_date, title_id, title_state,
                   age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/35860/4650317350e47fa4f658d3c90ef656e1.jpeg',
        'Karakai Jouzu no Takagi-san', 'TV_SHOW',
        'В классе Нисикаты учится девочка по имени Такаги. Весёлая, симпатичная — глаз не оторвать. И — чудо! — сидит за соседней партой. Любой мальчишка на месте Нисикаты надул бы в штаны от счастья, однако для последнего Такаги — сущее наказание. Ведь именно его, бедного, бедного Нисикату она выбрала в качестве объекта для своих глупых розыгрышей.',
        TO_DATE ('2018/01/8', 'yyyy/mm/dd'), 2, 'ANNOUNCEMENT', '12', 'WINTER');
/*Сезон2*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                   description, release_date, title_id, title_state,
                   age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/38993/534699774d8b121395967a91c573a66e.jpeg',
        'Karakai Jouzu no Takagi-san 2', 'TV_SHOW',
        'В классе Нисикаты учится девочка по имени Такаги. Весёлая, симпатичная — глаз не оторвать. И — чудо! — сидит за соседней партой. Любой мальчишка на месте Нисикаты надул бы в штаны от счастья, однако для последнего Такаги — сущее наказание. Ведь именно его, бедного, бедного Нисикату она выбрала в качестве объекта для своих глупых розыгрышей.',
        TO_DATE ('2019/06/7', 'yyyy/mm/dd') , 2, 'ANNOUNCEMENT', '16', 'SUMMER');

/*3 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/52211/3656ad1fb3786e5c5eba5806335c8599.jpeg',
        'Mashle', 'Взмахни волшебной палочкой, произнеси заклинание — так делаются все дела в мире магии, от работы по дому до сражений за судьбу мира. Кажется, что всё должно быть легко и занимательно в этом мире, где каждый сам себе волшебник, способный наколдовать счастья и денег.');

/*Сезон1*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/52211/3656ad1fb3786e5c5eba5806335c8599.jpeg',
        'Mashle', 'TV_SHOW', 'Взмахни волшебной палочкой, произнеси заклинание — так делаются все дела в мире магии, от работы по дому до сражений за судьбу мира. Кажется, что всё должно быть легко и занимательно в этом мире, где каждый сам себе волшебник, способный наколдовать счастья и денег.',
        TO_DATE ('2023/04/8', 'yyyy/mm/dd'), 3, 'ANNOUNCEMENT', '16', 'SPRING');

/*Сезон2*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/55813/378c027d13b5c9e8f01f370cb23da5e4.jpeg',
        'Mashle: Shinkakusha Kouho Senbatsu Shiken-hen', 'TV_SHOW',
        'Взмахни волшебной палочкой, произнеси заклинание — так делаются все дела в мире магии, от работы по дому до сражений за судьбу мира. Кажется, что всё должно быть легко и занимательно в этом мире, где каждый сам себе волшебник, способный наколдовать счастья и денег.',
        TO_DATE ('2024/01/6', 'yyyy/mm/dd'), 3, 'ANNOUNCEMENT', '16', 'WINTER');

/*4 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/54492/37693ebdb70f8b6edfb27b0361f04b1f.jpeg',
        'Kusuriya no Hitorigoto',
        'Уже полгода прошло с того момента, как 17-летнюю Маомао похитили и заставили трудиться в императорском дворце обычной служанкой. Работа тяжёлая, но девушка решила не сдаваться, не унывать и честно вкалывать, пока её не отпустят на покой. Планы изменились, когда до Маомао дошли вести о том, что детей императора одолел серьёзный недуг. ');

/*Сезон1*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/54492/37693ebdb70f8b6edfb27b0361f04b1f.jpeg',
        'Kusuriya no Hitorigoto', 'TV_SHOW',
        'Уже полгода прошло с того момента, как 17-летнюю Маомао похитили и заставили трудиться в императорском дворце обычной служанкой. Работа тяжёлая, но девушка решила не сдаваться, не унывать и честно вкалывать, пока её не отпустят на покой. Планы изменились, когда до Маомао дошли вести о том, что детей императора одолел серьёзный недуг.',
        TO_DATE ('2023/04/22', 'yyyy/mm/dd'), 4, 'ANNOUNCEMENT', '18', 'SPRING');

/*5 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/31964/d94d5bd52edd33ec458fd338a2f0517c.jpeg',
        'Boku no Hero Academia',
        'Четырнадцатилетний Идзуку Мидория рано осознал, что люди не рождаются равными. А пришло это понимание, когда его начали дразнить одноклассники, одарённые особой силой. Несмотря на то, что большинство людей в этом мире рождаются с необычными способностями, Идзуку оказался среди тех немногих, кто напрочь их лишён.');

/*Сезон1*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/31964/d94d5bd52edd33ec458fd338a2f0517c.jpeg',
        'Boku no Hero Academia', 'TV_SHOW',
        'Четырнадцатилетний Идзуку Мидория рано осознал, что люди не рождаются равными. А пришло это понимание, когда его начали дразнить одноклассники, одарённые особой силой. Несмотря на то, что большинство людей в этом мире рождаются с необычными способностями, Идзуку оказался среди тех немногих, кто напрочь их лишён.',
        TO_DATE ('2016/12/2', 'yyyy/mm/dd'), 5, 'ANNOUNCEMENT', '12', 'WINTER');

/*Сезон2*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/33486/de1e7a755b37a416347ba4e0bb9de7d7.jpeg',
        'Boku no Hero Academia 2nd Season', 'TV_SHOW',
        'Четырнадцатилетний Идзуку Мидория рано осознал, что люди не рождаются равными. А пришло это понимание, когда его начали дразнить одноклассники, одарённые особой силой. Несмотря на то, что большинство людей в этом мире рождаются с необычными способностями, Идзуку оказался среди тех немногих, кто напрочь их лишён.',
        TO_DATE ('2017/04/7', 'yyyy/mm/dd'), 5, 'ANNOUNCEMENT', '12', 'SPRING');

/*Сезон3*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/36456/93bcf75afeed32394232db7803685f38.jpeg',
        'Boku no Hero Academia 3rd Season', 'TV_SHOW',
        'Четырнадцатилетний Идзуку Мидория рано осознал, что люди не рождаются равными. А пришло это понимание, когда его начали дразнить одноклассники, одарённые особой силой. Несмотря на то, что большинство людей в этом мире рождаются с необычными способностями, Идзуку оказался среди тех немногих, кто напрочь их лишён.',
        TO_DATE ('2018/04/7', 'yyyy/mm/dd'), 5, 'ANNOUNCEMENT', '12', 'SPRING');

/*10 Релизов*/
/*Сезон4*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/38408/c688f6f111eddb56760e3580e4ed3abe.jpeg',
        'Boku no Hero Academia 4th Season', 'TV_SHOW',
        'Четырнадцатилетний Идзуку Мидория рано осознал, что люди не рождаются равными. А пришло это понимание, когда его начали дразнить одноклассники, одарённые особой силой. Несмотря на то, что большинство людей в этом мире рождаются с необычными способностями, Идзуку оказался среди тех немногих, кто напрочь их лишён.',
        TO_DATE ('2019/04/7', 'yyyy/mm/dd'), 5, 'RELEASED', '12', 'SPRING');

/*Сезон5*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/41587/3c927845258dcf5bbb81b1b03795f1d4.jpeg',
        'Boku no Hero Academia 5th Season', 'TV_SHOW',
        'Четырнадцатилетний Идзуку Мидория рано осознал, что люди не рождаются равными. А пришло это понимание, когда его начали дразнить одноклассники, одарённые особой силой. Несмотря на то, что большинство людей в этом мире рождаются с необычными способностями, Идзуку оказался среди тех немногих, кто напрочь их лишён.',
        TO_DATE ('2021/03/7', 'yyyy/mm/dd'), 5, 'RELEASED', '12', 'SPRING');

/*Сезон6*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/49918/6aeb68676ce8f628ec1e923284de5f27.jpeg',
        'Boku no Hero Academia 6th Season', 'TV_SHOW',
        'Четырнадцатилетний Идзуку Мидория рано осознал, что люди не рождаются равными. А пришло это понимание, когда его начали дразнить одноклассники, одарённые особой силой. Несмотря на то, что большинство людей в этом мире рождаются с необычными способностями, Идзуку оказался среди тех немногих, кто напрочь их лишён.',
        TO_DATE ('2022/03/7', 'yyyy/mm/dd'), 5, 'RELEASED', '12', 'SPRING');

/*Сезон7*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/54789/224f14f20b5aa2074d30aaa7cce727fa.jpeg',
        'Boku no Hero Academia 7th Season', 'TV_SHOW',
        'Четырнадцатилетний Идзуку Мидория рано осознал, что люди не рождаются равными. А пришло это понимание, когда его начали дразнить одноклассники, одарённые особой силой. Несмотря на то, что большинство людей в этом мире рождаются с необычными способностями, Идзуку оказался среди тех немногих, кто напрочь их лишён.',
        TO_DATE ('2024/05/4', 'yyyy/mm/dd'), 5, 'RELEASED', '12', 'SUMMER');

/*6 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/52741/b0ef01c548d1b54bee1439a7e84fb364.jpeg',
        'Undead Unluck',
        'Человек — существо социальное, ему требуется взаимодействие с окружающими не только посредством диалога, но и тактильного контакта. Увы, но Фуко Идзумо лишена этого удовольствия из-за своей «особенности» приносить неудачу через прикосновение.');

/*Сезон1*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/52741/b0ef01c548d1b54bee1439a7e84fb364.jpeg',
        'Undead Unluck', 'TV_SHOW',
        'Человек — существо социальное, ему требуется взаимодействие с окружающими не только посредством диалога, но и тактильного контакта. Увы, но Фуко Идзумо лишена этого удовольствия из-за своей «особенности» приносить неудачу через прикосновение.',
        TO_DATE ('2023/10/7', 'yyyy/mm/dd'), 6, 'RELEASED', '18', 'AUTUMN');

/*7 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/918/5b04621235daaa6f1ecae243f01019fe.jpeg',
        'Gintama',
        'Жить в феодальной Японии непросто... особенно если вас завоевали инопланетяне. Да, конечно, новая система здравоохранения хороша, но запрет на ношение меча ставит истинных самураев в безвыходное положение.');

/*Сезон1*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/918/5b04621235daaa6f1ecae243f01019fe.jpeg',
        'Gintama', 'TV_SHOW',
        'Жить в феодальной Японии непросто... особенно если вас завоевали инопланетяне. Да, конечно, новая система здравоохранения хороша, но запрет на ношение меча ставит истинных самураев в безвыходное положение.',
        TO_DATE ('2006/01/7', 'yyyy/mm/dd'), 7, 'RELEASED', '16', 'WINTER');

/*Сезон2*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/918/5b04621235daaa6f1ecae243f01019fe.jpeg',
        'Gintama 2', 'TV_SHOW',
        'Жить в феодальной Японии непросто... особенно если вас завоевали инопланетяне. Да, конечно, новая система здравоохранения хороша, но запрет на ношение меча ставит истинных самураев в безвыходное положение.',
        TO_DATE ('2011/01/8', 'yyyy/mm/dd'), 7, 'RELEASED', '16', 'WINTER');

/*Сезон3*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/918/5b04621235daaa6f1ecae243f01019fe.jpeg',
        'Gintama: Enchousen', 'TV_SHOW',
        'Жить в феодальной Японии непросто... особенно если вас завоевали инопланетяне. Да, конечно, новая система здравоохранения хороша, но запрет на ношение меча ставит истинных самураев в безвыходное положение.',
        TO_DATE ('2012/01/9', 'yyyy/mm/dd'), 7, 'RELEASED', '16', 'WINTER');

/*Сезон4*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/918/5b04621235daaa6f1ecae243f01019fe.jpeg',
        'Gintama 4', 'TV_SHOW',
        'Жить в феодальной Японии непросто... особенно если вас завоевали инопланетяне. Да, конечно, новая система здравоохранения хороша, но запрет на ношение меча ставит истинных самураев в безвыходное положение.',
        TO_DATE ('2015/01/10', 'yyyy/mm/dd'), 7, 'RELEASED', '16', 'WINTER');
/*Сезон5*/
INSERT INTO youmiteru_backend.season(season_image_url, name, anime_format,
                                     description, release_date, title_id, title_state,
                                     age_restriction, year_season)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/918/5b04621235daaa6f1ecae243f01019fe.jpeg',
        'Gintama 5', 'TV_SHOW',
        'Жить в феодальной Японии непросто... особенно если вас завоевали инопланетяне. Да, конечно, новая система здравоохранения хороша, но запрет на ношение меча ставит истинных самураев в безвыходное положение.',
        TO_DATE ('2017/01/11', 'yyyy/mm/dd'), 7, 'RELEASED', '16', 'WINTER');

/*8 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/52299/main_2x-464c78cefbef27e2e592598a4b756761.webp', 'Ore dake Level Up na Ken', 'Десять лет назад по всему миру стали открываться некие «врата», ведущие в подземелья с монстрами, противостоять которым оказалось не под силу даже военным.');
/*9 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/52701/main_2x-5869fd1cb8bb79757b4e69cc52831b0f.webp', 'Dungeon Meshi', 'Голод не тётка — пирожка не поднесёт» — эту истину не понаслышке знает любой авантюрист, бывающий в подземелье и вынужденный таскать с собой огромный запас провианта.');
/*10 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/49889/main_2x-ecc9dad17cfe75f396e64775a9201e67.webp', 'Tsuki ga Michibiku Isekai Douchuu 2nd Season', 'Когда Макото Мисуми внезапно отправили в другой мир, да к тому же чтобы он стал в нём героем, Макото был напуган и пребывал в замешательстве');
/*11 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/49613/main_2x-9583c4131192a80864028c49e39c59ee.webp', 'Chiyu Mahou no Machigatta Tsukaikata', 'Застряв в школьном холле и глядя на проливной дождь на улице, старшеклассник Кэн Усато размышлял об унылости собственной жизни, в которой ничегошеньки не происходит');
/*12 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/52347/main_2x-c30aaf75c27664a82b9b495dc339ccce.webp', 'Shangri-La Frontier: Kusoge Hunter, Kamige ni Idoman to su', 'Как правило, у старшеклассников насыщенная загруженная жизнь: одни добиваются успехов в спорте, вторые усиленно вгрызаются в гранит науки, третьи ищут романтики и любви.');
/*13 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/53590/main_2x-80585235f29a4402576be770bea76cb6.webp', 'Saijaku Tamer wa Gomi Hiroi no Tabi wo Hajimemashita.', 'Жизнь маленькой Фемиции изменилась в один день: любящие родные возненавидели её, безобидные доселе соседи стали врагами, а сама она превратилась в изгоя.');
/*14 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/55528/main_2x-e99f4a5626718c650d8118e9b01f9648.webp', 'Yuuki Bakuhatsu Bang Bravern', 'Десять лет назад по всему миру стали открываться некие «врата», ведущие в подземелья с монстрами, противостоять которым оказалось не под силу даже военным.');
/*15 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/54617/main_2x-34650dbbda6b1e7881311bb2342fa7b0.webp', 'Kyuujitsu no Warumono-san', 'Испокон веков величайшие злодеи заставляли героев и простой люд трепетать от страха.');
/*16 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/1199/main_2x-273a1f654c2b529f2910a252b3c8c91d.webp', 'Binghuo Mochu 2', '');
/*17 Тайтл*/
INSERT INTO youmiteru_backend.title(title_image_url, name, description)
VALUES ('https://desu.shikimori.one/uploads/poster/animes/53477/main_2x-38aed83f952e8bbb7f19a0eb6ccd8ac6.webp', 'Nintama Rantarou', '');
