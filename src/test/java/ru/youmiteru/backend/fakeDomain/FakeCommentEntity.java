package ru.youmiteru.backend.fakeDomain;

import ru.youmiteru.backend.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FakeCommentEntity {
    public static Comment creareComment(){
        Comment fakeComment = new Comment();
        fakeComment.setId(1L);
        fakeComment.setCreationDate(LocalDateTime.of(2023, 3, 19, 10, 0));
        fakeComment.setMessage("сообщение 1");
        fakeComment.setRatingValue(1);
        fakeComment.setReplyTo(null);
        fakeComment.setAnswerForThisCommList(null);
        fakeComment.setWriter(null);
        fakeComment.setSeason(null);

        return fakeComment;
    }

}
