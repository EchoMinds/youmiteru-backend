package ru.youmiteru.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.service.TitleService;

import java.util.List;

@RestController
@RequestMapping("/youmiteru")
@RequiredArgsConstructor
public class TitleController {
    private final TitleService titleService;

    @GetMapping("/catalog")
    public List<TitleDTO.Response.Catalog> getCatalog(@RequestParam(value = "filter") List<String> filter){
        return titleService.getCatalog(filter);
    }


}
