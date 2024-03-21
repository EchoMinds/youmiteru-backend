package ru.youmiteru.backend.fakeDomain;

import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.HomePage;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;
import ru.youmiteru.backend.dto.Title.TitlePageDTO;
import ru.youmiteru.backend.dto.UserDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FakeDomainCreator {
    private static Title fakeTitle;
    private static Season fakeSeason;
    private static Genre fakeGenre;
    private static Comment fakeComment;
    private static User fakeUser;
    private static Rating fakeRating;

    public static Title createFakeTitle() {
        fakeTitle = new Title();
        fakeTitle.setId(1L);
        fakeTitle.setName("Boku no Kokoro no Yabai Yatsu Season 2");
        fakeTitle.setTitleImageUrl("url");
        fakeTitle.setDescription("Повседневная жизнь маленького");
        fakeTitle.setGenres(List.of(new Genre("Shoujo")));
        fakeTitle.setSeasonList(List.of(createFakeSeason()));

        return fakeTitle;
    }

    public static Season createFakeSeason() {
        //fck, circle
        fakeTitle = new Title();
        fakeTitle.setId(1L);
        fakeTitle.setName("Boku no Kokoro no Yabai Yatsu Season 2");
        fakeTitle.setTitleImageUrl("url");
        fakeTitle.setDescription("Повседневная жизнь маленького");
        fakeTitle.setGenres(List.of(new Genre("Shoujo")));

        fakeSeason = new Season();

        fakeSeason = new Season();
        fakeSeason.setId(1L);
        fakeSeason.setSeasonImageUrl("https://example.com/season_image.jpg");
        fakeSeason.setName("Fake Season");
        fakeSeason.setDescription("This is a fake season for testing purposes.");
        fakeSeason.setReleaseDate(LocalDate.of(2023, 1, 1));
        fakeSeason.setAgeRestriction("2023");
        fakeSeason.setYearSeason("2023");
        fakeSeason.setAnimeBannerUrl("https://example.com/banner.jpg");
        fakeSeason.setTitle(fakeTitle);
        fakeSeason.setTitleState(TitleState.ANNOUNCEMENT);
        fakeSeason.setAnimeFormat(AnimeFormat.TV_SHOW);

        fakeTitle.setSeasonList(List.of(fakeSeason));
        return fakeSeason;
    }

    public static Genre createFakeGenre() {
        fakeGenre = new Genre();

        fakeGenre.setId(1L);
        fakeGenre.setName("Shoujo");
        fakeGenre.setTitles(List.of(createFakeTitle()));

        return fakeGenre;
    }

    public static Comment createFakeComment() {
        fakeComment = new Comment();

        fakeComment.setId(1L);
        fakeComment.setCreationDate(LocalDateTime.now());
        fakeComment.setMessage("Это фейковый комментарий для тестирования.");
        fakeComment.setRatingValue(5);
        fakeComment.setWriter(createFakeUser());
        fakeComment.setSeason(createFakeSeason());
        fakeComment.setAnswerForThisCommList(new ArrayList<>());

        return fakeComment;
    }

    public static User createFakeUser() {
        //for fix circle
        fakeComment = new Comment();
        fakeSeason = createFakeSeason();
        fakeRating = new Rating();

        fakeComment.setId(1L);
        fakeComment.setCreationDate(LocalDateTime.now());
        fakeComment.setMessage("Это фейковый комментарий для тестирования.");
        fakeComment.setRatingValue(5);
        fakeComment.setSeason(createFakeSeason());

        fakeRating.setId(1L);
        fakeRating.setValue(5);
        fakeRating.setSeason(fakeSeason);

        // createUser
        fakeUser = new User();

        fakeUser.setId(1L);
        fakeUser.setProfileImageUrl("https://example.com/profile.jpg");
        fakeUser.setName("Иван Иванов");
        fakeUser.setEmail("ivan@example.com");
        fakeUser.setRole(Role.USER);
        fakeUser.setCreationTime(LocalDateTime.now());

        fakeUser.setUsersComms(List.of(fakeComment));
        fakeUser.setRatingList(List.of(fakeRating));
        fakeUser.setFavoriteSeasonList(List.of(fakeSeason));

        return fakeUser;
    }

    public static Rating createFakeRating() {
        fakeRating = new Rating();

        fakeRating.setId(1L);
        fakeRating.setValue(5);
        fakeRating.setUser(createFakeUser());


        return fakeRating;
    }

    public static TitlePageDTO createTitlePageDTO() {
        return new TitlePageDTO(
            1L,
            "Boku no Kokoro no Yabai Yatsu Season 2",
            "url",
            "Повседневная жизнь маленького",
            List.of("Shoujo"),
            List.of(new HomePage(1L, "fakeNameDTO", "fakeDescription", "fakeURL"))
        );
    }

    public static UserDTO createFakeUserDTO() {
        return new UserDTO(
            1L, "https://example.com/profile.jpg", "Иван Иванов",
            List.of(new FavoriteSeason(
                1L, "Fake Season", "https://example.com/season_image.jpg")),
            List.of(new RatedSeason(
                1L, "Fake Season", "https://example.com/season_image.jpg", 5))
        );

    }
}
