package ru.youmiteru.backend.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.Season;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("test for season repository")
@ExtendWith(MockitoExtension.class)
class SeasonRepositoryTest {

    @Nested
    @DisplayName("find Announcement")
    class findAnnouncementTest {
        @InjectMocks
        List<Season> fakeList = new ArrayList<>();

        @Mock
        private SeasonRepository seasonRep;

        @Test
        @DisplayName("Nice position")
        void testFindAnnouncementIfRepHaveSeasons() {
            assertEquals(fakeList, seasonRep.findAnnouncement());
        }
    }

}
