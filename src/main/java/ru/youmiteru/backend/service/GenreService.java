package ru.youmiteru.backend.service;

import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.GenreDTO.GenreDto;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.TitleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final TitleRepository titleRepository;

    public GenreService(GenreRepository genreRepository, TitleRepository titleRepository) {
        this.genreRepository = genreRepository;
        this.titleRepository = titleRepository;
    }

    public GenreDto associateGenreWithTitle(Long genreId, Long titleId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        Optional<Title> optionalTitle = titleRepository.findById(titleId);

        if (optionalGenre.isPresent() && optionalTitle.isPresent()) {
            Genre genre = optionalGenre.get();
            Title title = optionalTitle.get();

            genre.getTitles().add(title);
            title.getGenres().add(genre);

            Genre updatedGenre = genreRepository.save(genre);
            return convertToDto(updatedGenre);
        }

        return null;
    }

    public GenreDto dissociateGenreFromTitle(Long genreId, Long titleId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        Optional<Title> optionalTitle = titleRepository.findById(titleId);

        if (optionalGenre.isPresent() && optionalTitle.isPresent()) {
            Genre genre = optionalGenre.get();
            Title title = optionalTitle.get();

            genre.getTitles().remove(title);
            title.getGenres().remove(genre);

            Genre updatedGenre = genreRepository.save(genre);
            return convertToDto(updatedGenre);
        }

        return null;
    }

    private GenreDto convertToDto(Genre genre) {
        List<Long> titleIds = genre.getTitles().stream()
            .map(Title::getId)
            .collect(Collectors.toList());

        return new GenreDto(genre.getId(), genre.getName(), titleIds);
    }
}

