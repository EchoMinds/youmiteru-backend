package ru.youmiteru.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.youmiteru.backend.fakeDomain.FakeDomainCreator;
import ru.youmiteru.backend.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    @Test
    void getUserPageById() {
        when(userService.getUserById(1L)).thenReturn(FakeDomainCreator.createFakeUserDTO());

        assertEquals(new ResponseEntity<>(FakeDomainCreator.createFakeUserDTO(), HttpStatus.OK),
            userController.getUserPageById(1L));
    }
}
