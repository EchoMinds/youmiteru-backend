package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.TitleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;
    private final GenreRepository genreRepository;

    //Возвращает каталог с сортировкой жанров
    public List<TitleDTO.Response.Catalog> getCatalog (List<String> filter) {

        List<TitleDTO.Response.Catalog> titleDto = filterConvertorCatalog(filter)
            .stream().map(this::convertToCatalog).collect(Collectors.toList());

        return titleDto;

    }

    //конвертирует для каталога
    public TitleDTO.Response.Catalog convertToCatalog(Title title){
        TitleDTO.Response.Catalog dto = new TitleDTO.Response.Catalog();

        dto.setTitleName(title.getName());
        dto.setTitleImageUrl(title.getTitleImageUrl());

        return dto;
    }

    public List<Title> filterConvertorCatalog(List<String> filter){
        List<Long> genreIds = new ArrayList<>();
        List<Long> titleIds = new ArrayList<>();

        for (String name : filter){
            for (Genre searchGenre : genreRepository.findByName(name)){
                genreIds.add(searchGenre.getId());
            }
        }

        List<Long> checkTitleIds = titleRepository.findTitleIdsByGenreIdsList(genreIds);

        for (Long id : checkTitleIds){
            int i = 0;
            for (Long ids : titleRepository.findGenreIdsByTitleIds(id)){
                for (Long oo : genreIds){
                    if (oo.equals(ids)){
                        i++;
                    }
                }
            }
            if (i == genreIds.size()){
                titleIds.add(id);
            }
        }

        return titleRepository.findAllById(titleIds);
    }

}
