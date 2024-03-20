package ru.youmiteru.backend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.convertors.UsersConvertors;
import ru.youmiteru.backend.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private  UserRepository userRepository;
    @Mock
    private  UsersConvertors usersConvertors;
    @Mock
    private  SeasonConvertors seasonConvertors;
}
