package ru.youmiteru.backend.controller.admin_panel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.dto.GenreDTO.GenreDto;
import ru.youmiteru.backend.dto.GenreDTO.GenreTitleDto;
import ru.youmiteru.backend.service.GenreService;
import ru.youmiteru.backend.service.TitleService;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    private final TitleService titleService;

    public GenreController(GenreService genreService, TitleService titleService) {
        this.genreService = genreService;
        this.titleService = titleService;
    }

    @PostMapping("/{genreId}/titles/{titleId}")
    public ResponseEntity<GenreDto> associateGenreWithTitle(
        @PathVariable Long genreId,
        @PathVariable Long titleId,
        @RequestBody GenreTitleDto genreTitleDto
    ) {
        if (genreId.equals(genreTitleDto.genreId()) && titleId.equals(genreTitleDto.titleId())) {
            GenreDto updatedGenre = genreService.associateGenreWithTitle(genreId, titleId);
            if (updatedGenre != null) {
                return ResponseEntity.ok(updatedGenre);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{genreId}/titles/{titleId}")
    public ResponseEntity<GenreDto> dissociateGenreFromTitle(
        @PathVariable Long genreId,
        @PathVariable Long titleId,
        @RequestBody GenreTitleDto genreTitleDto
    ) {
        if (genreId.equals(genreTitleDto.genreId()) && titleId.equals(genreTitleDto.titleId())) {
            GenreDto updatedGenre = genreService.dissociateGenreFromTitle(genreId, titleId);
            if (updatedGenre != null) {
                return ResponseEntity.ok(updatedGenre);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
