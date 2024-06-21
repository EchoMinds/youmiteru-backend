package ru.youmiteru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.repositories.TitleRepository;
import ru.youmiteru.backend.service.TitleService;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    TitleService titleService;

    @GetMapping
    public ResponseEntity<?> searchEngine() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> searchEngine(@RequestParam("searchString") String searchString) {
        return titleService.titleForSearch(searchString);
    }
}
