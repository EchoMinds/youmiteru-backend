package ru.youmiteru.backend.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.fakeDomain.FakeDomainCreator;
import ru.youmiteru.backend.user.service.UserPanelService;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserPanelControllerTest {
    @InjectMocks
    private UserPanelController userPanelController;
    @Mock
    private UserPanelService userPanelService;

    private Long user_id;
    private String name;
    private User fakeUser;

    @BeforeEach
    void init(){
        name = "name";
        user_id = 1L;
        fakeUser = FakeDomainCreator.createFakeUser();
    }

    @Test
    void testPutUserNameOK(){
        when(userPanelService.updateUserName(user_id, name)).thenReturn(fakeUser);
        HttpStatus httpStatus = userPanelController.putUserName(user_id, name);

        assertEquals(httpStatus, HttpStatus.OK);
    }

    @Test
    void testPutUserNameBadRequest(){
        HttpStatus httpStatus = userPanelController.putUserName(user_id, name);

        assertEquals(httpStatus, HttpStatus.BAD_REQUEST);
    }
}
