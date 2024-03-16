package ru.youmiteru.backend.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.Comment;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.dto.CommentDTO;
import ru.youmiteru.backend.repositories.CommentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("test comment service")
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private static Season seasonPage;

    @InjectMocks
    private static CommentService commentService;

    @Mock
    CommentRepository commentRepository;

    private static final LocalDateTime localDateTime = LocalDateTime.now();
    private static Comment fakeComm;
    private static User fakeUser;

    @BeforeAll
    static void init() {
        fakeComm = mockedComment();
        fakeUser = mockedUser();
    }

    @Test
    @DisplayName("shouldReturnListOfComments")
    void shouldReturnListOfComments() {
        when(seasonPage.getSeasonCommentList()).thenReturn(List.of(fakeComm, fakeComm));

        List<CommentDTO> comments = commentService.getCommentsList(seasonPage);

        assertEquals(2, comments.size());
    }

    @Test
    @DisplayName("shouldConvertCommentToCommentDto")
    void shouldConvertCommentToCommentDto() {

        CommentDTO correctedCommentDTO = new CommentDTO(
            1L,
            localDateTime,
            "TEST!!!",
            fakeUser.getProfileImageUrl(),
            fakeUser.getId(),
            10,
            new ArrayList<>()
        );

        CommentDTO checkedCommentDTO = commentService.convertToCommentDto(fakeComm);

        assertEquals(correctedCommentDTO, checkedCommentDTO);
    }
    private static User mockedUser() {
        User user = new User();

        user.setId(1L);
        user.setProfileImageUrl("Lol");
        user.setName("FakeUserForTest");

        return user;
    }

    private static Comment mockedComment() {
        Comment fc = new Comment();

        fc.setId(1L);
        fc.setCreationDate(localDateTime);
        fc.setRatingValue(10);
        fc.setMessage("TEST!!!");
        fc.setWriter(mockedUser());

        return fc;
    }
}
