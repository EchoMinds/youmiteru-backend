package ru.youmiteru.backend.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void testHandleSeasonNotFoundException() {
        // Создаем объект SeasonNotFoundException
        SeasonNotFoundException seasonNotFoundException = new SeasonNotFoundException();

        // Вызываем метод handleExecption для SeasonNotFoundException
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleExecption(seasonNotFoundException);

        // Проверяем, что возвращенный ResponseEntity содержит ожидаемое сообщение и статус код 404
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Season not found", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandleTitleNotFoundException() {
        // Создаем объект TitleNotFoundException
        TitleNotFoundException titleNotFoundException = new TitleNotFoundException();

        // Вызываем метод handleExecption для TitleNotFoundException
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleExecption(titleNotFoundException);

        // Проверяем, что возвращенный ResponseEntity содержит ожидаемое сообщение и статус код 404
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Title not found", responseEntity.getBody().getMessage());
    }
}
