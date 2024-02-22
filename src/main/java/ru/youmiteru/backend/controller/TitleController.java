package ru.youmiteru.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.service.TitleService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/youmiteru")
@RequiredArgsConstructor
public class TitleController {
    private final TitleService titleService;

    @GetMapping("/catalog")
    public List<TitleDTO.Response.Catalog> getCatalog(@RequestParam(value = "genres", required = false) List<String> genres,
                                                      @RequestParam(value = "dates", required = false) List<Long> date){
        return titleService.getCatalog(genres, date);
    }


}
