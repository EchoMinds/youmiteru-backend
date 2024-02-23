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


    private User mockedUser() {
        User user = new User();

        user.setId(1L);
        user.setProfileImageUrl("Lol");
        user.setName("FakeUserForTest");

        return user;
    }

    private Comment mockedComment() {
        Comment fc = new Comment();

        fc.setId(1L);
        fc.setCreationDate(LocalDateTime.now());
        fc.setRatingValue(10);
        fc.setMessage("TEST!!!");
        fc.setWriter(mockedUser());

        return fc;
    }


    @Test
    void shouldReturnListOfComments() {
        Comment fakeComm = mockedComment();

        when(commentRepository.findByReplyTo(fakeComm)).thenReturn(new ArrayList<>());
        when(seasonPage.getSeasonCommentList()).thenReturn(List.of(fakeComm, fakeComm));

        List<CommentDTO.Response.Comments> comments = commentService.getCommentsList(seasonPage);

        assertEquals(2, comments.size());
    }

}
